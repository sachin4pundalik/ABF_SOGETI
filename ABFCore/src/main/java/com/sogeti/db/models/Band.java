package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


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

	@Column(name="band_name", length=20)
	private String bandName;

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

}