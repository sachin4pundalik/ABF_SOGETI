/**
 * 
 */
package com.sogeti.model;

import java.util.Date;

/**
 * @author rajukaly
 *
 */
public class Contract {

	private int contractId;
    private String contractName;
	private String customerName;
	private String companyName;
	private String comments;
	private String contractCreatedBy;
	private Date contractStartDate;
	private Date contractEndDate;
	private int loginId;
    /**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the contractCreatedBy
	 */
	public String getContractCreatedBy() {
		return contractCreatedBy;
	}
	/**
	 * @param contractCreatedBy the contractCreatedBy to set
	 */
	public void setContractCreatedBy(String contractCreatedBy) {
		this.contractCreatedBy = contractCreatedBy;
	}
	/**
	 * @return the contractStartDate
	 */
	public Date getContractStartDate() {
		return contractStartDate;
	}
	/**
	 * @param contractStartDate the contractStartDate to set
	 */
	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}
	/**
	 * @return the contractEndDate
	 */
	public Date getContractEndDate() {
		return contractEndDate;
	}
	/**
	 * @param contractEndDate the contractEndDate to set
	 */
	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * @return the loginId
	 */
	public int getLoginId() {
		return loginId;
	}
	/**
	 * @param loginId the loginId to set
	 */
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	private Status status;
	/**
	 * @return the contractId
	 */
	public int getContractId() {
		return contractId;
	}
	/**
	 * @param contractId the contractId to set
	 */
	public void setContractId(int contractId) {
		this.contractId = contractId;
	}
	/**
	 * @return the contractName
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * @param contractName the contractName to set
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contract [contractId=");
		builder.append(contractId);
		builder.append(", contractName=");
		builder.append(contractName);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", contractCreatedBy=");
		builder.append(contractCreatedBy);
		builder.append(", contractStartDate=");
		builder.append(contractStartDate);
		builder.append(", contractEndDate=");
		builder.append(contractEndDate);
		builder.append(", loginId=");
		builder.append(loginId);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
    
    
	
}
