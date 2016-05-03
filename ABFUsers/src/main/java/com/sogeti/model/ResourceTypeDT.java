package com.sogeti.model;



public class ResourceTypeDT {
	private int resourcetypeId;

	private String resourceType;

	

	public int getResourcetypeId() {
		return resourcetypeId;
	}

	public void setResourcetypeId(int resourcetypeId) {
		this.resourcetypeId = resourcetypeId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResourceTypeDT [resourcetypeId=");
		builder.append(resourcetypeId);
		builder.append(", resourceType=");
		builder.append(resourceType);
		builder.append("]");
		return builder.toString();
	}
	
}
