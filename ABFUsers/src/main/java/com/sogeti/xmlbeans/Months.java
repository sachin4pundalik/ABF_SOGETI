package com.sogeti.xmlbeans;

import java.util.List;

public class Months {
	
	private List<Month> months;
	
	public Months(){
		/*Month m1 = new Month();
		m1.setName("APR 16");
		m1.setWeeks(new Weeks());
		
		Month m2 = new Month();
		m2.setName("MAY 16");
		m2.setWeeks(new Weeks());
		
		this.months = new ArrayList<Month>();
		this.months.add(m1);
		this.months.add(m2);*/
	}

	public List<Month> getMonth() {
		return months;
	}

	public void setMonth(List<Month> month) {
		months = month;
	}    

}
