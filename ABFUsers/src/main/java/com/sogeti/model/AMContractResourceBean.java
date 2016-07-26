package com.sogeti.model;

import com.sogeti.xmlbeans.Months;

public class AMContractResourceBean {	
		
	private int amContractResourceId;
	
	private int contractId;
	
	private ResourceTypeDT resourceType;
	
	private BusinessLineDT businessLine;
	
	private SkillDT skill;
	
	private BandDT band;
	
	private GradeDT grade;
	
	private RoleDT role;
	
	private StayTypeDT stayType;
			
	private float price;		
			
	private int onShorePrice;
	
	private int offShorePrice;
	
	private Months months;

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

	public int getAmContractResourceId() {
		return amContractResourceId;
	}

	public void setAmContractResourceId(int amContractResourceId) {
		this.amContractResourceId = amContractResourceId;
	}

	public ResourceTypeDT getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceTypeDT resourceType) {
		this.resourceType = resourceType;
	}

	public BusinessLineDT getBusinessLine() {
		return businessLine;
	}

	public void setBusinessLine(BusinessLineDT businessLine) {
		this.businessLine = businessLine;
	}

	public SkillDT getSkill() {
		return skill;
	}

	public void setSkill(SkillDT skill) {
		this.skill = skill;
	}

	public BandDT getBand() {
		return band;
	}

	public void setBand(BandDT band) {
		this.band = band;
	}

	public GradeDT getGrade() {
		return grade;
	}

	public void setGrade(GradeDT grade) {
		this.grade = grade;
	}

	public RoleDT getRole() {
		return role;
	}

	public void setRole(RoleDT role) {
		this.role = role;
	}

	public StayTypeDT getStayType() {
		return stayType;
	}

	public void setStayType(StayTypeDT stayType) {
		this.stayType = stayType;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getOnShorePrice() {
		return onShorePrice;
	}

	public void setOnShorePrice(int onShorePrice) {
		this.onShorePrice = onShorePrice;
	}

	public int getOffShorePrice() {
		return offShorePrice;
	}

	public void setOffShorePrice(int offShorePrice) {
		this.offShorePrice = offShorePrice;
	}

	public Months getMonths() {
		return months;
	}

	public void setMonths(Months months) {
		this.months = months;
	}

	public int getContractId() {
		return contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	
}
