package com.sogeti.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sogeti.db.models.Band;
import com.sogeti.db.models.BusinessLine;
import com.sogeti.db.models.Grade;
import com.sogeti.db.models.Role;
import com.sogeti.db.models.StayType;

public class OffshorePriceDT {

	private Integer offshorepriceId;
	private String description;

	private Integer lastUpdatedBy;

	private String lastUpdatedDatetime;

	private BigDecimal price;

	private Integer businessLineId;

	private Integer bandId;

	private Integer stayTypeId;
	
	private String bandName;
	
	private String stayTypeName;

	@JsonIgnore
	private BusinessLine businessLine;

	@JsonIgnore
	private Band band;

	@JsonIgnore
	private StayType stayType;

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

	public void setBusinessLineId(Integer businessLineId) {
		this.businessLineId = businessLineId;
	}

	
	public BusinessLine getBusinessLine() {
		return businessLine;
	}

	public void setBusinessLine(BusinessLine businessLine) {
		this.businessLine = businessLine;
	}


	public Integer getOffshorepriceId() {
		return offshorepriceId;
	}

	public void setOffshorepriceId(Integer offshorepriceId) {
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

	public Band getBand() {
		return band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public StayType getStayType() {
		return stayType;
	}

	public void setStayType(StayType stayType) {
		this.stayType = stayType;
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


}
