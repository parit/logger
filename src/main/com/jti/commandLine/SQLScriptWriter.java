package com.jti.commandLine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class SQLScriptWriter implements Writer {

	private PrintWriter pw = null;
	private String tableName = null;

	public SQLScriptWriter(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public void prepare(OutputStream outputStream) throws IOException {
		pw = new PrintWriter(outputStream);
	}

	@Override
	public void write(Model model) throws IOException {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO TABLE " + tableName + " (jobid, type, "
				+ "state, status, policy, schedule, client, destMediaServer, "
				+ "started, destStUnit, ended, kbs, kbps) VALUES (");
		buffer.append(model.getJobId() + ",");
		buffer.append(model.getType() + ",");
		buffer.append(changeTONull(model.getState()) + ",");
		buffer.append(changeTONull(model.getStatus()) + ",");
		buffer.append(changeTONull(model.getPolicy()) + ",");
		buffer.append(changeTONull(model.getSchedule()) + ",");
		buffer.append(changeTONull(model.getClient()) + ",");
		buffer.append(changeTONull(model.getDestMediaServer()) + ",");
		String _temp = changeTONull(Utils.toString(model.getStarted()));
		if (_temp.equals("null")) {
			buffer.append(_temp + ",");
		} else {
			buffer.append("TO_TIMESTAMP(" + _temp + ",'DD/MM/YYYY HH:MI:SS'),");
		}
		buffer.append(changeTONull(model.getDestStUnit()) + ",");
		_temp = changeTONull(Utils.toString(model.getEnded()));
		if (_temp.equals("null")) {
			buffer.append(_temp + ",");
		} else {
			buffer.append("TO_TIMESTAMP(" + _temp + ",'DD/MM/YYYY HH:MI:SS'),");
		}
		buffer.append(changeTONull(model.getKilobytes()) + ",");
		buffer.append(changeTONull(model.getKilobytes()));
		buffer.append(");");
		pw.println(buffer.toString());
	}

	private String changeTONull(String str) {
		if (str == null || str.isEmpty() || str.equals("-"))
			return "null";
		else
			return "'" + str + "'";
	}

	@Override
	public void finish() throws IOException {
		pw.flush();
		pw.close();
	}
} // SQLScriptWriter
