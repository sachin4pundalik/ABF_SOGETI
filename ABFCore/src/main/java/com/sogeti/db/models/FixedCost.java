package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the fixed_cost database table.
 * 
 */
@Entity
@Table(name="fixed_cost")
@NamedQuery(name="FixedCost.findAll", query="SELECT f FROM FixedCost f")
public class FixedCost implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fixedcost_id", unique=true, nullable=false)
	private int fixedcostId;

	@Column(name="fixedcost_description", length=500)
	private String fixedcostDescription;

	@Column(name="fixedcost_name", length=100)
	private String fixedcostName;

	//bi-directional many-to-one association to FixedContract
	@OneToMany(mappedBy="fixedCost")
	private List<FixedContract> fixedContracts;

	public FixedCost() {
	}

	public int getFixedcostId() {
		return this.fixedcostId;
	}

	public void setFixedcostId(int fixedcostId) {
		this.fixedcostId = fixedcostId;
	}

	public String getFixedcostDescription() {
		return this.fixedcostDescription;
	}

	public void setFixedcostDescription(String fixedcostDescription) {
		this.fixedcostDescription = fixedcostDescription;
	}

	public String getFixedcostName() {
		return this.fixedcostName;
	}

	public void setFixedcostName(String fixedcostName) {
		this.fixedcostName = fixedcostName;
	}

	public List<FixedContract> getFixedContracts() {
		return this.fixedContracts;
	}

	public void setFixedContracts(List<FixedContract> fixedContracts) {
		this.fixedContracts = fixedContracts;
	}

	public FixedContract addFixedContract(FixedContract fixedContract) {
		getFixedContracts().add(fixedContract);
		fixedContract.setFixedCost(this);

		return fixedContract;
	}

	public FixedContract removeFixedContract(FixedContract fixedContract) {
		getFixedContracts().remove(fixedContract);
		fixedContract.setFixedCost(null);

		return fixedContract;
	}

}