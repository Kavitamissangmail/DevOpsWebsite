/**
 * 
 */
package com.soprasteria.devopsassesmenttool.util;

import java.util.List;

import com.soprasteria.devopsassesmenttool.model.Answer;

/**
 * @author dbkumar
 *
 */
public class CategorizedAnswers {

	private Long cId;

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
	 * @return the cName
	 */
	public String getcName() {
		return cName;
	}

	/**
	 * @param cName
	 *            the cName to set
	 */
	public void setcName(String cName) {
		this.cName = cName;
	}

	/**
	 * @return the answers
	 */
	public List<Answer> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 *            the answers to set
	 */
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	private String cName;
	private List<Answer> answers;

}
