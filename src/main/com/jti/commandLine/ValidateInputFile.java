package com.jti.commandLine;

import java.io.File;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ValidateInputFile implements IParameterValidator {
	@Override
	public void validate(String name, String value) throws ParameterException {
		if (!(new File(value).exists())) {
			throw new ParameterException("Input file " + value + " not found");
		}
	}
} // ValidateInputFile
