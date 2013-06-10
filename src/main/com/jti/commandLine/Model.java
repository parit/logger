package com.jti.commandLine;

import java.util.Date;

public class Model {

	private String jobId = null, type = null, state = null, status = null,
			policy = null, schedule = null, client = null,
			destMediaServer = null, destStUnit = null, kilobytes = null,
			kBperSec = null;

	private Date started = null;
	private Date ended = null;

	public Model(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public Model setType(String type) {
		this.type = type;
		return this;
	}

	public String getState() {
		return state;
	}

	public final void setState(String state) {
		throw new UnsupportedOperationException(
				"Status cannot be set externally on a model");
	}

	public String getStatus() {
		return status;
	}

	public Model setStatus(String status) {
		this.status = status;
		return this;
	}

	public String getPolicy() {
		return policy;
	}

	public Model setPolicy(String policy) {
		this.policy = policy;
		return this;
	}

	public String getSchedule() {
		return schedule;
	}

	public Model setSchedule(String schedule) {
		this.schedule = schedule;
		return this;
	}

	public String getClient() {
		return client;
	}

	public Model setClient(String client) {
		this.client = client;
		return this;
	}

	public String getDestMediaServer() {
		return destMediaServer;
	}

	public Model setDestMediaServer(String destMediaServer) {
		this.destMediaServer = destMediaServer;
		return this;
	}

	public Date getStarted() {
		return started;
	}

	public Model setStarted(Date started) {
		this.started = started;
		return this;
	}

	public String getDestStUnit() {
		return destStUnit;
	}

	public Model setDestStUnit(String destStUnit) {
		this.destStUnit = destStUnit;
		return this;
	}

	public Date getEnded() {
		return ended;
	}

	public Model setEnded(Date ended) {
		this.ended = ended;
		return this;
	}

	public String getKilobytes() {
		return kilobytes;
	}

	public Model setKilobytes(String kilobytes) {
		this.kilobytes = kilobytes;
		return this;
	}

	public String getkBperSec() {
		return kBperSec;
	}

	public Model setkBperSec(String kBperSec) {
		this.kBperSec = kBperSec;
		return this;
	}

	public String getJobId() {
		return jobId;
	}

	// public abstract boolean validate();

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
} // Model
