package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="login_id", unique=true, nullable=false)
	private int loginId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_login_datetime")
	private Date lastLoginDatetime;

	@Column(length=100)
	@JsonIgnore
	private String password;

	@Column(name="user_name", length=100)
	private String userName;

	//bi-directional many-to-one association to ApprovalFlow
	@OneToMany(mappedBy="login")
	private List<ApprovalFlow> approvalFlows;

	//bi-directional many-to-one association to Contract
	@OneToMany(mappedBy="login")
	private List<Contract> contracts;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="user_role_id")
	@JsonIgnore
	private UserRole userRole;
	
	@Transient
	private int roleId;
	
	@Transient
	private String role;
	
	@Column(name="first_name", length=100)
	private String firstName;
	
	@Column(name="last_name", length=100)
	private String lastName;

	@Column(name="active", nullable=false)
	private int active;
	
	public Login() {
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public Date getLastLoginDatetime() {
		return this.lastLoginDatetime;
	}

	public void setLastLoginDatetime(Date lastLoginDatetime) {
		this.lastLoginDatetime = lastLoginDatetime;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ApprovalFlow> getApprovalFlows() {
		return this.approvalFlows;
	}

	public void setApprovalFlows(List<ApprovalFlow> approvalFlows) {
		this.approvalFlows = approvalFlows;
	}

	public ApprovalFlow addApprovalFlow(ApprovalFlow approvalFlow) {
		getApprovalFlows().add(approvalFlow);
		approvalFlow.setLogin(this);

		return approvalFlow;
	}

	public ApprovalFlow removeApprovalFlow(ApprovalFlow approvalFlow) {
		getApprovalFlows().remove(approvalFlow);
		approvalFlow.setLogin(null);

		return approvalFlow;
	}

	public List<Contract> getContracts() {
		return this.contracts;
	}

	public void setContracts(List<Contract> contracts) {
		this.contracts = contracts;
	}

	public Contract addContract(Contract contract) {
		getContracts().add(contract);
		contract.setLogin(this);

		return contract;
	}

	public Contract removeContract(Contract contract) {
		getContracts().remove(contract);
		contract.setLogin(null);

		return contract;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

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

	
}