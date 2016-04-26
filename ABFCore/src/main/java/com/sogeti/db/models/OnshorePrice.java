package com.sogeti.db.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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


/**
 * The persistent class for the onshore_price database table.
 * 
 */
@Entity
@Table(name="onshore_price")
@NamedQuery(name="OnshorePrice.findAll", query="SELECT o FROM OnshorePrice o")
public class OnshorePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="onshoreprice_id", unique=true, nullable=false)
	private int onshorepriceId;

	//@Column(name="businessline_id", nullable=false)
	//bi-directional many-to-one association to Contract
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="businessline_id")
	private BusinessLine businesslineId;

	@Column(length=256)
	private String description;

	//@Column(name="grade_id", nullable=false)
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="grade_id")
	private Grade gradeId;

	@Column(name="last_updated_by", length=45)
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_timestamp")
	private Date lastUpdatedTimestamp;

	@Column(nullable=false, precision=10, scale=2)
	private BigDecimal price;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_id")
	private Role roleId;
	
	//bi-directional many-to-one association to AmContract
	@OneToMany(mappedBy="contract")
	private List<AMContractResource> amContractResources;

	public OnshorePrice() {
	}

	public int getOnshorepriceId() {
		return this.onshorepriceId;
	}

	public void setOnshorepriceId(int onshorepriceId) {
		this.onshorepriceId = onshorepriceId;
	}

	

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	

	public List<AMContractResource> getAmContractResources() {
		return amContractResources;
	}

	public void setAmContractResources(List<AMContractResource> amContractResources) {
		this.amContractResources = amContractResources;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BusinessLine getBusinesslineId() {
		return businesslineId;
	}

	public void setBusinesslineId(BusinessLine businesslineId) {
		this.businesslineId = businesslineId;
	}

	public Grade getGradeId() {
		return gradeId;
	}

	public void setGradeId(Grade gradeId) {
		this.gradeId = gradeId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

}