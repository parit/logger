package com.jti.commandLine;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.util.DefaultPrettyPrinter;

public class JSONWriter implements Writer {

	JsonGenerator jGenerator = null;
	PrintWriter printWriter = null;

	public void write(Model model) throws IOException {
		jGenerator.writeStartObject();
		jGenerator.writeFieldName("jobid");
		jGenerator.writeString(model.getJobId());
		jGenerator.writeFieldName("type");
		jGenerator.writeString(model.getType());
		jGenerator.writeFieldName("state");
		jGenerator.writeString(model.getState());
		jGenerator.writeFieldName("status");
		jGenerator.writeString(model.getStatus());
		jGenerator.writeFieldName("policy");
		jGenerator.writeString(model.getPolicy());
		jGenerator.writeFieldName("schedule");
		jGenerator.writeString(model.getSchedule());
		jGenerator.writeFieldName("client");
		jGenerator.writeString(model.getClient());
		jGenerator.writeFieldName("destMediaServer");
		jGenerator.writeString(model.getDestMediaServer());
		jGenerator.writeFieldName("started");
		jGenerator.writeString(Utils.toString(model.getStarted()));
		jGenerator.writeFieldName("destSetUnit");
		jGenerator.writeString(model.getDestStUnit());
		jGenerator.writeFieldName("ended");
		jGenerator.writeString(Utils.toString(model.getEnded()));
		jGenerator.writeFieldName("kbs");
		jGenerator.writeString(model.getKilobytes());
		jGenerator.writeFieldName("kbps");
		jGenerator.writeString(model.getkBperSec());
		jGenerator.writeEndObject();
	}

	@Override
	public void prepare(OutputStream outputStream) throws IOException {
		printWriter = new PrintWriter(outputStream);
		jGenerator = new JsonFactory().createJsonGenerator(printWriter);
		jGenerator.setPrettyPrinter(new DefaultPrettyPrinter());
		jGenerator.writeStartObject(); // {
		jGenerator.writeArrayFieldStart("results");
	}

	@Override
	public void finish() throws IOException {
		jGenerator.writeEndArray();
		jGenerator.writeEndObject();
		printWriter.flush();
		printWriter.close();
	}
} // JSONWriter
