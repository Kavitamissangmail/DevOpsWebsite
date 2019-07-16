package com.soprasteria.devopsassesmenttool.sevice;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.HttpHeaders.CONTENT_ENCODING;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.EXPIRES;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.Answer;
import com.soprasteria.devopsassesmenttool.model.DBFile;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.Rating;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AccountLabelRespository;
import com.soprasteria.devopsassesmenttool.repository.AccountRepository;
import com.soprasteria.devopsassesmenttool.repository.AnswerRepository;
import com.soprasteria.devopsassesmenttool.repository.DBFileRepository;
import com.soprasteria.devopsassesmenttool.repository.QuestionRepository;
import com.soprasteria.devopsassesmenttool.repository.RatingRepository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.FileStorageException;
import com.soprasteria.devopsassesmenttool.util.MyFileNotFoundException;
import com.soprasteria.devopsassesmenttool.util.PDFUserReportCreation;
import com.soprasteria.devopsassesmenttool.util.PDFUtils;
import com.soprasteria.devopsassesmenttool.util.ResourceNotFoundException;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails.AccountDetails;
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
	private AccountRepository accountRepository;

	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private PDFUserReportCreation pdfUserReportCreation;

	@Autowired
	private AccountLabelRespository accountLabelRespository;

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

	public UserReportDetails getUserReportDetails(User user)  {
		UserReportDetails userRepDetails = new UserReportDetails();
		Account account = accountRepository.findByUserUserId(user.getUserId());

		List<AccountLabel> labels = accountLabelRespository.findAll();
		List<AccountDetails> accountDetails = new ArrayList<>();
		 try{
			    Class c = Class.forName("com.soprasteria.devopsassesmenttool.model.Account");
				System.out.println("Loaded class: " + c);
				Object obj = c.newInstance();
				obj=accountRepository.findByUserUserId(user.getUserId());
				if (account != null) {
			
					for(int i=0;i<labels.size();i++ ){
						
						
						AccountDetails accountDetail1 = new AccountDetails();
						String mdname=labels.get(i).getAcccolname().substring(0, 1).toUpperCase()+labels.get(i).getAcccolname().substring(1);	
						
						String cmdname="get"+mdname;
						accountDetail1.setAcccolnamedetails(String.valueOf(c.getMethod(cmdname).invoke(obj)));


				        accountDetail1.setAcccolname(labels.get(i).getAcccolname());
						accountDetail1.setAcclabel(labels.get(i).getAccountlabel());
				        accountDetails.add(accountDetail1);
					
					}
					userRepDetails.setAccountdetails(accountDetails);
				
				}
		 }catch (Exception e){
			  e.printStackTrace();
		 }
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

				Rating ratingbyqIdandrId = ratingRepository.getRatingsByQuestionQIdAndRatingValue(question.getqId(),
						answer.getRatingId());
				Rating rating = ratingRepository.findByRid(ratingbyqIdandrId.getRid());
				if (rating != null) {
					reportQuestionDetail.setRatingValue(rating.getRatingValue());
					reportQuestionDetail.setRatingLabel(rating.getRatinglabel());
				}
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

	@SuppressWarnings("unused")
	public HttpEntity<byte[]> exportUserReportDetailsPDF(User user) throws Exception {
		final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		final HttpHeaders headers;
		final Document document = new Document();
		UserReportDetails userReportDetails = getUserReportDetails(user);
		try {
			final PdfWriter writer = PdfWriter.getInstance(document, byteArrayOutputStream);
			pdfUserReportCreation.create(document, userReportDetails);
		} catch (DocumentException ex) {
			ex.printStackTrace();
		}

		final byte[] pdfBytes = byteArrayOutputStream.toByteArray();
		headers = createResponseHeaders(pdfBytes, MediaType.APPLICATION_PDF_VALUE, "UserReportDetails.pdf");

		return new HttpEntity<>(pdfBytes, headers);
	}

	/**
	 * Creates the response headers for the new export types.
	 *
	 * @param rawBody
	 *            the raw body
	 * @param contentType
	 *            the content type
	 * @param filename
	 *            the filename
	 * @return the http headers
	 */
	private HttpHeaders createResponseHeaders(final byte[] rawBody, final String contentType, final String filename) {
		final HttpHeaders headers = new HttpHeaders();

		headers.setContentLength(rawBody.length);
		headers.add(CONTENT_ENCODING, "UTF-8");
		headers.add(PDFUtils.CONTENT_TRANSFER_ENCODING, PDFUtils.BINARY);
		headers.add(CONTENT_TYPE, contentType);
		headers.add(CONTENT_DISPOSITION, String.format(PDFUtils.VALUE_ATTACHMENT_FILENAME, filename));
		headers.add(CACHE_CONTROL, PDFUtils.CACHE_CONTROL_VALUE);
		headers.add(EXPIRES, PDFUtils.EXPIRES_ZERO);

		return headers;
	}

}
