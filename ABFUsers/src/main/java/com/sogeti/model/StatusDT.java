package com.sogeti.model;

public class StatusDT {
	
	private int statusId;
	private String description;
	private String statusName;
	private int active;
	
	public StatusDT(){
		
	}
	
	/**
	 * @param statusId
	 * @param description
	 * @param statusName
	 */
	public StatusDT(int statusId, String description, String statusName, int active) {
		super();
		this.statusId = statusId;
		this.description = description;
		this.statusName = statusName;
		this.active = active;
	}
	
	
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}

	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StatusDT [statusId=");
		builder.append(statusId);
		builder.append(", description=");
		builder.append(description);
		builder.append(", statusName=");
		builder.append(statusName);
		builder.append("]");
		return builder.toString();
	}

	
}
