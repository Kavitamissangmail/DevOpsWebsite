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
	private Long answerId;
	private Long qId;
	private Long ratingId;
	private String comment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "useridanswerlink", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	/**
	 * @return the answerId
	 */
	public Long getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId the answerId to set
	 */
	public void setAnswerId(Long answerId) {
		this.answerId = answerId;
	}

	/**
	 * @return the user
	 */
	@JsonIgnore
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	public Answer() {

	}

	/**
	 * @return the qId
	 */
	public Long getqId() {
		return qId;
	}

	/**
	 * @param qId the qId to set
	 */
	public void setqId(Long qId) {
		this.qId = qId;
	}

	/**
	 * @return the ratingId
	 */
	public Long getRatingId() {
		return ratingId;
	}

	/**
	 * @param ratingId the ratingId to set
	 */
	public void setRatingId(Long ratingId) {
		this.ratingId = ratingId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
