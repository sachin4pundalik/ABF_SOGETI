package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;


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

	@Column(name="grade_type", length=256)
	private String gradeType;

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

}