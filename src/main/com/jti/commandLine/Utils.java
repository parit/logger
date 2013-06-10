package com.jti.commandLine;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {

	final static DateFormat dateFormat = new SimpleDateFormat(
			"MM/dd/yyyy hh:mm:ss a", Locale.ENGLISH);

	public static Date toDate(String str) {
		try {
			return dateFormat.parse(str);
		} catch (ParseException e) {
			Cmd.logger.error("Error parsing str to date : str ", e);
		}
		return null;
	}

	public static String toString(Date date) {
		return date == null ? "" : dateFormat.format(date);
	}
}
