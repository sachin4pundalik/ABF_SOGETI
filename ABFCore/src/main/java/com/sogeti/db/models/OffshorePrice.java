package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the offshore_price database table.
 * 
 */
@Entity
@Table(name="offshore_price")
@NamedQuery(name="OffshorePrice.findAll", query="SELECT o FROM OffshorePrice o")
public class OffshorePrice implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="offshoreprice_id", unique=true, nullable=false)
	private int offshorepriceId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="band_id")
	private Band bandId;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="businessline_id")
	private BusinessLine businesslineId;

	@Column(length=256)
	private String description;

	@Column(name="last_updated_by", length=45)
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_timestamp")
	private Date lastUpdatedTimestamp;

	@Column(precision=10, scale=2)
	private BigDecimal price;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="stay_type_id")
	private StayType stayTypeId;
	
	//bi-directional many-to-one association to AmContract
	@OneToMany(mappedBy="contract")
	private List<AMContractResource> amContractResources;

	public OffshorePrice() {
	}

	public int getOffshorepriceId() {
		return this.offshorepriceId;
	}

	public void setOffshorepriceId(int offshorepriceId) {
		this.offshorepriceId = offshorepriceId;
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

	public Band getBandId() {
		return bandId;
	}

	public void setBandId(Band bandId) {
		this.bandId = bandId;
	}

	public BusinessLine getBusinesslineId() {
		return businesslineId;
	}

	public void setBusinesslineId(BusinessLine businesslineId) {
		this.businesslineId = businesslineId;
	}

	public StayType getStayTypeId() {
		return stayTypeId;
	}

	public void setStayTypeId(StayType stayTypeId) {
		this.stayTypeId = stayTypeId;
	}

}