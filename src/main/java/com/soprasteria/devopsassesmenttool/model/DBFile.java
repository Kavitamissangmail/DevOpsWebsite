package com.soprasteria.devopsassesmenttool.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "file")
public class DBFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;

	private String fileName;

	private String fileType;

	private Long userId;

	private Long qId;

	@Lob
	private byte[] data;
	
	@Transient
	private String fileDownloadUri;

	/**
	 * @return the fileDownloadUri
	 */
	public String getFileDownloadUri() {
		return fileDownloadUri;
	}

	/**
	 * @param fileDownloadUri the fileDownloadUri to set
	 */
	public void setFileDownloadUri(String fileDownloadUri) {
		this.fileDownloadUri = fileDownloadUri;
	}

	public DBFile(String fileName, String fileType, Long userId, Long qId, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.userId = userId;
		this.qId = qId;
		this.data = data;

	}

	public DBFile() {
	
	}

	/**
	 * @return the fileId
	 */
	public Long getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType
	 *            the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the answerId
	 */

	/**
	 * @return the data
	 */
	@JsonIgnore
	public byte[] getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

}
