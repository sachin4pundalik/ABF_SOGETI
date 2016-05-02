package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id", unique=true, nullable=false)
	private int roleId;

	@Column(name="role_type", length=45)
	private String roleType;

	//bi-directional many-to-one association to OnshorePrice
	@JsonIgnore
	@OneToMany(mappedBy="role")
	private List<OnshorePrice> onshorePrices;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public List<OnshorePrice> getOnshorePrices() {
		return this.onshorePrices;
	}

	public void setOnshorePrices(List<OnshorePrice> onshorePrices) {
		this.onshorePrices = onshorePrices;
	}

	public OnshorePrice addOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().add(onshorePrice);
		onshorePrice.setRole(this);

		return onshorePrice;
	}

	public OnshorePrice removeOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().remove(onshorePrice);
		onshorePrice.setRole(null);

		return onshorePrice;
	}

}