package com.soprasteria.devopsassesmenttool.sevice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.Rating;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.DBFileRepository;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.repository.RatingRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.FileStorageException;
import com.soprasteria.devopsassesmenttool.util.MyFileNotFoundException;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails.ReportFileDetails;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails.ReportQuestionDetails;

@Service
public class DBFileService {

	@Autowired
	private DBFileRepository dbFileRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository ansRepository;

	@Autowired
	private RatingRepository ratingRepository;

	public static final String DOWNLOAD_FILE_PATH = "/devops/downloadFile/";

	public DBFile storeFile(MultipartFile file, Long userId, Long qId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(fileName, file.getContentType(), userId, qId, file.getBytes());

			return dbFileRepository.save(dbFile);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public DBFile getFile(Long fileId) {
		DBFile dbFile = dbFileRepository.findOne(fileId);
		if (dbFile == null)
			throw new MyFileNotFoundException("File not found with id " + fileId);

		return dbFile;
	}

	public void deleteFile(Long fileId) {
		DBFile dbFile = dbFileRepository.findOne(fileId);
		if (dbFile == null)
			throw new MyFileNotFoundException("File not found with id " + fileId);
		dbFileRepository.delete(fileId);
	}

	public List<DBFile> getAllFiles() {
		return dbFileRepository.findAll();
	}

	public Set<DBFile> getFileByUserId(Long userId) {
		User user = userRepository.findOne(userId);
		if (userId == null || user == null)
			throw new ResourceNotFoundException("User with " + userId + " ID does not exist!");

		return dbFileRepository.findByUserId(userId);
	}

	public Set<DBFile> getFileByQuestionId(Long questionId) {
		Question ques = questionRepository.findOne(questionId);
		if (questionId == null || ques == null)
			throw new ResourceNotFoundException("User with " + questionId + " ID does not exist!");

		return dbFileRepository.findByQId(questionId);
	}

	public UserReportDetails getUserReportDetails(User user) {
		UserReportDetails userRepDetails = new UserReportDetails();
		userRepDetails.setUserId(user.getUserId());
		userRepDetails.setUserName(user.getUsername());
		List<ReportQuestionDetails> reportQuestionDetails = new ArrayList<>();
		List<Question> questions = questionRepository.findAll();
		questions.forEach(question -> {
			ReportQuestionDetails reportQuestionDetail = new ReportQuestionDetails();
			reportQuestionDetail.setQuestionId(question.getqId());
			reportQuestionDetail.setQuestionLabel(question.getQuestionlabel());
			Answer answer = ansRepository.getAnswerByUserUserIdAndQId(user.getUserId(), question.getqId());
			if (answer != null) {
				Rating rating = ratingRepository.findByRid(answer.getRatingId());
				reportQuestionDetail.setRatingId(rating.getRid());
				reportQuestionDetail.setRatingLabel(rating.getRatinglabel());
				reportQuestionDetail.setComment(answer.getComment());
			}
			List<ReportFileDetails> reportFiles = new ArrayList<>();
			Set<DBFile> qFiles = dbFileRepository.findByQId(question.getqId());
			if (qFiles != null && !qFiles.isEmpty())
				qFiles.forEach(file -> {
					reportFiles.add(new ReportFileDetails(file.getId(), file.getFileName(), ServletUriComponentsBuilder
							.fromCurrentContextPath().path(DOWNLOAD_FILE_PATH).path(file.getId() + "").toUriString()));
				});
			reportQuestionDetail.setFiles(reportFiles);
			reportQuestionDetails.add(reportQuestionDetail);
		});
		userRepDetails.setQuestions(reportQuestionDetails);

		return userRepDetails;
	}

}
