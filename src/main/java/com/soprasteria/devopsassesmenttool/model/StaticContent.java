/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "StaticContent_details")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class StaticContent implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scId;

	@Column(length = 5000)
	private String content;
	@Column(length = 5000)
	private String pagename;



	/**
	 * @return the scId
	 */
	public Long getScId() {
		return scId;
	}

	/**
	 * @param scId the scId to set
	 */
	public void setScId(Long scId) {
		this.scId = scId;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the pagename
	 */
	public String getPagename() {
		return pagename;
	}

	/**
	 * @param pagename
	 *            the pagename to set
	 */
	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

}
