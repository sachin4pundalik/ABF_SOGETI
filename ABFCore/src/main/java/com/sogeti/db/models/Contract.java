package com.sogeti.db.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * The persistent class for the contract database table.
 * 
 */
@Entity
@Table(name="contract")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contract_id", unique=true, nullable=false)
	private int contractId;

	@Column(length=500)
	private String comments;

	@Column(name="company_name", length=100)
	private String companyName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="contract_created_datetime")
	private Date contractCreatedDatetime;

	@Temporal(TemporalType.DATE)
	@Column(name="contract_end_date")
	private Date contractEndDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="contract_modified_date_time")
	private Date contractModifiedDateTime;

	@Column(name="contract_name", length=100)
	private String contractName;

	@Temporal(TemporalType.DATE)
	@Column(name="contract_start_date")
	private Date contractStartDate;

	@Column(name="customer_name", length=100)
	private String customerName;

	//bi-directional many-to-one association to AmContract
	@OneToMany(mappedBy="contract")
	@JsonIgnore
	private List<AmContract> amContracts;

	//bi-directional many-to-one association to ApprovalFlow
	@OneToMany(mappedBy="contract")
	@JsonIgnore
	private List<ApprovalFlow> approvalFlows;

	//bi-directional many-to-one association to Login
	@ManyToOne
	@JoinColumn(name="login_id")
	@JsonIgnore
	private Login login;

	//bi-directional many-to-one association to Status
	@ManyToOne
	@JoinColumn(name="status_id")
	@JsonIgnore
	private Status status;

	//bi-directional many-to-one association to FixedContract
	@OneToMany(mappedBy="contract")
	@JsonIgnore
	private List<FixedContract> fixedContracts;

	//bi-directional many-to-one association to KtContract
	@OneToMany(mappedBy="contract")
	@JsonIgnore
	private List<KtContract> ktContracts;

	//bi-directional many-to-one association to RiskComment
	@OneToMany(mappedBy="contract")
	@JsonIgnore
	private List<RiskComment> riskComments;

	public Contract() {
	}

	public int getContractId() {
		return this.contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getContractCreatedDatetime() {
		return this.contractCreatedDatetime;
	}

	public void setContractCreatedDatetime(Date contractCreatedDatetime) {
		this.contractCreatedDatetime = contractCreatedDatetime;
	}

	public Date getContractEndDate() {
		return this.contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getContractModifiedDateTime() {
		return this.contractModifiedDateTime;
	}

	public void setContractModifiedDateTime(Date contractModifiedDateTime) {
		this.contractModifiedDateTime = contractModifiedDateTime;
	}

	public String getContractName() {
		return this.contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Date getContractStartDate() {
		return this.contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<AmContract> getAmContracts() {
		return this.amContracts;
	}

	public void setAmContracts(List<AmContract> amContracts) {
		this.amContracts = amContracts;
	}

	public AmContract addAmContract(AmContract amContract) {
		getAmContracts().add(amContract);
		amContract.setContract(this);

		return amContract;
	}

	public AmContract removeAmContract(AmContract amContract) {
		getAmContracts().remove(amContract);
		amContract.setContract(null);

		return amContract;
	}

	public List<ApprovalFlow> getApprovalFlows() {
		return this.approvalFlows;
	}

	public void setApprovalFlows(List<ApprovalFlow> approvalFlows) {
		this.approvalFlows = approvalFlows;
	}

	public ApprovalFlow addApprovalFlow(ApprovalFlow approvalFlow) {
		getApprovalFlows().add(approvalFlow);
		approvalFlow.setContract(this);

		return approvalFlow;
	}

	public ApprovalFlow removeApprovalFlow(ApprovalFlow approvalFlow) {
		getApprovalFlows().remove(approvalFlow);
		approvalFlow.setContract(null);

		return approvalFlow;
	}

	public Login getLogin() {
		return this.login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<FixedContract> getFixedContracts() {
		return this.fixedContracts;
	}

	public void setFixedContracts(List<FixedContract> fixedContracts) {
		this.fixedContracts = fixedContracts;
	}

	public FixedContract addFixedContract(FixedContract fixedContract) {
		getFixedContracts().add(fixedContract);
		fixedContract.setContract(this);

		return fixedContract;
	}

	public FixedContract removeFixedContract(FixedContract fixedContract) {
		getFixedContracts().remove(fixedContract);
		fixedContract.setContract(null);

		return fixedContract;
	}

	public List<KtContract> getKtContracts() {
		return this.ktContracts;
	}

	public void setKtContracts(List<KtContract> ktContracts) {
		this.ktContracts = ktContracts;
	}

	public KtContract addKtContract(KtContract ktContract) {
		getKtContracts().add(ktContract);
		ktContract.setContract(this);

		return ktContract;
	}

	public KtContract removeKtContract(KtContract ktContract) {
		getKtContracts().remove(ktContract);
		ktContract.setContract(null);

		return ktContract;
	}

	public List<RiskComment> getRiskComments() {
		return this.riskComments;
	}

	public void setRiskComments(List<RiskComment> riskComments) {
		this.riskComments = riskComments;
	}

	public RiskComment addRiskComment(RiskComment riskComment) {
		getRiskComments().add(riskComment);
		riskComment.setContract(this);

		return riskComment;
	}

	public RiskComment removeRiskComment(RiskComment riskComment) {
		getRiskComments().remove(riskComment);
		riskComment.setContract(null);

		return riskComment;
	}

}