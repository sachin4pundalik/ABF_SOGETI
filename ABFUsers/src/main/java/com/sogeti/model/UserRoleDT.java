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
	private int active;
	
	
	public UserRoleDT() {
	}
	
	/**
	 * @param userRoleId
	 * @param userRole
	 */
	public UserRoleDT(int userRoleId, String userRole, int active) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
		this.active = active;
	}
	
	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
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
		builder.append(", active=");
		builder.append(active);
		builder.append("]");
		return builder.toString();
	}
	
}
