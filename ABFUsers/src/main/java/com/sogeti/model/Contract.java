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
	private String comments;
	private String companyName;
	private String contractName;
	private Date contractStartDate;
	private Date contractEndDate;

	private Date contractCreatedDatetime;
	private Date contractModifiedDateTime;
	private String customerName;
	private int loginId;
	private int statusId;
	private String status;
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
	 * @return the contractCreatedDatetime
	 */
	public Date getContractCreatedDatetime() {
		return contractCreatedDatetime;
	}
	/**
	 * @param contractCreatedDatetime the contractCreatedDatetime to set
	 */
	public void setContractCreatedDatetime(Date contractCreatedDatetime) {
		this.contractCreatedDatetime = contractCreatedDatetime;
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
	 * @return the contractModifiedDateTime
	 */
	public Date getContractModifiedDateTime() {
		return contractModifiedDateTime;
	}
	/**
	 * @param contractModifiedDateTime the contractModifiedDateTime to set
	 */
	public void setContractModifiedDateTime(Date contractModifiedDateTime) {
		this.contractModifiedDateTime = contractModifiedDateTime;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contract [contractId=");
		builder.append(contractId);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", contractName=");
		builder.append(contractName);
		builder.append(", contractStartDate=");
		builder.append(contractStartDate);
		builder.append(", contractEndDate=");
		builder.append(contractEndDate);
		builder.append(", contractCreatedDatetime=");
		builder.append(contractCreatedDatetime);
		builder.append(", contractModifiedDateTime=");
		builder.append(contractModifiedDateTime);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", loginId=");
		builder.append(loginId);
		builder.append(", statusId=");
		builder.append(statusId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
