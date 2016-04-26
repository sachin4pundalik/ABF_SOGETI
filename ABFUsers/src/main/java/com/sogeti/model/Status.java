package com.sogeti.model;

public class Status {

	private int id;

	private String statusName;

	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Status [id=");
		builder.append(id);
		builder.append(", statusName=");
		builder.append(statusName);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
