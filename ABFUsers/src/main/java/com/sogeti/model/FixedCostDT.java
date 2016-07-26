package com.sogeti.model;

import java.io.Serializable;


/**
 * The persistent class for the fixed_cost database table.
 * 
 */
public class FixedCostDT implements Serializable {
	private static final long serialVersionUID = 1L;

	private int fixedcostId;
	private String fixedcostDescription;
	private String fixedcostName;
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

	public FixedCostDT() {
	}

	/**
	 * @return the fixedcostId
	 */
	public int getFixedcostId() {
		return fixedcostId;
	}

	/**
	 * @param fixedcostId the fixedcostId to set
	 */
	public void setFixedcostId(int fixedcostId) {
		this.fixedcostId = fixedcostId;
	}

	/**
	 * @return the fixedcostDescription
	 */
	public String getFixedcostDescription() {
		return fixedcostDescription;
	}

	/**
	 * @param fixedcostDescription the fixedcostDescription to set
	 */
	public void setFixedcostDescription(String fixedcostDescription) {
		this.fixedcostDescription = fixedcostDescription;
	}

	/**
	 * @return the fixedcostName
	 */
	public String getFixedcostName() {
		return fixedcostName;
	}

	/**
	 * @param fixedcostName the fixedcostName to set
	 */
	public void setFixedcostName(String fixedcostName) {
		this.fixedcostName = fixedcostName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FixedCostDT [fixedcostId=");
		builder.append(fixedcostId);
		builder.append(", fixedcostDescription=");
		builder.append(fixedcostDescription);
		builder.append(", fixedcostName=");
		builder.append(fixedcostName);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	

}