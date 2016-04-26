package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the business_line database table.
 * 
 */
@Entity
@Table(name="business_line")
@NamedQuery(name="BusinessLine.findAll", query="SELECT b FROM BusinessLine b")
public class BusinessLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="businessline_id", unique=true, nullable=false)
	private int businesslineId;

	@Column(name="businessline_name", length=150)
	private String businesslineName;

	//bi-directional many-to-one association to ResourceType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="resourcetype_id", nullable=false)
	private ResourceType resourceType;

	//bi-directional many-to-one association to Skill
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="skill_id")
	private Skill skill;

	public BusinessLine() {
	}

	public int getBusinesslineId() {
		return this.businesslineId;
	}

	public void setBusinesslineId(int businesslineId) {
		this.businesslineId = businesslineId;
	}

	public String getBusinesslineName() {
		return this.businesslineName;
	}

	public void setBusinesslineName(String businesslineName) {
		this.businesslineName = businesslineName;
	}

	public ResourceType getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Skill getSkill() {
		return this.skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}