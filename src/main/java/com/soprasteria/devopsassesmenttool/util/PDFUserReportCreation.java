package com.soprasteria.devopsassesmenttool.util;

import static com.soprasteria.devopsassesmenttool.util.PDFUtils.EMPTY_STRING;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HEADER_VALUE_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HELVETICA_BOLD_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K1;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K2;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K50;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K6;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.soprasteria.devopsassesmenttool.model.AccountLabel;
import com.soprasteria.devopsassesmenttool.model.Question;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.AccountLabelRespository;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;
import com.soprasteria.devopsassesmenttool.util.UserReportDetails.AccountDetails;

@Component
public class PDFUserReportCreation {

	private static final String FILE_HEADER = "DevOps Assessment Tool";
	private static final String ACCOUNT_DETAILS_HEADER = "Account Details";
	private static final String USER_REPORT_DETAILS_HEADER = "User Report Details";

	@Autowired
	UserRepository userRepo;
	@Autowired
	AccountLabelRespository accountLabelRespository;

	@Autowired
	PDFUtils pdfUtils;

	public void create(final Document document, final UserReportDetails userReportDetails) throws DocumentException {
		document.setPageSize(PageSize.A4);
		document.open();
		AddingImage(document);
		createHeader(document);
		createUserDetails(document, userReportDetails);
		createReportDetails(document, userReportDetails);
		document.close();
	}

	private void AddingImage(Document document) throws DocumentException {

		PdfPCell tmImageCell = getTMImage();

		PdfPTable dataTable = new PdfPTable(3);
		tmImageCell.setColspan(K2);
		dataTable.addCell(tmImageCell);

		document.add(dataTable);
	}

	private PdfPCell getTMImage() {
		final PdfPCell imageCell = new PdfPCell();
		imageCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		imageCell.setBorder(0);
		imageCell.setPaddingTop(10);
		Image image = null;
		byte[] data;
		try {

			BufferedImage bImage = ImageIO.read(new File(
					"D:\\Learning\\DevOpsWebsite\\DevOpsWebsite\\src\\main\\webapp\\assets\\images\\Soprasteria.png"));
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(bImage, "png", bos);
			data = bos.toByteArray();
			image = Image.getInstance(data);
			image.setAlignment(Element.ALIGN_CENTER);
		} catch (Exception e1) {
			image = null;
		}
		if (image != null) {
			image.setWidthPercentage(35f);
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
		document.add(Chunk.NEWLINE);
		document.add(new Paragraph(ACCOUNT_DETAILS_HEADER, HELVETICA_BOLD_FONT));

		PdfPTable userAccountTable = pdfUtils.createpdfTablebyCustomiseWidthWithSplit(K2, new int[] { K50, K50 });
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "User Name");
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getUsername()));
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "User ID");
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getUserId()));
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "Email ID");
		pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getUsermailid()));

		try {

			List<AccountLabel> labels = accountLabelRespository.findAll();
			Class c = Class.forName("com.soprasteria.devopsassesmenttool.model.Account");
			System.out.println("Loaded class: " + c);
			Object obj = c.newInstance();
			obj = user.getAccount();
			if (user.getAccount() != null) {

				for (int i = 0; i < labels.size(); i++) {

					String mdname = labels.get(i).getAcccolname().substring(0, 1).toUpperCase()
							+ labels.get(i).getAcccolname().substring(1);

					String cmdname = "get" + mdname;

					pdfUtils.addElementToCellContent(userAccountTable,
							accountLabelRespository.findByacccolname(labels.get(i).getAcccolname()).getAccountlabel());
					pdfUtils.addElementToCellContent(userAccountTable,
							String.valueOf(c.getMethod(cmdname).invoke(obj)));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		document.add(userAccountTable);
		document.newPage();
	}

	private void createReportDetails(Document document, UserReportDetails userReportDetails) throws DocumentException {

		Paragraph p = new Paragraph(USER_REPORT_DETAILS_HEADER, HELVETICA_BOLD_FONT);
		document.add(p);
		document.add(Chunk.NEWLINE);

		PdfPTable userReportTable = pdfUtils.createpdfTable(K6);
		pdfUtils.addElementToCellHeading(userReportTable, "Question ID");
		pdfUtils.addElementToCellHeading(userReportTable, "Question Label");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating Value");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating Label");
		pdfUtils.addElementToCellHeading(userReportTable, "Files");
		pdfUtils.addElementToCellHeading(userReportTable, "Comment");

		if (!userReportDetails.getQuestions().isEmpty()) {
			userReportDetails.getQuestions().forEach(question -> {
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getQuestionId()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getQuestionLabel()));

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingValue()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingLabel()));

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

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getComment()));
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
