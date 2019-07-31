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
	private String loginName;
	private String accountName;
	private String token;
	private long userId;
	private String message;
	private String role;


	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

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

	public ApiResponse(Long status, String userrName, String loginName ,String accountName, String token, long userId,String message,String role ) {

		this.status = status;
		this.userrName = userrName;
		this.loginName =loginName;
		this.accountName=accountName;
		this.token = token;
		this.userId = userId;
		this.message = message;
		this.role=role;
		
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
