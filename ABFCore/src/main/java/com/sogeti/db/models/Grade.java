package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the grade database table.
 * 
 */
@Entity
@Table(name="grade")
@NamedQuery(name="Grade.findAll", query="SELECT g FROM Grade g")
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grade_id", unique=true, nullable=false)
	private int gradeId;

	@Column(name="grade_type", length=45)
	private String gradeType;

	//bi-directional many-to-one association to OnshorePrice
	@JsonIgnore
	@OneToMany(mappedBy="grade")
	private List<OnshorePrice> onshorePrices;

	public Grade() {
	}

	public int getGradeId() {
		return this.gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeType() {
		return this.gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	public List<OnshorePrice> getOnshorePrices() {
		return this.onshorePrices;
	}

	public void setOnshorePrices(List<OnshorePrice> onshorePrices) {
		this.onshorePrices = onshorePrices;
	}

	public OnshorePrice addOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().add(onshorePrice);
		onshorePrice.setGrade(this);

		return onshorePrice;
	}

	public OnshorePrice removeOnshorePrice(OnshorePrice onshorePrice) {
		getOnshorePrices().remove(onshorePrice);
		onshorePrice.setGrade(null);

		return onshorePrice;
	}

}