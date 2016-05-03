package com.sogeti.model;

public class RoleDT {
	private int roleId;
	private String roleType;
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleType() {
		return roleType;
	}
	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleDT [roleId=");
		builder.append(roleId);
		builder.append(", roleType=");
		builder.append(roleType);
		builder.append("]");
		return builder.toString();
	}
	
	
}
