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
@Table(name = "rating")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Rating implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rid;

	private Long ratingValue;
	@Column(length=1000) 
	private String ratinglabel;

	
	@Column(length=1000)
	private String ratingDesc;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "questionId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Question question;

	/**
	 * @return the rid
	 */
	public Long getRid() {
		return rid;
	}

	/**
	 * @param rid the rid to set
	 */
	public void setRid(Long rid) {
		this.rid = rid;
	}

	/**
	 * @return the ratingDesc
	 */
	public String getRatingDesc() {
		return ratingDesc;
	}

	/**
	 * @return the ratingValue
	 */


	/**
	 * @return the ratinglabel
	 */
	public String getRatinglabel() {
		return ratinglabel;
	}

	/**
	 * @return the ratingValue
	 */
	public Long getRatingValue() {
		return ratingValue;
	}

	/**
	 * @param ratingValue the ratingValue to set
	 */
	public void setRatingValue(Long ratingValue) {
		this.ratingValue = ratingValue;
	}

	/**
	 * @param ratinglabel the ratinglabel to set
	 */
	public void setRatinglabel(String ratinglabel) {
		this.ratinglabel = ratinglabel;
	}

	/**
	 * @param ratingDesc the ratingDesc to set
	 */
	public void setRatingDesc(String ratingDesc) {
		this.ratingDesc = ratingDesc;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the question
	 */
	@JsonIgnore
	public Question getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}

}
