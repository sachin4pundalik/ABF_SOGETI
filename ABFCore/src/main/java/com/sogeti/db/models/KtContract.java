package com.sogeti.db.models;

import java.io.Serializable;
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

}