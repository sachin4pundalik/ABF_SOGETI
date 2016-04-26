package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fixed database table.
 * 
 */
@Entity
@Table(name="fixed")
@NamedQuery(name="Fixed.findAll", query="SELECT f FROM Fixed f")
public class Fixed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fixedcost_id", unique=true, nullable=false)
	private int fixedcostId;

	@Column(length=256)
	private String description;

	@Column(length=256)
	private String price;

	//bi-directional one-to-one association to Contract
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional many-to-one association to FixedCostName
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fixedcost_id", nullable=false, insertable=false, updatable=false)
	private FixedCostName fixedCostName;

	public Fixed() {
	}

	public int getFixedcostId() {
		return this.fixedcostId;
	}

	public void setFixedcostId(int fixedcostId) {
		this.fixedcostId = fixedcostId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public FixedCostName getFixedCostName() {
		return this.fixedCostName;
	}

	public void setFixedCostName(FixedCostName fixedCostName) {
		this.fixedCostName = fixedCostName;
	}

}