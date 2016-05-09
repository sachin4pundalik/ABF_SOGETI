package com.sogeti.db.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the kt_contract database table.
 * 
 */
@Entity
@Table(name="kt_contract")
@NamedQuery(name="KtContract.findAll", query="SELECT k FROM KtContract k")
public class KtContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kt_contract_id", unique=true, nullable=false)
	private int ktContractId;

	@Lob
	@Column(name="details_xml")
	private String detailsXml;

	//bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional many-to-one association to OffshorePrice
	@ManyToOne
	@JoinColumn(name="offshore_price_id")
	private OffshorePrice offshorePrice;

	//bi-directional many-to-one association to OnshorePrice
	@ManyToOne
	@JoinColumn(name="onshore_price_id")
	private OnshorePrice onshorePrice;
	
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

	public KtContract() {
	}

	public int getKtContractId() {
		return this.ktContractId;
	}

	public void setKtContractId(int ktContractId) {
		this.ktContractId = ktContractId;
	}

	public String getDetailsXml() {
		return this.detailsXml;
	}

	public void setDetailsXml(String detailsXml) {
		this.detailsXml = detailsXml;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public OffshorePrice getOffshorePrice() {
		return this.offshorePrice;
	}

	public void setOffshorePrice(OffshorePrice offshorePrice) {
		this.offshorePrice = offshorePrice;
	}

	public OnshorePrice getOnshorePrice() {
		return this.onshorePrice;
	}

	public void setOnshorePrice(OnshorePrice onshorePrice) {
		this.onshorePrice = onshorePrice;
	}

	public List<String> getMonths() {
		return months;
	}

	public void setMonths(List<String> months) {
		this.months = months;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}