package com.sogeti.model;

import javax.persistence.Column;

public class StatusDT {
	private String description;
	
	private String statusName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}


}
