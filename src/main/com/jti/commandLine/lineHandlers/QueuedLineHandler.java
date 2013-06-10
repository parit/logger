package com.jti.commandLine.lineHandlers;

import java.util.StringTokenizer;

import com.jti.commandLine.Model;
import com.jti.commandLine.Utils;

public class QueuedLineHandler implements LineHandler<Model> {
	@Override
	public Model handle(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		Model model = new Model(State.Queued.toString());
		int counter = 0;
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			switch (counter) {
			case 0:
				model.setJobId(token);
				break;
			case 1:
				if ("Catalog".equals(token)) {
					model.setType(token + " " + tokenizer.nextToken());
				} else {
					model.setType(token);
				}
				break;
			case 2:
				// state
				break;
			case 3:
				model.setPolicy(token);
				break;
			case 4:
				model.setSchedule(token);
				break;
			case 5:
				model.setClient(token);
				break;
			case 6:
				model.setStarted(Utils.toDate(token + " "
						+ tokenizer.nextToken() + " " + tokenizer.nextToken()));
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
		return str != null && str.indexOf(State.Queued.toString()) != -1;
	}
} // QueuedLineHandler
