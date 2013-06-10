package com.jti.commandLine.lineHandlers;

import java.util.StringTokenizer;

import com.jti.commandLine.Model;

public class JunkLineHandler implements LineHandler<Model> {
	@Override
	public Model handle(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		Model model = new Model(State.Junk.toString());
		int counter = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			switch (counter) {
			case 0:
				model.setJobId(token);
				break;
			default:
				System.err.println("Extra field encounterd for line " + str);
			}
			counter++;
		}
		return model;
	}

	@Override
	public boolean canHandle(String str) {
		return str != null
				&& (new StringTokenizer(str, " ")).countTokens() == 1;
	}
} // JunkLineHandler
