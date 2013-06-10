package com.jti.commandLine.lineHandlers;

import com.jti.commandLine.Model;

public interface LineHandler<T extends Model> {
	public T handle(String line);

	public boolean canHandle(String str);
} // LineHandler
