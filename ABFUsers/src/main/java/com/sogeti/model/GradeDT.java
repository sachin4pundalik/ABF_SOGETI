package com.sogeti.model;

public class GradeDT {

	
	private int gradeId;

	private String gradeType;

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeType() {
		return gradeType;
	}

	public void setGradeType(String gradeType) {
		this.gradeType = gradeType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GradeDT [gradeId=");
		builder.append(gradeId);
		builder.append(", gradeType=");
		builder.append(gradeType);
		builder.append("]");
		return builder.toString();
	}

	
}
