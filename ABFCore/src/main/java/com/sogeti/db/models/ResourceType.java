package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the resource_type database table.
 * 
 */
@Entity
@Table(name="resource_type")
@NamedQuery(name="ResourceType.findAll", query="SELECT r FROM ResourceType r")
public class ResourceType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resourcetype_id", unique=true, nullable=false)
	private int resourcetypeId;

	@Column(name="resource_type", length=45)
	private String resourceType;

	//bi-directional many-to-one association to BusinessLine
	@OneToMany(mappedBy="resourceType")
	private List<BusinessLine> businessLines;

	public ResourceType() {
	}

	public int getResourcetypeId() {
		return this.resourcetypeId;
	}

	public void setResourcetypeId(int resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public String getResourceType() {
		return this.resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public List<BusinessLine> getBusinessLines() {
		return this.businessLines;
	}

	public void setBusinessLines(List<BusinessLine> businessLines) {
		this.businessLines = businessLines;
	}

	public BusinessLine addBusinessLine(BusinessLine businessLine) {
		getBusinessLines().add(businessLine);
		businessLine.setResourceType(this);

		return businessLine;
	}

	public BusinessLine removeBusinessLine(BusinessLine businessLine) {
		getBusinessLines().remove(businessLine);
		businessLine.setResourceType(null);

		return businessLine;
	}

}