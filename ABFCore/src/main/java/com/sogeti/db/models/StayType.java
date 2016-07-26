package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the stay_type database table.
 * 
 */
@Entity
@Table(name="stay_type")
@NamedQuery(name="StayType.findAll", query="SELECT s FROM StayType s")
public class StayType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="stay_type_id", unique=true, nullable=false)
	private int stayTypeId;

	@Column(name="stay_type", length=45)
	private String stayType;

	//bi-directional many-to-one association to OffshorePrice
	@JsonIgnore
	@OneToMany(mappedBy="stayType")
	private List<OffshorePrice> offshorePrices;

	@Column(nullable=false)
	private int active;
	
	
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}
	
	public StayType() {
	}

	public int getStayTypeId() {
		return this.stayTypeId;
	}

	public void setStayTypeId(int stayTypeId) {
		this.stayTypeId = stayTypeId;
	}

	public String getStayType() {
		return this.stayType;
	}

	public void setStayType(String stayType) {
		this.stayType = stayType;
	}

	public List<OffshorePrice> getOffshorePrices() {
		return this.offshorePrices;
	}

	public void setOffshorePrices(List<OffshorePrice> offshorePrices) {
		this.offshorePrices = offshorePrices;
	}

	public OffshorePrice addOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().add(offshorePrice);
		offshorePrice.setStayType(this);

		return offshorePrice;
	}

	public OffshorePrice removeOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().remove(offshorePrice);
		offshorePrice.setStayType(null);

		return offshorePrice;
	}

}