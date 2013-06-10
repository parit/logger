package com.jti.commandLine.lineHandlers;

public enum State {
	Done("Done"), Active("Active"), Queued("Queued"), Waiting(
			"Waiting-to-Retry"), Junk("");

	private String represent;

	private State(String represent) {
		this.represent = represent;
	}

	@Override
	public String toString() {
		return represent;
	}
} // State
