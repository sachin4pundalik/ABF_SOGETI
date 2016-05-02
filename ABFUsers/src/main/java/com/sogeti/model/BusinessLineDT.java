package com.sogeti.model;

public class BusinessLineDT {
	private int businesslineId;

	private String businesslineName;
	
	private Integer resourceTypeId;
	
	private Integer skillId;

	public int getBusinesslineId() {
		return businesslineId;
	}

	public void setBusinesslineId(int businesslineId) {
		this.businesslineId = businesslineId;
	}

	public String getBusinesslineName() {
		return businesslineName;
	}

	public void setBusinesslineName(String businesslineName) {
		this.businesslineName = businesslineName;
	}

	public Integer getResourceTypeId() {
		return resourceTypeId;
	}

	public void setResourceTypeId(Integer resourceTypeId) {
		this.resourceTypeId = resourceTypeId;
	}

	public Integer getSkillId() {
		return skillId;
	}

	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}

}
