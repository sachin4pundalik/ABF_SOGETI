package com.sogeti.db.models;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the skill database table.
 * 
 */
@Entity
@Table(name="skill")
@NamedQuery(name="Skill.findAll", query="SELECT s FROM Skill s")
public class Skill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="skill_id", unique=true, nullable=false)
	private int skillId;

	@Column(name="skill_name", length=45)
	private String skillName;

	//bi-directional many-to-one association to BusinessLine
	@OneToMany(mappedBy="skill")
	private List<BusinessLine> businessLines;

	public Skill() {
	}

	public int getSkillId() {
		return this.skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return this.skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public List<BusinessLine> getBusinessLines() {
		return this.businessLines;
	}

	public void setBusinessLines(List<BusinessLine> businessLines) {
		this.businessLines = businessLines;
	}

	public BusinessLine addBusinessLine(BusinessLine businessLine) {
		getBusinessLines().add(businessLine);
		businessLine.setSkill(this);

		return businessLine;
	}

	public BusinessLine removeBusinessLine(BusinessLine businessLine) {
		getBusinessLines().remove(businessLine);
		businessLine.setSkill(null);

		return businessLine;
	}

}