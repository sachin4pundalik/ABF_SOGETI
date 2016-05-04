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

	@Column(length=500)
	private String description;

	@Column(name="last_updated_by")
	private Integer lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_updated_datetime")
	private Date lastUpdatedDatetime;

	@Column(precision=10, scale=2)
	private BigDecimal price;

	//bi-directional many-to-one association to AmContract
	@OneToMany(mappedBy="offshorePrice")
	private List<AmContract> amContracts;

	//bi-directional many-to-one association to KtContract
	@OneToMany(mappedBy="offshorePrice")
	private List<KtContract> ktContracts;

	//bi-directional many-to-one association to Band
	@ManyToOne
	@JoinColumn(name="band_id")
	private Band band;

	//bi-directional many-to-one association to BusinessLine
	@ManyToOne
	@JoinColumn(name="businessline_id")
	private BusinessLine businessLine;

	//bi-directional many-to-one association to StayType
	@ManyToOne
	@JoinColumn(name="stay_type_id")
	private StayType stayType;

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

	public Integer getLastUpdatedBy() {
		return this.lastUpdatedBy;
	}

	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getLastUpdatedDatetime() {
		return this.lastUpdatedDatetime;
	}

	public void setLastUpdatedDatetime(Date lastUpdatedDatetime) {
		this.lastUpdatedDatetime = lastUpdatedDatetime;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<AmContract> getAmContracts() {
		return this.amContracts;
	}

	public void setAmContracts(List<AmContract> amContracts) {
		this.amContracts = amContracts;
	}

	public AmContract addAmContract(AmContract amContract) {
		getAmContracts().add(amContract);
		amContract.setOffshorePrice(this);

		return amContract;
	}

	public AmContract removeAmContract(AmContract amContract) {
		getAmContracts().remove(amContract);
		amContract.setOffshorePrice(null);

		return amContract;
	}

	public List<KtContract> getKtContracts() {
		return this.ktContracts;
	}

	public void setKtContracts(List<KtContract> ktContracts) {
		this.ktContracts = ktContracts;
	}

	public KtContract addKtContract(KtContract ktContract) {
		getKtContracts().add(ktContract);
		ktContract.setOffshorePrice(this);

		return ktContract;
	}

	public KtContract removeKtContract(KtContract ktContract) {
		getKtContracts().remove(ktContract);
		ktContract.setOffshorePrice(null);

		return ktContract;
	}

	public Band getBand() {
		return this.band;
	}

	public void setBand(Band band) {
		this.band = band;
	}

	public BusinessLine getBusinessLine() {
		return this.businessLine;
	}

	public void setBusinessLine(BusinessLine businessLine) {
		this.businessLine = businessLine;
	}

	public StayType getStayType() {
		return this.stayType;
	}

	public void setStayType(StayType stayType) {
		this.stayType = stayType;
	}

}