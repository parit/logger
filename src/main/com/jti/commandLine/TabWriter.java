package com.jti.commandLine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class TabWriter implements Writer {

	private PrintWriter printWriter = null;

	public void write(Model model) throws IOException {
		printWriter.print(model.getJobId() + "\t");
		printWriter.print((model.getType() == null ? "" : model.getType())
				+ "\t");
		printWriter.print(model.getState() + "\t");
		printWriter.print((model.getStatus() == null ? "" : model.getStatus())
				+ "\t");
		printWriter.print((model.getPolicy() == null ? "" : model.getPolicy())
				+ "\t");
		printWriter.print((model.getSchedule() == null ? "" : model
				.getSchedule()) + "\t");
		printWriter.print((model.getClient() == null ? "" : model.getClient())
				+ "\t");
		printWriter.print((model.getDestMediaServer() == null ? "" : model
				.getDestMediaServer()) + "\t");
		printWriter.print((model.getStarted() == null ? "" : Utils
				.toString(model.getStarted())) + "\t");
		printWriter.print((model.getDestStUnit() == null ? "" : model
				.getDestStUnit()) + "\t");
		printWriter.print((model.getEnded() == null ? "" : Utils.toString(model
				.getEnded())) + "\t");
		printWriter.print((model.getKilobytes() == null ? "" : model
				.getKilobytes()) + "\t");
		printWriter.print((model.getkBperSec() == null ? "" : model
				.getkBperSec()) + "\n");
	}

	@Override
	public void prepare(OutputStream outputStream) throws IOException {
		printWriter = new PrintWriter(outputStream);
	}

	@Override
	public void finish() throws IOException {
		printWriter.flush();
		printWriter.close();
	}
} // TabWriter