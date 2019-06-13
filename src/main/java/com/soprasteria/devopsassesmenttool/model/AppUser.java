/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author dbkumar
 *
 */

@Entity
@Table(name = "app_user", uniqueConstraints = { @UniqueConstraint(name = "APP_USER_UK", columnNames = "User_Name") })
public class AppUser implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "user_name", length = 36, nullable = false)
	private String userName;

	@Column(name = "encryted_password", length = 128, nullable = false)
	private String encrytedPassword;

	@Column(name = "name", length = 36, nullable = false)
	private String name;

	@Column(name = "email_id", length = 36, nullable = false)
	private String emailId;

	@Column(name = "Enabled", length = 1, nullable = false)
	private boolean enabled;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Set<Answer> answers = new HashSet<Answer>();

	public AppUser() {

	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the encrytedPassword
	 */
	public String getEncrytedPassword() {
		return encrytedPassword;
	}

	/**
	 * @param encrytedPassword the encrytedPassword to set
	 */
	public void setEncrytedPassword(String encrytedPassword) {
		this.encrytedPassword = encrytedPassword;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the answers
	 */
	@JsonIgnore
	public Set<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers the answers to set
	 */
	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
