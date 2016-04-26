package com.sogeti.db.models;

import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the contract database table.
 * 
 */

@Entity
@Table(name="contract")
@NamedQuery(name="Contract.findAll", query="SELECT c FROM Contract c")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contract implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="contract_id", unique=true, nullable=false)
	private int contractId;

	@Column(length=256)
	private String comments;

	@Column(name="company_name", length=256)
	private String companyName;

	@Column(name="contract_created_by", length=256)
	private String contractCreatedBy;

	@Temporal(TemporalType.DATE)
	@Column(name="contract_end_date")
	private Date contractEndDate;

	@Temporal(TemporalType.DATE)
	@Column(name="contract_start_date")
	private Date contractStartDate;

	@Column(name="customer_name", length=256)
	private String customerName;

	@Column(name="login_id")
	private int loginId;

	//bi-directional many-to-one association to AmContract
	@JsonIgnore
	@OneToMany(mappedBy="contract")
	private List<AmContract> amContracts;

	//bi-directional one-to-one association to ApproverFlow
	@JsonIgnore
	@OneToOne(mappedBy="contract", fetch=FetchType.LAZY)
	private ApproverFlow approverFlow;

	//bi-directional many-to-one association to Status
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id")
	@JsonIgnore
	private Status status;

	//bi-directional one-to-one association to Fixed
	@JsonIgnore
	@OneToOne(mappedBy="contract", fetch=FetchType.LAZY)
	private Fixed fixed;

	//bi-directional many-to-one association to KtContract
	@JsonIgnore
	@OneToMany(mappedBy="contract")
	private List<KtContract> ktContracts;

	//bi-directional many-to-one association to RiskComment
	@JsonIgnore
	@OneToMany(mappedBy="contract")
	private List<RiskComment> riskComments;

	public Contract() {
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

	public String getContractCreatedBy() {
		return this.contractCreatedBy;
	}

	public void setContractCreatedBy(String contractCreatedBy) {
		this.contractCreatedBy = contractCreatedBy;
	}

	public Date getContractEndDate() {
		return this.contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getContractStartDate() {
		return this.contractStartDate;
	}

	public void setContractStartDate(Date contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public int getContractId() {
		return this.contractId;
	}

	public void setContractId(int contractId) {
		this.contractId = contractId;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
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

	public ApproverFlow getApproverFlow() {
		return this.approverFlow;
	}

	public void setApproverFlow(ApproverFlow approverFlow) {
		this.approverFlow = approverFlow;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Fixed getFixed() {
		return this.fixed;
	}

	public void setFixed(Fixed fixed) {
		this.fixed = fixed;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contract [contractId=");
		builder.append(contractId);
		
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", contractCreatedBy=");
		builder.append(contractCreatedBy);
		builder.append(", contractEndDate=");
		builder.append(contractEndDate);
		builder.append(", contractStartDate=");
		builder.append(contractStartDate);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", loginId=");
		builder.append(loginId);
		builder.append(", amContracts=");
		builder.append(amContracts);
		builder.append(", approverFlow=");
		builder.append(approverFlow);
		builder.append(", status=");
		builder.append(status);
		builder.append(", fixed=");
		builder.append(fixed);
		builder.append(", ktContracts=");
		builder.append(ktContracts);
		builder.append(", riskComments=");
		builder.append(riskComments);
		builder.append("]");
		return builder.toString();
	}
	

}