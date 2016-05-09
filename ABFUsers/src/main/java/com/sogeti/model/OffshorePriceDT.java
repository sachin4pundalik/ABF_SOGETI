package com.sogeti.model;

import java.math.BigDecimal;

public class OffshorePriceDT {

	private int offshorepriceId;
	private String description;

	private Integer lastUpdatedBy;

	private String lastUpdatedDatetime;

	private BigDecimal price;

	private Integer businessLineId;

	private Integer bandId;

	private Integer stayTypeId;
	
	private String bandName;
	
	private String stayTypeName;

	
	private String businessLineName;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public String getLastUpdatedDatetime() {
		return lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(String lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Integer getBusinessLineId() {
		return businessLineId;
	}

	public void setBusinessLineId(int businessLineId) {
		this.businessLineId = businessLineId;
	}


	public int getOffshorepriceId() {
		return offshorepriceId;
	}

	public void setOffshorepriceId(int offshorepriceId) {
		this.offshorepriceId = offshorepriceId;
	}

	public Integer getBandId() {
		return bandId;
	}

	public void setBandId(Integer bandId) {
		this.bandId = bandId;
	}

	public Integer getStayTypeId() {
		return stayTypeId;
	}

	public void setStayTypeId(Integer stayTypeId) {
		this.stayTypeId = stayTypeId;
	}

	public String getBandName() {
		return bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public String getStayTypeName() {
		return stayTypeName;
	}

	public void setStayTypeName(String stayTypeName) {
		this.stayTypeName = stayTypeName;
	}

	public String getBusinessLineName() {
		return businessLineName;
	}

	public void setBusinessLineName(String businessLineName) {
		this.businessLineName = businessLineName;
	}


}
