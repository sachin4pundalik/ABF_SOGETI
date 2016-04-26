package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_type database table.
 * 
 */
@Entity
@Table(name="user_type")
@NamedQuery(name="UserType.findAll", query="SELECT u FROM UserType u")
public class UserType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_type_id", unique=true, nullable=false)
	private int userTypeId;

	@Column(name="user_type", length=256)
	private String userType;

	//bi-directional many-to-one association to Login
	@OneToMany(mappedBy="userType")
	private List<Login> logins;

	public UserType() {
	}

	public int getUserTypeId() {
		return this.userTypeId;
	}

	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserType() {
		return this.userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<Login> getLogins() {
		return this.logins;
	}

	public void setLogins(List<Login> logins) {
		this.logins = logins;
	}

	public Login addLogin(Login login) {
		getLogins().add(login);
		login.setUserType(this);

		return login;
	}

	public Login removeLogin(Login login) {
		getLogins().remove(login);
		login.setUserType(null);

		return login;
	}

}