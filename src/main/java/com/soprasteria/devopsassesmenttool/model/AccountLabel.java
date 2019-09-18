/**
 * 
 */
package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author dbkumar
 *
 */
@Entity
@Table(name = "account_label")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AccountLabel implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private String acccolname;

	private String accountlabel;


	private String tablename;

	/**
	 * @return the tablename
	 */
	public String getTablename() {
		return tablename;
	}

	/**
	 * @param tablename
	 *            the tablename to set
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 * @return the dropdown
	 */
	public List<String> getDropdown() {
		return dropdown;
	}

	/**
	 * @param dropdown
	 *            the dropdown to set
	 */
	public void setDropdown(List<String> dropdown) {
		this.dropdown = dropdown;
	}

	private String qtype;


	@ElementCollection(fetch = FetchType.EAGER)
	@Embedded
	private List<String> dropdown = new ArrayList<String>();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the acccolname
	 */
	public String getAcccolname() {
		return acccolname;
	}

	/**
	 * @param acccolname
	 *            the acccolname to set
	 */
	public void setAcccolname(String acccolname) {
		this.acccolname = acccolname;
	}

	/**
	 * @return the accountlabel
	 */
	public String getAccountlabel() {
		return accountlabel;
	}

	/**
	 * @param accountlabel
	 *            the accountlabel to set
	 */
	public void setAccountlabel(String accountlabel) {
		this.accountlabel = accountlabel;
	}

	/**
	 * @return the qtype
	 */
	public String getQtype() {
		return qtype;
	}

	/**
	 * @param qtype
	 *            the qtype to set
	 */
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

}
