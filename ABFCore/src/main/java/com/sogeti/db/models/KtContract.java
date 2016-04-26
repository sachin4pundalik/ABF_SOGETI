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
	@Column(name="kt_id", unique=true, nullable=false)
	private int ktId;

	@Column(length=256)
	private String duration;

	@Column(name="role_id")
	private int roleId;

	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional one-to-one association to Role
	@OneToOne(mappedBy="ktContract", fetch=FetchType.LAZY)
	private Role role;

	public KtContract() {
	}

	public int getKtId() {
		return this.ktId;
	}

	public void setKtId(int ktId) {
		this.ktId = ktId;
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