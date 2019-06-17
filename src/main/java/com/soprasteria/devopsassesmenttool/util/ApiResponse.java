/**
 * 
 */
package com.soprasteria.devopsassesmenttool.util;

/**
 * @author dbkumar
 *
 */
public class ApiResponse {

	private int status;
	private String userrname;
	private String token;

	public int getStatus() {
		return status;
	}

	public ApiResponse(int status, String userrname, String token) {

		this.status = status;
		this.userrname = userrname;
		this.token = token;
	}

	/**
	 * @return the userrname
	 */
	public String getUserrname() {
		return userrname;
	}

	/**
	 * @param userrname
	 *            the userrname to set
	 */
	public void setUserrname(String userrname) {
		this.userrname = userrname;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */

}
