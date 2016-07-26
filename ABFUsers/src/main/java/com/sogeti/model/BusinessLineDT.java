package com.sogeti.model;

public class BusinessLineDT {

	private int businesslineId;
	private String businesslineName;
	private Integer resourceTypeId;
	private Integer skillId;
	private String resourceType;
	private String skillName;
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

	/**
	 * @return the resourceType
	 */
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * @param resourceType the resourceType to set
	 */
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * @return the skillName
	 */
	public String getSkillName() {
		return skillName;
	}

	/**
	 * @param skillName the skillName to set
	 */
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessLineDT [businesslineId=");
		builder.append(businesslineId);
		builder.append(", businesslineName=");
		builder.append(businesslineName);
		builder.append(", resourceTypeId=");
		builder.append(resourceTypeId);
		builder.append(", skillId=");
		builder.append(skillId);
		builder.append(", resourceType=");
		builder.append(resourceType);
		builder.append(", skillName=");
		builder.append(skillName);
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}

	
}
