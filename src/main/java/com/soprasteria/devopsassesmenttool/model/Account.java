package com.soprasteria.devopsassesmenttool.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "account_details")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 

public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
			
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String customerlocation;

	private String deliverymanager;

	private String deliverylocation;

	

	private Integer teamsize;

	private String contracttype;

	private String technology;


	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}



	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;

	private String l1L2ServiceSupport;
	private String devL3ServiceSupport;
	private String projectMgmtMethod;
	private String appArchitecture;

	private String cloudApplication;
	private String sepDevOPTeams;
	private String smallFrequentorProjectChanges;
	private String complianceregulatoryRestrictions;
	private String e2eSDLCProjectEnhancements;
	private String internalCustomerBoth;

	public String getComplianceregulatoryRestrictions() {
		return complianceregulatoryRestrictions;
	}

	public void setComplianceregulatoryRestrictions(String complianceregulatoryRestrictions) {
		this.complianceregulatoryRestrictions = complianceregulatoryRestrictions;
	}

	public String getE2eSDLCProjectEnhancements() {
		return e2eSDLCProjectEnhancements;
	}

	public void setE2eSDLCProjectEnhancements(String e2eSDLCProjectEnhancements) {
		this.e2eSDLCProjectEnhancements = e2eSDLCProjectEnhancements;
	}

	public String getInternalCustomerBoth() {
		return internalCustomerBoth;
	}

	public void setInternalCustomerBoth(String internalCustomerBoth) {
		this.internalCustomerBoth = internalCustomerBoth;
	}

	public String getSepDevOPTeams() {
		return sepDevOPTeams;
	}

	public void setSepDevOPTeams(String sepDevOPTeams) {
		this.sepDevOPTeams = sepDevOPTeams;
	}

	public String getSmallFrequentorProjectChanges() {
		return smallFrequentorProjectChanges;
	}

	public void setSmallFrequentorProjectChanges(String smallFrequentorProjectChanges) {
		this.smallFrequentorProjectChanges = smallFrequentorProjectChanges;
	}

	public String getL1L2ServiceSupport() {
		return l1L2ServiceSupport;
	}

	public void setL1L2ServiceSupport(String l1l2ServiceSupport) {
		l1L2ServiceSupport = l1l2ServiceSupport;
	}

	public String getDevL3ServiceSupport() {
		return devL3ServiceSupport;
	}

	public void setDevL3ServiceSupport(String devL3ServiceSupport) {
		this.devL3ServiceSupport = devL3ServiceSupport;
	}

	public String getProjectMgmtMethod() {
		return projectMgmtMethod;
	}

	public void setProjectMgmtMethod(String projectMgmtMethod) {
		this.projectMgmtMethod = projectMgmtMethod;
	}

	public String getAppArchitecture() {
		return appArchitecture;
	}

	public void setAppArchitecture(String appArchitecture) {
		this.appArchitecture = appArchitecture;
	}

	public String getCloudApplication() {
		return cloudApplication;
	}

	public void setCloudApplication(String cloudApplication) {
		this.cloudApplication = cloudApplication;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Account() {
	}
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomerlocation() {
		return customerlocation;
	}

	public void setCustomerlocation(String customerlocation) {
		this.customerlocation = customerlocation;
	}

	public String getDeliverymanager() {
		return deliverymanager;
	}

	public void setDeliverymanager(String deliverymanager) {
		this.deliverymanager = deliverymanager;
	}

	public String getDeliverylocation() {
		return deliverylocation;
	}

	public void setDeliverylocation(String deliverylocation) {
		this.deliverylocation = deliverylocation;
	}

	public Integer getTeamsize() {
		return teamsize;
	}

	public void setTeamsize(Integer teamsize) {
		this.teamsize = teamsize;
	}

	public String getContracttype() {
		return contracttype;
	}

	public void setContracttype(String contracttype) {
		this.contracttype = contracttype;
	}

	public String getTechnology() {
		return technology;
	}

	public void setTechnology(String technology) {
		this.technology = technology;
	}

}
