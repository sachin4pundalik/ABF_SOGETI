package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name="status")
@NamedQuery(name="Status.findAll", query="SELECT s FROM Status s")
public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="status_id", unique=true, nullable=false)
	private int statusId;

	@Column(length=256)
	private String description;

	@Column(name="status_name", length=256)
	private String statusName;

	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="status")
	private List<Contract> contracts;

	public Status() {
	}

	public int getStatusId() {
		return this.statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatusName() {
		return this.statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Contract addContract(Contract contract) {
		getContracts().add(contract);
		contract.setStatus(this);

		return contract;
	}

	public Contract removeContract(Contract contract) {
		getContracts().remove(contract);
		contract.setStatus(null);

		return contract;
	}

}