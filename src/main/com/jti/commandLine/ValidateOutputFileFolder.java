package com.jti.commandLine;

import java.io.File;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateOutputFileFolder implements IParameterValidator {
	@Override
	public void validate(String name, String value) throws ParameterException {
		if (!new File(value).getParentFile().exists()) {
			throw new ParameterException("Output folder " + value
					+ " doesn't exist");
		}
		if (new File(value).exists()) {
			throw new ParameterException("Outfile " + value
					+ " already exists. Provide a different file name");
		}
	} // ValidateOutputFileFolder
}
