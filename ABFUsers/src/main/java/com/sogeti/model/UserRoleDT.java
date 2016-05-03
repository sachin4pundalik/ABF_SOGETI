/**
 * 
 */
package com.sogeti.model;

/**
 * @author vkalyana
 *
 */
public class UserRoleDT {

	private int userRoleId;
	private String userRole;
	
	
	/**
	 * @param userRoleId
	 * @param userRole
	 */
	public UserRoleDT(int userRoleId, String userRole) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
	}
	/**
	 * @return the userRoleId
	 */
	public int getUserRoleId() {
		return userRoleId;
	}
	/**
	 * @param userRoleId the userRoleId to set
	 */
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRoleDT [userRoleId=");
		builder.append(userRoleId);
		builder.append(", userRole=");
		builder.append(userRole);
		builder.append("]");
		return builder.toString();
	}
	
}
