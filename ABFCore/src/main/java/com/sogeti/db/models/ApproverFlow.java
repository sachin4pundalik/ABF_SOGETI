package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the approver_flow database table.
 * 
 */
@Entity
@Table(name="approver_flow")
@NamedQuery(name="ApproverFlow.findAll", query="SELECT a FROM ApproverFlow a")
public class ApproverFlow implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="approval_id", unique=true, nullable=false)
	private int approvalId;

	//bi-directional one-to-one association to Contract
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="contract_id", nullable=false)
	private Contract contract;

	//bi-directional many-to-one association to Login
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="login_id")
	private Login login;

	public ApproverFlow() {
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