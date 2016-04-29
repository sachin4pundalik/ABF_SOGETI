package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the approval_flow database table.
 * 
 */
@Entity
@Table(name="approval_flow")
@NamedQuery(name="ApprovalFlow.findAll", query="SELECT a FROM ApprovalFlow a")
public class ApprovalFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="approval_id", unique=true, nullable=false)
	private int approvalId;

	//bi-directional many-to-one association to Contract
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="login_id")
	private Login login;

	public ApprovalFlow() {
	}

	public int getApprovalId() {
		return this.approvalId;
	}

	public void setApprovalId(int approvalId) {
		this.approvalId = approvalId;
	}

	public Contract getContract() {
		return this.contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

}