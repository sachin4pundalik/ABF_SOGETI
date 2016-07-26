package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the fixed_contract database table.
 * 
 */
@Entity
@Table(name="fixed_contract")
@NamedQuery(name="FixedContract.findAll", query="SELECT f FROM FixedContract f")
public class FixedContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fixedcontract_id", unique=true, nullable=false)
	private int fixedcontractId;

	@Column(length=100)
	private String description;

	@Column(precision=10, scale=2)
	private BigDecimal price;

	//bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional many-to-one association to FixedCost
	@ManyToOne
	@JoinColumn(name="fixed_cost_id")
	private FixedCost fixedCost;

	@Column(nullable=false)
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
	
	public FixedContract() {
	}

	public int getFixedcontractId() {
		return this.fixedcontractId;
	}

	public void setFixedcontractId(int fixedcontractId) {
		this.fixedcontractId = fixedcontractId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public FixedCost getFixedCost() {
		return this.fixedCost;
	}

	public void setFixedCost(FixedCost fixedCost) {
		this.fixedCost = fixedCost;
	}

}