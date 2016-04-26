package com.sogeti.model;

import com.sogeti.xmlbeans.Months;

public class AMContractResourceBean {	
		
	private String id;	
	
	private String type;	
	
	private String bline;	
	
	private String Skill;	
	
	private String band;	
	
	private String role;	
	
	private String grade;	
	
	private String stay;	
	
	private String price;		
	
	private String onShorePriceId;
	
	private String offShorePriceId;
	
	private Months months;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBline() {
		return bline;
	}

	public void setBline(String bline) {
		this.bline = bline;
	}

	public String getSkill() {
		return Skill;
	}

	public void setSkill(String skill) {
		Skill = skill;
	}

	public String getBand() {
		return band;
	}

	public void setBand(String band) {
		this.band = band;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStay() {
		return stay;
	}

	public void setStay(String stay) {
		this.stay = stay;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Months getMonths() {
		return months;
	}

	public void setMonths(Months months) {
		this.months = months;
	}

	public String getOnShorePriceId() {
		return onShorePriceId;
	}

	public void setOnShorePriceId(String onShorePriceId) {
		this.onShorePriceId = onShorePriceId;
	}

	public String getOffShorePriceId() {
		return offShorePriceId;
	}

	public void setOffShorePriceId(String offShorePriceId) {
		this.offShorePriceId = offShorePriceId;
	}

}
