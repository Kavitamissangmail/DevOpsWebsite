/**
 * 
 */
package com.soprasteria.devopsassesmenttool.util;

/**
 * @author dbkumar
 *
 */
public class ApiResponse {

	private Long status;
	private String userrName;
	private String token;
	private long userId;


	/**
	 * @return the userrName
	 */
	public String getUserrName() {
		return userrName;
	}

	/**
	 * @param userrName the userrName to set
	 */
	public void setUserrName(String userrName) {
		this.userrName = userrName;
	}

	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}

	public ApiResponse(Long status, String userrName, String token, long userId) {

		this.status = status;
		this.userrName = userrName;
		this.token = token;
		this.userId = userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the status
	 */
	public Long getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Long status) {
		this.status = status;
	}




}
