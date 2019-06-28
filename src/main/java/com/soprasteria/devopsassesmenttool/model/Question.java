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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author dbkumar
 *
 */
@Entity
@Table(name = "question_details")
// @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3577426965070580318L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long qId;

	private String questionlabel;

	@Column(columnDefinition = "text")
	private String questionDesc;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@org.hibernate.annotations.OrderBy(clause = "rid")
	private Set<Rating> ratings = new HashSet<Rating>();

	public Question() {
	}

	/**
	 * @return the category
	 */
	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * @return the questionId
	 */

	/**
	 * @return the id
	 */

	/**
	 * @return the categoryId
	 */

	/**
	 * @return the question
	 */

	/**
	 * @return the questionDesc
	 */
	public String getQuestionDesc() {
		return questionDesc;
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
	 * @return the questionlabel
	 */
	public String getQuestionlabel() {
		return questionlabel;
	}

	/**
	 * @param questionlabel the questionlabel to set
	 */
	public void setQuestionlabel(String questionlabel) {
		this.questionlabel = questionlabel;
	}

	/**
	 * @param questionDesc the questionDesc to set
	 */
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the ratings
	 */

	public Set<Rating> getRatings() {
		return ratings;
	}

	/**
	 * @param ratings the ratings to set
	 */

	public void setRatings(Set<Rating> ratings) {
		this.ratings = ratings;
	}

}