package com.jti.commandLine;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.beust.jcommander.Parameter;

public class ArgumentBean {
	@Parameter(names = { "-i" }, description = "Input file name", validateWith = ValidateInputFile.class, required = true)
	private String inputFile;
	@Parameter(names = { "-o" }, description = "Output file name without extensions. Extensions will depend on the format provided", validateWith = ValidateOutputFileFolder.class, required = true)
	private String outputFile;
	@Parameter(names = { "-fmt" }, description = "Comma separated list of output file formats. Default is tab. Supported are tab,json,sql")
	private String fmt;

	private List<String> formats;

	@Parameter(names = { "-table" }, description = "Name of the table to use for generating sql script file")
	private String tableName;
	public static String[] supportedFormats = { "tab", "sql", "json" };

	public File getInputFile() {
		return new File(inputFile);
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public File getOutputFile() {
		return new File(outputFile);
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public List<String> getFormats() {
		if (this.formats.size() == 0) {
			this.formats.add("tab");
		}
		return this.formats;
	}

	public void setTableName(String table) {
		this.tableName = table;
	}

	public String getTableName() {
		return this.tableName;
	}

	public String getFmt() {
		return this.fmt;
	}

	public void setFmt(String fmt) {
		this.formats = Arrays.asList(fmt.split(","));
		List<String> suptedFormats = Arrays.asList(supportedFormats);
		Iterator<String> iterator = this.formats.iterator();
		while (iterator.hasNext()) {
			String format = iterator.next();
			if (suptedFormats.contains(format)) {
				Cmd.logger.info("Unrecognised format detected " + format
						+ " ignoring it");
				iterator.remove();
			}
		}
	}
} // ArgumentBean

