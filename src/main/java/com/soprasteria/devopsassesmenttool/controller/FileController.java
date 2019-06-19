package com.soprasteria.devopsassesmenttool.controller;

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

import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.sevice.AnswerService;
import com.soprasteria.devopsassesmenttool.sevice.DBFileService;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;
import com.soprasteria.devopsassesmenttool.util.UploadFileResponse;

@RestController
@RequestMapping("/devops")
public class FileController {

	@Autowired
	private DBFileService dbFileService;

	@Autowired
	private AnswerService ansService;

	@PostMapping("/answer/{answerId}/uploadFile")
	public UploadFileResponse uploadFile(@PathVariable(value = "answerId") Long answerId,
			@RequestParam("file") MultipartFile file) {
		Answer ans = ansService.getAnswerByAnswerId(answerId);
		if (ans == null)
			throw new ResourceNotFoundException("Answer with ID " + answerId + " does not exist!");

		DBFile dbFile = dbFileService.storeFile(file, ans.getUser().getUserId(), ans.getqId(), ans.getAnswerId());

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/devops/downloadFile/")
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

}
