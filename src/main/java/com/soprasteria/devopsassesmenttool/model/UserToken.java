/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dbkumar
 *
 */

@Entity
@Table(name = "user_token")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class UserToken  implements Serializable{
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seqId;
	
	private String username;
	private String token;
	private  boolean isvalid;
	/**
	 * @return the seqId
	 */
	public Integer getSeqId() {
		return seqId;
	}
	/**
	 * @param seqId the seqId to set
	 */
	public void setSeqId(Integer seqId) {
		this.seqId = seqId;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the isvalid
	 */
	public boolean isIsvalid() {
		return isvalid;
	}
	/**
	 * @param isvalid the isvalid to set
	 */
	public void setIsvalid(boolean isvalid) {
		this.isvalid = isvalid;
	}

}
