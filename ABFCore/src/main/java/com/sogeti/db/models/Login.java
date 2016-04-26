package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
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
	@Column(name="last_login")
	private Date lastLogin;

	@Column(length=256)
	private String password;

	@Column(nullable=false)
	private int uid;

	@Column(name="user_name", length=256)
	private String userName;

	//bi-directional many-to-one association to ApproverFlow
	@OneToMany(mappedBy="login")
	private List<ApproverFlow> approverFlows;

	//bi-directional many-to-one association to UserType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_type_id")
	private UserType userType;

	public Login() {
	}

	public int getLoginId() {
		return this.loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUid() {
		return this.uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<ApproverFlow> getApproverFlows() {
		return this.approverFlows;
	}

	public void setApproverFlows(List<ApproverFlow> approverFlows) {
		this.approverFlows = approverFlows;
	}

	public ApproverFlow addApproverFlow(ApproverFlow approverFlow) {
		getApproverFlows().add(approverFlow);
		approverFlow.setLogin(this);

		return approverFlow;
	}

	public ApproverFlow removeApproverFlow(ApproverFlow approverFlow) {
		getApproverFlows().remove(approverFlow);
		approverFlow.setLogin(null);

		return approverFlow;
	}

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

}