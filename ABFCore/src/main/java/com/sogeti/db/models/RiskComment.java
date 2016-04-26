package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the risk_comments database table.
 * 
 */
@Entity
@Table(name="risk_comments")
@NamedQuery(name="RiskComment.findAll", query="SELECT r FROM RiskComment r")
public class RiskComment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="risk_id", unique=true, nullable=false)
	private int riskId;

	@Column(length=256)
	private String description;

	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id")
	private Contract contract;

	public RiskComment() {
	}

	public int getRiskId() {
		return this.riskId;
	}

	public void setRiskId(int riskId) {
		this.riskId = riskId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}