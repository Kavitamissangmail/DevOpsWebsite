/**
 * 
 */
package com.soprasteria.devopsassesmenttool.util;

import java.util.List;

/**
 * @author ddhinakaran
 *
 */
public class UserReportDetails {

	private Long userId;
	private String userName;
	private List<ReportQuestionDetails> questions;

	public UserReportDetails(Long userId, String userName, List<ReportQuestionDetails> questions) {
		this.userId = userId;
		this.userName = userName;
		this.questions = questions;
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
		private Long ratingId;
		private String ratingLabel;
		private String comment;
		private List<ReportFileDetails> files;

		public ReportQuestionDetails() {

		}

		public ReportQuestionDetails(Long questionId, String questionLabel, Long ratingId, String ratingLabel,
				String comment, List<ReportFileDetails> files) {
			this.questionId = questionId;
			this.questionLabel = questionLabel;
			this.ratingId = ratingId;
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
		 * @return the ratingId
		 */
		public Long getRatingId() {
			return ratingId;
		}

		/**
		 * @param ratingId the ratingId to set
		 */
		public void setRatingId(Long ratingId) {
			this.ratingId = ratingId;
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
}
