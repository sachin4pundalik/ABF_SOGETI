package com.sogeti.db.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the am_contract database table.
 * 
 */
@Entity
@Table(name="am_contract_resource")
@NamedQuery(name="AMContractResource.findAll", query="SELECT resource FROM AMContractResource resource")
public class AMContractResource {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="am_id", unique=true, nullable=false)
	private int amId;
	
	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id")
	private Contract contract;
	
	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="onshore_price_id")
	private OnshorePrice onShorePriceId;
	
	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="offshore_price_id")
	private OffshorePrice offShorePriceId;
	
	@Column(name = "details_xml", length = 65535, columnDefinition="Text")
	private String detailsXml;
	
	@Transient
	private List<String> months;
	
	@Transient
	private String id;
	
	@Transient
	private String type;
	
	@Transient
	private String bline;
	
	@Transient
	private String Skill;
	
	@Transient
	private String band;
	
	@Transient
	private String role;
	
	@Transient
	private String grade;
	
	@Transient
	private String stay;
	
	@Transient
	private String price;
	
	

	public int getAmId() {
		return amId;
	}

	public void setAmId(int amId) {
		this.amId = amId;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public OnshorePrice getOnShorePriceId() {
		return onShorePriceId;
	}

	public void setOnShorePriceId(OnshorePrice onShorePriceId) {
		this.onShorePriceId = onShorePriceId;
	}

	public OffshorePrice getOffShorePriceId() {
		return offShorePriceId;
	}

	public void setOffShorePriceId(OffshorePrice offShorePriceId) {
		this.offShorePriceId = offShorePriceId;
	}

	public String getDetailsXml() {
		return detailsXml;
	}

	public void setDetailsXml(String detailsXml) {
		this.detailsXml = detailsXml;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBline() {
		return bline;
	}

	public void setBline(String bline) {
		this.bline = bline;
	}

	public String getSkill() {
		return Skill;
	}

	public void setSkill(String skill) {
		Skill = skill;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}	

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
	}

	

}
