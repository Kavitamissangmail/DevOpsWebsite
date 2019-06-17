/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

/**
 * @author dbkumar
 *
 */
public class Login {
	
	private String token;

	private boolean validlogin;

	private String role;

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
	 * @return the validlogin
	 */
	public boolean isValidlogin() {
		return validlogin;
	}

	/**
	 * @param validlogin the validlogin to set
	 */
	public void setValidlogin(boolean validlogin) {
		this.validlogin = validlogin;
	}

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

}
