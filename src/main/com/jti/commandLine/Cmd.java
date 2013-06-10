package com.jti.commandLine;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.jti.commandLine.lineHandlers.ActiveLineHandler;
import com.jti.commandLine.lineHandlers.DoneLineHandler;
import com.jti.commandLine.lineHandlers.JunkLineHandler;
import com.jti.commandLine.lineHandlers.LineHandler;
import com.jti.commandLine.lineHandlers.QueuedLineHandler;
import com.jti.commandLine.lineHandlers.WaitingLineHandler;

public class Cmd {

	static List<LineHandler<Model>> handlers = new ArrayList<LineHandler<Model>>();
	static Map<String, Writer> writes = new HashMap<String, Writer>();

	public static Logger logger = LoggerFactory.getLogger(Cmd.class);

	public static void main(String[] args) {
		ArgumentBean bean = new ArgumentBean();
		try {
			new JCommander(bean, args);
		} catch (ParameterException e) {
			logger.error(e.getMessage(), e);
			System.exit(-1);
		}

		setUp(bean);
		FileReader fileReader = new FileReader(bean.getInputFile());
		Map<String, Model> map = new LinkedHashMap<String, Model>();
		logger.debug("Reading the input file");
		for (String str : fileReader) {
			boolean handled = false;
			for (LineHandler<Model> handler : handlers) {
				logger.debug(str);
				if (handler.canHandle(str)) {
					handled = true;
					Model model = handler.handle(str);
					if (!map.containsKey(model.getJobId())) {
						map.put(model.getJobId(), model);
					}
				}
			}
			if (!handled) {
				logger.error("Cannot handle the line: " + str);
			}
		}
		logger.debug("Input file read");
		if (map.size() > 0) {
			try {
				for (Entry<String, Writer> entry : writes.entrySet()) {
					entry.getValue()
							.prepare(
									new FileOutputStream(new File(bean
											.getOutputFile().getAbsolutePath()
											+ "." + entry.getKey())));
				}

				for (Entry<String, Model> modelEntry : map.entrySet()) {
					for (Entry<String, Writer> entry : writes.entrySet()) {
						entry.getValue().write(modelEntry.getValue());
					}
				}

				for (Entry<String, Writer> entry : writes.entrySet()) {
					entry.getValue().finish();
				}
			} catch (Exception e) {
				logger.error("Error writing the output file", e);
			}
			logger.info("Logs exported as tab to "
					+ bean.getOutputFile().getAbsolutePath());
		} else {
			logger.warn("No handler found for any of the lines");
		}

	}

	static void setUp(ArgumentBean argumentBean) {
		handlers.add(new ActiveLineHandler());
		handlers.add(new WaitingLineHandler());
		handlers.add(new QueuedLineHandler());
		handlers.add(new DoneLineHandler());
		handlers.add(new JunkLineHandler());

		for (int i = 0; i < ArgumentBean.supportedFormats.length; i++) {
			String format = ArgumentBean.supportedFormats[i];
			if ("tab".equals(format)) {
				writes.put("tab", new TabWriter());
			} else if ("sql".equals(format)) {
				String table = argumentBean.getTableName();
				if (table == null) {
					Cmd.logger
							.warn("No table name provided for sql script-generator therefore ignoring sql format for generation");
				} else {
					writes.put("sql", new SQLScriptWriter(table));
				}
			} else if ("json".equals(format)) {
				writes.put("json", new JSONWriter());
			}
		}
	}
} // Cmd
