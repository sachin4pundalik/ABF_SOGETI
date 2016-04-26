package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


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

	@Column(name="last_updated_by", length=256)
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_timestamp")
	private Date lastUpdatedTimestamp;

	@Column(name="role_type", length=45)
	private String roleType;

	//bi-directional one-to-one association to AmContract
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private AmContract amContract;

	//bi-directional one-to-one association to KtContract
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id", nullable=false, insertable=false, updatable=false)
	private KtContract ktContract;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedTimestamp() {
		return this.lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public String getRoleType() {
		return this.roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public AmContract getAmContract() {
		return this.amContract;
	}

	public void setAmContract(AmContract amContract) {
		this.amContract = amContract;
	}

	public KtContract getKtContract() {
		return this.ktContract;
	}

	public void setKtContract(KtContract ktContract) {
		this.ktContract = ktContract;
	}

}