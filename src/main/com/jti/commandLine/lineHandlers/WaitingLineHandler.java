package com.jti.commandLine.lineHandlers;

import java.util.StringTokenizer;

import com.jti.commandLine.Model;

public class WaitingLineHandler implements LineHandler<Model> {
	@Override
	public Model handle(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		Model model = new Model(State.Waiting.toString());
		int counter = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			switch (counter) {
			case 0:
				model.setJobId(token);
				break;
			case 2:
				model.setStatus(token);
				break;
			default:
				break;
			}
			counter++;
		}
		return model;
	}

	@Override
	public boolean canHandle(String str) {
		return str != null && str.indexOf(State.Waiting.toString()) != -1;
	}
} // WaitingLineHandler
