package com.soprasteria.devopsassesmenttool.util;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

/**
 * The PDFUtils.
 */
@Component
public class PDFUtils {

	/** The Constant K0. */
	public static final int K0 = 0;

	/** The Constant K1. */
	public static final int K1 = 1;

	/** The Constant K2. */
	public static final int K2 = 2;
	/** The Constant K4. */
	public static final int K4 = 4;

	/** The Constant K5. */
	public static final int K5 = 5;

	/** The Constant K6. */
	public static final int K6 = 6;
	
	/** The Constant K7. */
	public static final int K7 = 7;
	/** The Constant K6. */
	public static final int K8 = 8;
	/** The Constant K11. */
	public static final int K11 = 11;

	/** The Constant K12. */
	public static final int K12 = 12;
	/** The Constant K25. */
	public static final int K25= 25;

	/** The Constant K50. */
	public static final int K50 = 50;
	/** The Constant K40. */
	public static final int K40 = 40;
	
	/** The Constant K55. */
	public static final int K55 = 55;

	/** The Constant K68. */
	public static final int K68 = 68;

	/** The Constant K100. */
	public static final int K100 = 100;

	/** The Constant K114. */
	public static final int K114 = 114;

	/** The Constant K180. */
	public static final int K180 = 180;

	/** The constant HEADER. */
	static final Font CONTENT_FONT = new Font(Font.FontFamily.HELVETICA, 11);

	/** The constant HEADER VALUE FONT. */
	static final Font HEADER_VALUE_FONT = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);

	/** The constant HELVETICA_BOLD_FONT. */
	public static final Font HELVETICA_BOLD_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

	/** The Constant EMPTY_STRING */
	public static final String EMPTY_STRING = "";

	/** The Constant NO_TEXT_TO_SHOW. */
	public static final String NO_TEXT_TO_SHOW = "-";

	/** The constant BASE_COLOR. */
	private static final BaseColor BASE_COLOR = new BaseColor(K68, K114, K180);

	/** The constant UNDERLINE_FONT. */
	private static final Font UNDERLINE_FONT = new Font(Font.FontFamily.HELVETICA, K12, Font.UNDERLINE, BASE_COLOR);

	/** The Constant CACHE_CONTROL_VALUE. */
	public static final String CACHE_CONTROL_VALUE = "must-revalidate, post-check=0, pre-check=0";

	/** The Constant CONTENT_TRANSFER_ENCODING. */
	public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";

	/** The Constant BINARY. */
	public static final String BINARY = "binary";

	/** The Constant EXPIRES_ZERO. */
	public static final String EXPIRES_ZERO = "0";

	/** The Constant VALUE_ATTACHMENT_FILENAME. */
	public static final String VALUE_ATTACHMENT_FILENAME = "Attachment; filename=\"%s\"";

	/**
	 * Create a table with cell count.
	 *
	 * @return The PdfPtable object.
	 * @throws Exception
	 */
	public PdfPTable createpdfTablebyCustomiseWidthWithSplit(int size, int[] customiseWidth) throws DocumentException {
		final PdfPTable pageTabledefault = new PdfPTable(size);
		pageTabledefault.setWidthPercentage(K100);
		pageTabledefault.setWidths(customiseWidth);
		return pageTabledefault;
	}
	


	public void addElementToCellContentWithoutBorder(PdfPTable table, String value) {
		table.addCell(createCellContent(value, false));
	}

	public void addHyperlinkToCellContentWithoutBorder(PdfPTable table, String value, String url) {
		table.addCell(createCellHyperlink(value, url, false));
	}

	public void addElementToCellHeadingWithoutBorder(PdfPTable table, String value) {
		table.addCell(createCellHeading(value, false));
	}

	public void addElementToCellContent(PdfPTable table, String value) {
		table.addCell(createCellContent(value, true));
	}
	


	public void addElementToCellHeading(PdfPTable table, String value) {
		table.addCell(createCellHeading(value, true));
	}

	private PdfPCell createCellHeading(String values, boolean isBorder) {
		final PdfPCell cell;
		if (isBorder)
			cell = createBorderCell();
		else
			cell = createNoBorderCell();
		cell.addElement(headingText(values));
		return cell;
	}

	private PdfPCell createCellContent(String values, boolean isBorder) {
		final PdfPCell cell;
		if (isBorder)
			cell = createBorderCell();
		else
			cell = createNoBorderCell();
		cell.addElement(text(values));
		return cell;
	}

	private PdfPCell createCellHyperlink(String values, String url, boolean isBorder) {
		final PdfPCell cell;
		if (isBorder)
			cell = createBorderCell();
		else
			cell = createNoBorderCell();
		Paragraph paragraph = new Paragraph();
		Anchor anchor = new Anchor(values, UNDERLINE_FONT);
		anchor.setReference(url);
		paragraph.add(anchor);
		cell.addElement(paragraph);
		return cell;
	}

	public Anchor getHyperLink(String value, String url) {
		Anchor anchor = new Anchor(value, UNDERLINE_FONT);
		anchor.setReference(url);
		return anchor;
	}

	/**
	 * Create a table cell without border.
	 *
	 * @return The PdfPCell object.
	 */
	private PdfPCell createNoBorderCell() {
		final PdfPCell cell = new PdfPCell();
		cell.setBorder(Rectangle.NO_BORDER);
		cell.setPaddingBottom(K5);
		cell.setPaddingLeft(K5);
		cell.setPaddingRight(K5);
		return cell;
	}

	/**
	 * Create a table cell without border.
	 *
	 * @return The PdfPCell object.
	 */
	private PdfPCell createBorderCell() {
		final PdfPCell cell = new PdfPCell();
		cell.setPaddingBottom(K5);
		cell.setPaddingLeft(K5);
		cell.setPaddingRight(K5);
		return cell;
	}

	/**
	 * Create a Pdf Phrase.
	 *
	 * @param text
	 *            the text
	 * @return the phrase
	 */
	private Phrase text(final String text) {
		return new Phrase(text, CONTENT_FONT);
	}

	/**
	 * Create a Pdf Phrase.
	 * 
	 * @param text
	 *            the text
	 * @return the phrase
	 */
	public Phrase headingText(final String text) {
		return new Phrase(text, HEADER_VALUE_FONT);
	}

	/**
	 * Create a table with cell count.
	 *
	 * @return The PdfPtable object.
	 * @throws Exception
	 */
	public PdfPTable createpdfTable(int size) {
		final PdfPTable pageTabledefault = new PdfPTable(size);
		pageTabledefault.setKeepTogether(true);
		pageTabledefault.setWidthPercentage(K100);
		return pageTabledefault;
	}


}
