/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dbkumar
 *
 */
@Entity
@Table(name = "answer_table")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Answer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer answerId;

	/**
	 * @return the answerId
	 */
	public Integer getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(Integer answerId) {
		this.answerId = answerId;
	}

	private Integer qId;
	private Integer ratingId;
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "useridanswerlink", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private AppUser appUser;

	/**
	 * @return the user
	 */

	@JsonIgnore
	public AppUser getUser() {
		return appUser;
	}

	/**
	 * @param appUser the user to set
	 */
	public void setUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Answer() {

	}

	/**
	 * @return the qId
	 */
	public Integer getqId() {
		return qId;
	}

	/**
	 * @param qId the qId to set
	 */
	public void setqId(Integer qId) {
		this.qId = qId;
	}

	/**
	 * @return the ratingId
	 */
	public Integer getRatingId() {
		return ratingId;
	}

	/**
	 * @param ratingId the ratingId to set
	 */
	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

}
