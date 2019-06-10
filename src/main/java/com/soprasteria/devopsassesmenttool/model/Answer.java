/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dbkumar
 *
 */
@Entity
@Table(name = "answer_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
public class Answer implements Serializable {
	
	
	
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
	private User user;
	
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





