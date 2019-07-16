/**
 * 
 */
package com.soprasteria.devopsassesmenttool.util;

import java.util.List;

import com.soprasteria.devopsassesmenttool.model.Account;

/**
 * @author ddhinakaran
 *
 */
public class UserReportDetails {

	private Long userId;
	private String userName;
	private List<AccountDetails> accountdetails;

	/**
	 * @return the accountdetails
	 */
	public List<AccountDetails> getAccountdetails() {
		return accountdetails;
	}

	/**
	 * @param accountdetails the accountdetails to set
	 */
	public void setAccountdetails(List<AccountDetails> accountdetails) {
		this.accountdetails = accountdetails;
	}


	private List<ReportQuestionDetails> questions;

	

	public UserReportDetails(Long userId, String userName, List<ReportQuestionDetails> questions,List<AccountDetails>  accountdetails) {
		this.userId = userId;
		this.userName = userName;
		this.questions = questions;
		this.accountdetails=accountdetails;
	}

	public UserReportDetails() {

	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the questions
	 */
	public List<ReportQuestionDetails> getQuestions() {
		return questions;
	}

	/**
	 * @param questions the questions to set
	 */
	public void setQuestions(List<ReportQuestionDetails> questions) {
		this.questions = questions;
	}

	public static class ReportQuestionDetails {
		private Long questionId;
		private String questionLabel;
		private Long ratingValue;
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

		private String ratingLabel;
		private String comment;
		private List<ReportFileDetails> files;

		public ReportQuestionDetails() {

		}

		public ReportQuestionDetails(Long questionId, String questionLabel, Long ratingValue, String ratingLabel,
				String comment, List<ReportFileDetails> files) {
			this.questionId = questionId;
			this.questionLabel = questionLabel;
			this.ratingValue = ratingValue;
			this.ratingLabel = ratingLabel;
			this.comment = comment;
			this.files = files;
		}

		/**
		 * @return the questionId
		 */
		public Long getQuestionId() {
			return questionId;
		}

		/**
		 * @param questionId the questionId to set
		 */
		public void setQuestionId(Long questionId) {
			this.questionId = questionId;
		}

		/**
		 * @return the questionLabel
		 */
		public String getQuestionLabel() {
			return questionLabel;
		}

		/**
		 * @param questionLabel the questionLabel to set
		 */
		public void setQuestionLabel(String questionLabel) {
			this.questionLabel = questionLabel;
		}


		/**
		 * @return the ratingLabel
		 */
		public String getRatingLabel() {
			return ratingLabel;
		}

		/**
		 * @param ratingLabel the ratingLabel to set
		 */
		public void setRatingLabel(String ratingLabel) {
			this.ratingLabel = ratingLabel;
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

		/**
		 * @return the files
		 */
		public List<ReportFileDetails> getFiles() {
			return files;
		}

		/**
		 * @param files the files to set
		 */
		public void setFiles(List<ReportFileDetails> files) {
			this.files = files;
		}

	}

	public static class ReportFileDetails {
		private Long fileId;
		private String fileName;
		private String fileDownloadLink;

		public ReportFileDetails() {

		}

		public ReportFileDetails(Long fileId, String fileName, String fileDownloadLink) {
			this.fileId = fileId;
			this.fileName = fileName;
			this.fileDownloadLink = fileDownloadLink;
		}

		/**
		 * @return the fileId
		 */
		public Long getFileId() {
			return fileId;
		}

		/**
		 * @param fileId the fileId to set
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
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return the fileDownloadLink
		 */
		public String getFileDownloadLink() {
			return fileDownloadLink;
		}

		/**
		 * @param fileDownloadLink the fileDownloadLink to set
		 */
		public void setFileDownloadLink(String fileDownloadLink) {
			this.fileDownloadLink = fileDownloadLink;
		}

	}
	
	
	public static class AccountDetails {
		
		private String acclabel;
		private String acccolname;
		private String acccolnamedetails;
	
		/**
		 * @return the acclabel
		 */
		public String getAcclabel() {
			return acclabel;
		}
		/**
		 * @param acclabel the acclabel to set
		 */
		public void setAcclabel(String acclabel) {
			this.acclabel = acclabel;
		}
		/**
		 * @return the acccolname
		 */
		public String getAcccolname() {
			return acccolname;
		}
		/**
		 * @param acccolname the acccolname to set
		 */
		public void setAcccolname(String acccolname) {
			this.acccolname = acccolname;
		}
		/**
		 * @return the acccolnamedetails
		 */
		public String getAcccolnamedetails() {
			return acccolnamedetails;
		}
		/**
		 * @param acccolnamedetails the acccolnamedetails to set
		 */
		public void setAcccolnamedetails(String acccolnamedetails) {
			this.acccolnamedetails = acccolnamedetails;
		}
		public AccountDetails(String acccolname, String acccolnamedetails, String acclabel) {
			super();
			this.acccolname = acccolname;
			this.acccolnamedetails = acccolnamedetails;
			this.acclabel = acclabel;
		}
		public AccountDetails() {
			super();
			// TODO Auto-generated constructor stub
		}


	}
}
