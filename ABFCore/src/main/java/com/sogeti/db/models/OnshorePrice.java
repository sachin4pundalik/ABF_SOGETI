package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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
	@OneToMany(mappedBy="onshorePrice")
	private List<AmContract> amContracts;

	//bi-directional many-to-one association to KtContract
	@OneToMany(mappedBy="onshorePrice")
	private List<KtContract> ktContracts;

	//bi-directional many-to-one association to BusinessLine
	@ManyToOne
	@JoinColumn(name="businessline_id")
	private BusinessLine businessLine;

	//bi-directional many-to-one association to Grade
	@ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;

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
		amContract.setOnshorePrice(this);

		return amContract;
	}

	public AmContract removeAmContract(AmContract amContract) {
		getAmContracts().remove(amContract);
		amContract.setOnshorePrice(null);

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
		ktContract.setOnshorePrice(this);

		return ktContract;
	}

	public KtContract removeKtContract(KtContract ktContract) {
		getKtContracts().remove(ktContract);
		ktContract.setOnshorePrice(null);

		return ktContract;
	}

	public BusinessLine getBusinessLine() {
		return this.businessLine;
	}

	public void setBusinessLine(BusinessLine businessLine) {
		this.businessLine = businessLine;
	}

	public Grade getGrade() {
		return this.grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}