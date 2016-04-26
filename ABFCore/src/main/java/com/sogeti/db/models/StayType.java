package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


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

}