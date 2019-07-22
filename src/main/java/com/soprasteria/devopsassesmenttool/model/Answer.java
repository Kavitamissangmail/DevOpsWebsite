/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;

import javax.persistence.Column;
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
	private Long cId;
	private Long qId;
	private Long ratingValue;
	private Long targetRatingValue;

	@Column(length = 1000)
	private String comment;

	@Column(length = 1000)
	private String targetComment;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "useridanswerlink", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

	/**
	 * @return the cId
	 */
	public Long getcId() {
		return cId;
	}

	/**
	 * @param cId
	 *            the cId to set
	 */
	public void setcId(Long cId) {
		this.cId = cId;
	}

	/**
	 * @return the answerId
	 */
	public Long getAnswerId() {
		return answerId;
	}

	/**
	 * @param answerId
	 *            the answerId to set
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
	 * @param user
	 *            the user to set
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
	 * @param qId
	 *            the qId to set
	 */
	public void setqId(Long qId) {
		this.qId = qId;
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
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the ratingValue
	 */
	public Long getRatingValue() {
		return ratingValue;
	}

	/**
	 * @param ratingValue
	 *            the ratingValue to set
	 */
	public void setRatingValue(Long ratingValue) {
		this.ratingValue = ratingValue;
	}

	/**
	 * @return the targetRatingValue
	 */
	public Long getTargetRatingValue() {
		return targetRatingValue;
	}

	/**
	 * @param targetRatingValue
	 *            the targetRatingValue to set
	 */
	public void setTargetRatingValue(Long targetRatingValue) {
		this.targetRatingValue = targetRatingValue;
	}

	/**
	 * @return the targetComment
	 */
	public String getTargetComment() {
		return targetComment;
	}

	/**
	 * @param targetComment
	 *            the targetComment to set
	 */
	public void setTargetComment(String targetComment) {
		this.targetComment = targetComment;
	}
}
