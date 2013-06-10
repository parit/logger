package com.jti.commandLine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class FileReader implements Iterable<String> {

	final File fl;

	public FileReader(File file) {
		fl = file;
	}

	@Override
	public Iterator<String> iterator() {
		try {
			final BufferedReader bufferedReader = new BufferedReader(
					new java.io.FileReader(fl));
			return new Iterator<String>() {
				String readLine = null;

				@Override
				public boolean hasNext() {
					try {
						readLine = bufferedReader.readLine();
						if (readLine == null) {
							bufferedReader.close();
						}
					} catch (IOException e) {
						e.printStackTrace();
						readLine = null;
					}
					return readLine != null;
				}

				@Override
				public String next() {
					return readLine;
				}

				@Override
				public void remove() {
					throw new UnsupportedOperationException(
							"Cannot remove lines from the file");
				}
			};
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
} // FileReader
