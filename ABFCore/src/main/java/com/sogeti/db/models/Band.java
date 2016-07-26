package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the band database table.
 * 
 */
@Entity
@Table(name="band")
@NamedQuery(name="Band.findAll", query="SELECT b FROM Band b")
public class Band implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="band_id", unique=true, nullable=false)
	private int bandId;

	@Column(name="band_name", length=45)
	private String bandName;

	//bi-directional many-to-one association to OffshorePrice
	@JsonIgnore
	@OneToMany(mappedBy="band")
	private List<OffshorePrice> offshorePrices;

	@Column(nullable=false)
	private int active;
	
	
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
	
	public Band() {
	}

	public int getBandId() {
		return this.bandId;
	}

	public void setBandId(int bandId) {
		this.bandId = bandId;
	}

	public String getBandName() {
		return this.bandName;
	}

	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

	public List<OffshorePrice> getOffshorePrices() {
		return this.offshorePrices;
	}

	public void setOffshorePrices(List<OffshorePrice> offshorePrices) {
		this.offshorePrices = offshorePrices;
	}

	public OffshorePrice addOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().add(offshorePrice);
		offshorePrice.setBand(this);

		return offshorePrice;
	}

	public OffshorePrice removeOffshorePrice(OffshorePrice offshorePrice) {
		getOffshorePrices().remove(offshorePrice);
		offshorePrice.setBand(null);

		return offshorePrice;
	}

}