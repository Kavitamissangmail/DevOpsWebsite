package com.soprasteria.devopsassesmenttool.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.sevice.DBFileService;
import com.soprasteria.devopsassesmenttool.sevice.QuestionService;
import com.soprasteria.devopsassesmenttool.sevice.UserService;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;
import com.soprasteria.devopsassesmenttool.util.UploadFileResponse;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails;

@RestController
@RequestMapping("/devops")
public class FileController {

	@Autowired
	private DBFileService dbFileService;

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService userService;

	public static final String DOWNLOAD_FILE_PATH="/devops/downloadFile/";
	
	@PostMapping("/user/{userId}/question/{questionId}/uploadFile")
	public UploadFileResponse uploadFile(@PathVariable(value = "userId") Long userId,
			@PathVariable(value = "questionId") Long questionId, @RequestParam("file") MultipartFile file) {

		User user = userService.getUserByUserId(userId);
		Question question = questionService.getQuestionById(questionId);
		if (user == null)
			throw new ResourceNotFoundException("Answer with ID " + userId + " does not exist!");

		DBFile dbFile = dbFileService.storeFile(file, user.getUserId(), question.getqId());

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path(DOWNLOAD_FILE_PATH)
				.path(dbFile.getId() + "").toUriString();

		return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri, file.getContentType(), 200L,
				dbFile.getId(), file.getSize());
	}

	@GetMapping("/downloadFile/{fileId}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId) {
		if (fileId == null || fileId == 0L) {
			throw new ResourceNotFoundException("File ID should not be empty.");
		}
		// Load file from database
		DBFile dbFile = dbFileService.getFile(fileId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.getData()));
	}

	@DeleteMapping("/file/{fileId}")
	public ResponseEntity<Object> deleteQuestionById(@PathVariable(value = "fileId") Long fileId) {
		dbFileService.deleteFile(fileId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/getAllFiles")
	public List<DBFile> getAllFiles() {
		return dbFileService.getAllFiles();
	}

	@GetMapping("/user/{userId}/files")
	public Set<DBFile> getFilesByUser(@PathVariable Long userId) {
		return dbFileService.getFileByUserId(userId);
	}

	@GetMapping("/question/{questionId}/files")
	public Set<DBFile> getFilesByQuestion(@PathVariable Long questionId) {
		return dbFileService.getFileByQuestionId(questionId);
	}

	@GetMapping("/user/{userId}/userReportDetails")
	public UserReportDetails getUserReportDetails(@PathVariable(value = "userId") Long userId) {

		User user = userService.getUserByUserId(userId);
		if (user == null)
			throw new ResourceNotFoundException("User with ID " + userId + " does not exist!");

		return dbFileService.getUserReportDetails(user);
	}
}
