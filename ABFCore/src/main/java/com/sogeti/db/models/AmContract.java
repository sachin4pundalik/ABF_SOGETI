package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the am_contract database table.
 * 
 */
@Entity
@Table(name="am_contract")
@NamedQuery(name="AmContract.findAll", query="SELECT a FROM AmContract a")
public class AmContract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="am_id", unique=true, nullable=false)
	private int amId;

	@Column(length=256)
	private String duration;

	@Column(name="role_id", unique=true)
	private int roleId;

	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional one-to-one association to Role
	@OneToOne(mappedBy="amContract", fetch=FetchType.LAZY)
	private Role role;

	public AmContract() {
	}

	public int getAmId() {
		return this.amId;
	}

	public void setAmId(int amId) {
		this.amId = amId;
	}

	public String getDuration() {
		return this.duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}