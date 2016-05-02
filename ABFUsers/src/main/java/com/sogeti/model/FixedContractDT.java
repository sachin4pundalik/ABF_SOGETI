package com.sogeti.model;

public class FixedContractDT {

	
	private String price;
	private String description;
	private Integer contractId;
	private int fixedId;
	private int fixedcostId;
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
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
}
