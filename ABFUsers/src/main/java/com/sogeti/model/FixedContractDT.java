package com.sogeti.model;

import java.math.BigDecimal;

public class FixedContractDT {

	
	private BigDecimal price;
	private String description;
	private Integer contractId;
	private int fixedId;
	private int fixedcostId;
	private String fixedcostName;
	
	
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
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getFixedId() {
		return fixedId;
	}
	public void setFixedId(int fixedId) {
		this.fixedId = fixedId;
	}
	public int getFixedcostId() {
		return fixedcostId;
	}
	public void setFixedcostId(int fixedcostId) {
		this.fixedcostId = fixedcostId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FixedContractDT [price=");
		builder.append(price);
		builder.append(", description=");
		builder.append(description);
		builder.append(", contractId=");
		builder.append(contractId);
		builder.append(", fixedId=");
		builder.append(fixedId);
		builder.append(", fixedcostId=");
		builder.append(fixedcostId);
		builder.append("]");
		return builder.toString();
	}
	
}
