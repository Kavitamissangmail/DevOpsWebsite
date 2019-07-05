package com.soprasteria.devopsassesmenttool.util;

import static com.soprasteria.devopsassesmenttool.util.PDFUtils.EMPTY_STRING;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HEADER_VALUE_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.HELVETICA_BOLD_FONT;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K1;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K2;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K50;
import static com.soprasteria.devopsassesmenttool.util.PDFUtils.K6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.soprasteria.devopsassesmenttool.model.User;
import com.soprasteria.devopsassesmenttool.repository.UserRepository;

@Component
public class PDFUserReportCreation {

	private static final String FILE_HEADER = "DevOps Assessment Tool";
	private static final String ACCOUNT_DETAILS_HEADER = "Account Details";
	private static final String USER_REPORT_DETAILS_HEADER = "User Report Details";

	@Autowired
	UserRepository userRepo;

	@Autowired
	PDFUtils pdfUtils;

	public void create(final Document document, final UserReportDetails userReportDetails) throws DocumentException {
		document.setPageSize(PageSize.A4);
		document.open();
		createHeader(document);
		createUserDetails(document, userReportDetails);
		createReportDetails(document, userReportDetails);
		document.close();
	}

	private void createHeader(Document document) throws DocumentException {
		Paragraph p=new Paragraph(FILE_HEADER, HEADER_VALUE_FONT);
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
		if (user.getAccount() != null) {
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "Name");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getName()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "Customerlocation");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getCustomerlocation()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "deliverymanager");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getDeliverymanager()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "deliverylocation");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getDeliverylocation()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "teamsize");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getTeamsize()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "contracttype");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getContracttype()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "technology");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getTechnology()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "l1L2ServiceSupport");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getL1L2ServiceSupport()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "projectMgmtMethod");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getProjectMgmtMethod()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "appArchitecture");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getAppArchitecture()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "cloudApplication");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getCloudApplication()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "sepDevOPTeams");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getSepDevOPTeams()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "smallFrequentorProjectChanges");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getSmallFrequentorProjectChanges()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "complianceregulatoryRestrictions");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getComplianceregulatoryRestrictions()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "e2eSDLCProjectEnhancements");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getE2eSDLCProjectEnhancements()));
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, "Internal Customer Both");
			pdfUtils.addElementToCellContentWithoutBorder(userAccountTable, getString(user.getAccount().getInternalCustomerBoth()));
		}
		document.add(userAccountTable);
		document.newPage();
	}

	private void createReportDetails(Document document, UserReportDetails userReportDetails) throws DocumentException {

		Paragraph p=new Paragraph(USER_REPORT_DETAILS_HEADER, HELVETICA_BOLD_FONT);
		document.add(p);
		document.add(Chunk.NEWLINE);

		PdfPTable userReportTable = pdfUtils.createpdfTable(K6);
		pdfUtils.addElementToCellHeading(userReportTable, "Question ID");
		pdfUtils.addElementToCellHeading(userReportTable, "Question Label");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating ID");
		pdfUtils.addElementToCellHeading(userReportTable, "Rating Label");
		pdfUtils.addElementToCellHeading(userReportTable, "Files");
		pdfUtils.addElementToCellHeading(userReportTable, "Comment");

		if (!userReportDetails.getQuestions().isEmpty()) {
			userReportDetails.getQuestions().forEach(question -> {
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getQuestionId()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getQuestionLabel()));

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingId()));
				pdfUtils.addElementToCellContent(userReportTable, getString(question.getRatingLabel()));

				if (!question.getFiles().isEmpty()) {
					question.getFiles().forEach(file -> {
						PdfPTable table = pdfUtils.createpdfTable(K1);
						pdfUtils.addHyperlinkToCellContent(table, file.getFileName(), file.getFileDownloadLink());
						userReportTable.addCell(table);
					});

				}else {
					userReportTable.addCell("");
				}

				pdfUtils.addElementToCellContent(userReportTable, getString(question.getComment()));
			});
		}
		userReportTable.setKeepTogether(false);
		document.add(userReportTable);
		
		
	}
	
	private String getString(String value) {
		if(value==null)
			return EMPTY_STRING;
		return value;
	}
	
	private String getString(Long value) {
		if(value==null)
			return EMPTY_STRING;
		return value.toString();
	}
	
	private String getString(Integer value) {
		if(value==null)
			return EMPTY_STRING;
		return value.toString();
	}
}
