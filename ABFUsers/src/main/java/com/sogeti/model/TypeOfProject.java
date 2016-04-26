/**
 * 
 */
package com.sogeti.model;

/**
 * @author rajukaly
 *
 */
public class TypeOfProject {

	private String id;
	private String projType;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the projType
	 */
	public String getProjType() {
		return projType;
	}
	/**
	 * @param projType the projType to set
	 */
	public void setProjType(String projType) {
		this.projType = projType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeOfProject [id=");
		builder.append(id);
		builder.append(", projType=");
		builder.append(projType);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
