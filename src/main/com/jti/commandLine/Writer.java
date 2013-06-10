package com.jti.commandLine;

import java.io.IOException;
import java.io.OutputStream;

public interface Writer {
	public void prepare(OutputStream outputStream) throws IOException;

	public void write(Model model) throws IOException;

	public void finish() throws IOException;
} // Writer
