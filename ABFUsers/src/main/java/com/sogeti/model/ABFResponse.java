/**
 * 
 */
package com.sogeti.model;

/**
 * @author rajukaly
 *
 */
public class ABFResponse {

	private Object successResponse;
	private String failureResponse;
	private String status;
	
	/**
	 * @return the successResponse
	 */
	public Object getSuccessResponse() {
		return successResponse;
	}
	/**
	 * @param successResponse the successResponse to set
	 */
	public void setSuccessResponse(Object successResponse) {
		this.successResponse = successResponse;
	}
	/**
	 * @return the failureResponse
	 */
	public String getFailureResponse() {
		return failureResponse;
	}
	/**
	 * @param failureResponse the failureResponse to set
	 */
	public void setFailureResponse(String failureResponse) {
		this.failureResponse = failureResponse;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ABFResponse [successResponse=");
		builder.append(successResponse);
		builder.append(", failureResponse=");
		builder.append(failureResponse);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
	
}
