package com.jti.commandLine.lineHandlers;

import java.util.StringTokenizer;

import com.jti.commandLine.Model;
import com.jti.commandLine.Utils;

public class DoneLineHandler implements LineHandler<Model> {
	@Override
	public Model handle(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, " ");
		Model model = new Model(State.Done.toString());
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
				model.setStatus(token);
				break;
			case 4:
				if ("Restore".equals(model.getType())) {
					counter++;
					model.setPolicy("-");
				} else {
					model.setPolicy(token);
					break;
				}
			case 5:
				if ("Restore".equals(model.getType())) {
					counter++;
					model.setSchedule("-");
				} else {
					model.setSchedule(token);
					break;
				}
			case 6:
				model.setClient(token);
				break;
			case 7:
				if (token.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
					counter++;
					model.setDestMediaServer("-");
				} else {
					model.setDestMediaServer(token);
					break;
				}
			case 8:
				model.setStarted(Utils.toDate(token + " "
						+ tokenizer.nextToken() + " " + tokenizer.nextToken()));
				break;
			case 9:
				if (token.matches("^\\d{1,2}/\\d{1,2}/\\d{4}$")) {
					counter++;
					model.setDestStUnit("-");
				} else {
					model.setDestStUnit(token);
					break;
				}
			case 10:
				model.setEnded(Utils.toDate(token + " " + tokenizer.nextToken()
						+ " " + tokenizer.nextToken()));
				break;
			case 11:
				model.setKilobytes(token);
				break;
			case 12:
				model.setkBperSec(token);
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
		return str != null && str.indexOf(State.Done.toString()) != -1;
	}
} // DoneLineHandler
