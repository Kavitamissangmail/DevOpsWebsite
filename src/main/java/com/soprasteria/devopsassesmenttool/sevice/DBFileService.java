package com.soprasteria.devopsassesmenttool.sevice;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.DBFileRepository;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.FileStorageException;
import com.soprasteria.devopsassesmenttool.util.MyFileNotFoundException;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;

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

	public DBFile storeFile(MultipartFile file, Long userId, Long qId, Long answerId) {
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());

		try {
			// Check if the file's name contains invalid characters
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			DBFile dbFile = new DBFile(fileName, file.getContentType(), userId, qId, answerId, file.getBytes());

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

	public Set<DBFile> getFileByAnswerId(Long answerId) {
		Answer answer = ansRepository.findOne(answerId);
		if (answerId == null || answer == null)
			throw new ResourceNotFoundException("User with " + answerId + " ID does not exist!");

		return dbFileRepository.findByAnswerId(answerId);
	}
}
