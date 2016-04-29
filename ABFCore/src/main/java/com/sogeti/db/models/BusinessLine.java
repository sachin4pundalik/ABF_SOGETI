package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


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

	@Column(name="businessline_name", length=100)
	private String businesslineName;

	//bi-directional many-to-one association to ResourceType
	@ManyToOne
	@JoinColumn(name="resourcetype_id")
	private ResourceType resourceType;

	//bi-directional many-to-one association to Skill
	@ManyToOne
	@JoinColumn(name="skill_id")
	private Skill skill;

	//bi-directional many-to-one association to OffshorePrice
	@OneToMany(mappedBy="businessLine")
	private List<OffshorePrice> offshorePrices;

	//bi-directional many-to-one association to OnshorePrice
	@OneToMany(mappedBy="businessLine")
	private List<OnshorePrice> onshorePrices;

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

	public List<OffshorePrice> getOffshorePrices() {
		return this.offshorePrices;
	}

	public void setOffshorePrices(List<OffshorePrice> offshorePrices) {
		this.offshorePrices = offshorePrices;
	}

	public OffshorePrice addOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().add(offshorePrice);
		offshorePrice.setBusinessLine(this);

		return offshorePrice;
	}

	public OffshorePrice removeOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().remove(offshorePrice);
		offshorePrice.setBusinessLine(null);

		return offshorePrice;
	}

	public List<OnshorePrice> getOnshorePrices() {
		return this.onshorePrices;
	}

	public void setOnshorePrices(List<OnshorePrice> onshorePrices) {
		this.onshorePrices = onshorePrices;
	}

	public OnshorePrice addOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().add(onshorePrice);
		onshorePrice.setBusinessLine(this);

		return onshorePrice;
	}

	public OnshorePrice removeOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().remove(onshorePrice);
		onshorePrice.setBusinessLine(null);

		return onshorePrice;
	}

}