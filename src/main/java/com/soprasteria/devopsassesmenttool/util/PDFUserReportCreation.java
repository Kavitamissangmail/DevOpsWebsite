package com.soprasteria.devopsassesmenttool.util;

import static com.soprasteria.devopsassesmenttool.util.PDFUtils.EMPTY_STRING;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HEADER_VALUE_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HELVETICA_BOLD_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K1;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K2;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K25;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K4;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K40;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K50;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K8;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AccountLabelRespository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;

@Component
public class PDFUserReportCreation {

	private static final String FILE_HEADER = "DevOps Assessment Report";
	private static final String ACCOUNT_DETAILS_HEADER = "Account Information";
	private static final String ACCOUNT_SCOPE_HEADER = "Account Scope";
	private static final String USER_REPORT_DETAILS_HEADER = "Rating Details";
	private static final String USER_DETAILS_HEADER = "User  Details";

	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountLabelRespository accountLabelRespository;

	@Autowired
	PDFUtils pdfUtils;

	public void create(final Document document, final UserReportDetails userReportDetails) throws DocumentException {
		document.setPageSize(PageSize.A4);
		document.open();
	
		addingImage(document);
		createHeader(document);
		createUserDetails(document, userReportDetails);
		createReportDetails(document, userReportDetails);
		document.close();
	}

	private void addingImage(Document document) throws DocumentException {
		PdfPCell tmImageCell = getTMImage();
		PdfPTable dataTable = new PdfPTable(1);
		dataTable.setHorizontalAlignment(0);
		dataTable.addCell(tmImageCell);
		document.add(dataTable);		
		document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
	}

	private PdfPCell getTMImage() {
		final PdfPCell imageCell = new PdfPCell();
		
		imageCell.setHorizontalAlignment(Image.ALIGN_LEFT);
		imageCell.setBorder(0);
		Image image = null;
		byte[] data;
		try {
			BufferedImage bImage = ImageIO.read(new File(
					"D:\\Learning\\DevOpsWebsite\\DevOpsWebsite\\src\\main\\webapp\\assets\\images\\Soprasteria.png"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos);
			data = bos.toByteArray();
			image = Image.getInstance(data);
			image.setAbsolutePosition(0f, 0f);

		} catch (Exception e1) {
			image = null;
		}
		if (image != null) {
			image.setWidthPercentage(50f);
			imageCell.addElement(image);
		}

		return imageCell;
	}
	
	

	private void createHeader(Document document) throws DocumentException {

		Paragraph p = new Paragraph(FILE_HEADER, HEADER_VALUE_FONT);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);
	}

	private void createUserDetails(Document document, UserReportDetails userReportDetails) throws DocumentException {
		User user = userRepo.findByUserId(userReportDetails.getUserId());



		
		PdfPTable userAccountInfoTable = pdfUtils.createpdfTablebyCustomiseWidthWithSplit(K4,
				new int[] { K25, K25, K25, K25 });
		
		PdfPTable userAccountScopeTable = pdfUtils.createpdfTablebyCustomiseWidthWithSplit(K2, new int[] { K50, K50 });

		try {
			document.add(new Paragraph(ACCOUNT_DETAILS_HEADER, HELVETICA_BOLD_FONT));
			document.add(new Phrase("\n"));

			List<AccountLabel> labels = accountLabelRespository.findAll();
			Class c = Class.forName("com.soprasteria.devopsassesmenttool.model.Account");

			Object obj = c.newInstance();
			obj = user.getAccount();
			if (user.getAccount() != null) {

				for (int i = 0; i < labels.size(); i++) {
					if (labels.get(i).getTablename().trim().equals("Account Information")){
						String mdname = labels.get(i).getAcccolname().substring(0, 1).toUpperCase()
								+ labels.get(i).getAcccolname().substring(1);

						String cmdname = "get" + mdname;

						pdfUtils.addElementToCellHeading(userAccountInfoTable, accountLabelRespository
								.findByacccolname(labels.get(i).getAcccolname()).getAccountlabel()+":");
						pdfUtils.addElementToCellContent(userAccountInfoTable,c.getMethod(cmdname).invoke(obj)==null?"":String.valueOf(c.getMethod(cmdname).invoke(obj)));

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		document.add(userAccountInfoTable);
		document.add(new Phrase("\n"));
		
		
		try {
			document.add(new Paragraph(ACCOUNT_SCOPE_HEADER, HELVETICA_BOLD_FONT));
			document.add(new Phrase("\n"));

			List<AccountLabel> labels = accountLabelRespository.findAll();
			Class c = Class.forName("com.soprasteria.devopsassesmenttool.model.Account");
			Object obj = c.newInstance();
			obj = user.getAccount();
			if (user.getAccount() != null) {

				for (int i = 0; i < labels.size(); i++) {
					if (labels.get(i).getTablename().trim().equals("Account Scope")) {
						String mdname = labels.get(i).getAcccolname().substring(0, 1).toUpperCase()
								+ labels.get(i).getAcccolname().substring(1);

						String cmdname = "get" + mdname;

						pdfUtils.addElementToCellHeading(userAccountScopeTable, accountLabelRespository
								.findByacccolname(labels.get(i).getAcccolname()).getAccountlabel()+":");
						pdfUtils.addElementToCellContent(userAccountScopeTable,c.getMethod(cmdname).invoke(obj)==null?"":String.valueOf(c.getMethod(cmdname).invoke(obj)));

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		document.add(userAccountScopeTable);

		document.newPage();
	}

	private void createReportDetails(Document document, UserReportDetails userReportDetails) throws DocumentException {

		Paragraph p = new Paragraph(USER_REPORT_DETAILS_HEADER, HELVETICA_BOLD_FONT);
		document.add(p);
		document.add(Chunk.NEWLINE);

		PdfPTable userReportTable = pdfUtils.createpdfTablebyCustomiseWidthWithSplit(K8, new int[] { K50, K40 ,K50, K50,K50, K50,K50,K50});

		pdfUtils.addElementToCellHeading(userReportTable, "Questions");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating Desc");
		pdfUtils.addElementToCellHeading(userReportTable, "Comment");
		pdfUtils.addElementToCellHeading(userReportTable, "Target Rating");
		pdfUtils.addElementToCellHeading(userReportTable, "Target Rating Desc");
		pdfUtils.addElementToCellHeading(userReportTable, "Taregt Comment");
		pdfUtils.addElementToCellHeading(userReportTable, "Files");

		if (!userReportDetails.getQuestions().isEmpty()) {
			userReportDetails.getQuestions().forEach(question -> {

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getQuestionLabel()));

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingValue()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingLabel()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getComment()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getTargetRatingValue()));			
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getTargetRatingLabel()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getTargetComment()));	

				if (!question.getFiles().isEmpty()) {
					PdfPTable table = pdfUtils.createpdfTable(K1);
					question.getFiles().forEach(file -> {
						pdfUtils.addHyperlinkToCellContentWithoutBorder(table, file.getFileName(),
								file.getFileDownloadLink());
					});
					userReportTable.addCell(table);

				} else {
					userReportTable.addCell("");
				}

			});
		}
		userReportTable.setKeepTogether(false);
		document.add(userReportTable);

	}

	private String getString(String value) {
		if (value == null)
			return EMPTY_STRING;
		return value;
	}

	private String getString(Long value) {
		if (value == null)
			return EMPTY_STRING;
		return value.toString();
	}

	private String getString(Integer value) {
		if (value == null)
			return EMPTY_STRING;
		return value.toString();
	}
}
