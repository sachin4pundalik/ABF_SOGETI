package com.sogeti.model;

public class StayTypeDT {
	private int stayTypeId;
	private String stayType;
	private int active;
	
	
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
	public int getStayTypeId() {
		return stayTypeId;
	}
	public void setStayTypeId(int stayTypeId) {
		this.stayTypeId = stayTypeId;
	}
	public String getStayType() {
		return stayType;
	}
	public void setStayType(String stayType) {
		this.stayType = stayType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StayTypeDT [stayTypeId=");
		builder.append(stayTypeId);
		builder.append(", stayType=");
		builder.append(stayType);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
	
	
}
