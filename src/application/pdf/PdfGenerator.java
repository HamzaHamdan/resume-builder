package application.pdf;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import application.db.ConnectionFactory;
import application.model.Education;
import application.model.Experience;
import application.model.PersonalInfo;
import application.model.Skill;
import application.model.Summary;
import javafx.scene.control.Button;

public class PdfGenerator {

	private String schoolDatesPattern = "MM/dd/yyyy";
	private SimpleDateFormat schoolDatesFormatter = new SimpleDateFormat(schoolDatesPattern);

	public String generatePdfFile() throws SQLException, Exception {

		String fileName = fileNameGenerator();
		String firstName = null;
		String lastName = null;
		String emailAddress = null;
		String physicalAddress = null;
		String phoneNumber = null;
		byte[] imageDate = null;
		Chunk linebreak = new Chunk(new LineSeparator(1f, 100f, BaseColor.BLACK, Element.ALIGN_CENTER, -1));

		/*
		 * 
		 * Rectangle pageSize = new Rectangle(PageSize.LETTER);
		 * pageSize.setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xDE));
		 * Document document = new Document(pageSize);
		 * 
		 */
		
		Document document = new Document(PageSize.LETTER, 36, 36, 36, 36);

		PdfWriter.getInstance(document, new FileOutputStream(fileName));

		document.open();

		Connection connection = ConnectionFactory.getConnection();

		// retrieve personal info from the database
		String query = "select first_name, last_name, email_address, phone_number, physical_address, image_data from personalinfo";
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(query);

		while (rs.next()) {
			PersonalInfo info = new PersonalInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5));

			firstName = info.getFirstName();
			lastName = info.getLastName();
			emailAddress = info.getEmailAddress();
			physicalAddress = info.getPhysicalAddress();
			phoneNumber = info.getPhoneNumber();
			imageDate = rs.getBytes(6);
		}

		// retrieve education records from the database
		query = "select id, school_name, from_Date, to_date, degree_type, degree_name, description from education";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(query);
		ArrayList<Education> educationList = new ArrayList<Education>();
		while (rs.next()) {
			Education education = new Education(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
					rs.getString(5), rs.getString(6), rs.getString(7), new Button(), new Button());
			educationList.add(education);
		}

		// retrieve experience records from the database
		query = "select id, company_name, from_Date, to_date, position, description from experience";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(query);
		ArrayList<Experience> experienceList = new ArrayList<Experience>();
		while (rs.next()) {
			Experience experience = new Experience(rs.getLong(1), rs.getString(2), rs.getDate(3), rs.getDate(4),
					rs.getString(5), rs.getString(6), new Button(), new Button());
			experienceList.add(experience);
		}

		// retrieve summary details
		query = "select summary from summary";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(query);
		Summary summary = new Summary();
		while (rs.next()) {
			summary.setSummary(rs.getString(1));
		}

		// retrieve skills records from the database
		query = "select id, skill from skills";
		stmt = connection.createStatement();
		rs = stmt.executeQuery(query);
		ArrayList<Skill> skillsList = new ArrayList<Skill>();
		while (rs.next()) {
			Skill skill = new Skill(rs.getLong(1), rs.getString(2), new Button(), new Button());
			skillsList.add(skill);
		}

		// create an image object and show image in the document
		Image image = Image.getInstance(imageDate);
		image.scaleToFit(80, 80);
		image.setAbsolutePosition(496f, 670f);
		document.add(image);

		// print empty line in the document
		document.add(new Paragraph(Chunk.NEWLINE));

		Paragraph fullName = new Paragraph(firstName + " " + lastName,
				new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));
		fullName.setAlignment(Element.ALIGN_LEFT);
		document.add(fullName);

		Paragraph contactInfoSection = new Paragraph(physicalAddress + " | " + emailAddress + " | " + phoneNumber,
				new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL));
		contactInfoSection.setAlignment(Element.ALIGN_LEFT);
		document.add(contactInfoSection);

		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph(Chunk.NEWLINE));

		Chunk summaryText = new Chunk();
		summaryText.append("Summary");
		summaryText.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));

		Paragraph summarySection = new Paragraph(2);
		summarySection.add(summaryText);
		summarySection.setAlignment(Element.ALIGN_LEFT);
		document.add(summarySection);

		document.add(linebreak);

		PdfPTable summaryTable = new PdfPTable(1);
		summaryTable.setWidthPercentage(100);
		summaryTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		Phrase summaryCellPhrase = new Phrase(summary.getSummary());

		PdfPCell summaryCell = new PdfPCell();
		summaryCell.addElement(summaryCellPhrase);
		summaryCell.setBorder(0);

		summaryTable.addCell(summaryCell);

		document.add(summaryTable);

		// print empty line in the document
		document.add(new Paragraph(Chunk.NEWLINE));
		document.add(new Paragraph(Chunk.NEWLINE));

		Chunk text = new Chunk();
		text.append("Education");
		text.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));

		Paragraph educationSection = new Paragraph(2);
		educationSection.add(text);
		educationSection.setAlignment(Element.ALIGN_LEFT);
		document.add(educationSection);

		document.add(linebreak);

		PdfPTable educationTable = new PdfPTable(2);
		educationTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		for (Education education : educationList) {

			Paragraph educationDate = new Paragraph(
					schoolDatesFormatter.format(education.getFromDate()) + " - "
							+ schoolDatesFormatter.format(education.getToDate()),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			educationDate.setAlignment(Element.ALIGN_LEFT);

			Phrase datePhrase = new Phrase();
			datePhrase.add(educationDate);

			Paragraph schoolName = new Paragraph(education.getSchoolName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			schoolName.setAlignment(Element.ALIGN_LEFT);

			Paragraph degreeDetails = new Paragraph(education.getDegreeType() + " - " + education.getDegreeName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			degreeDetails.setAlignment(Element.ALIGN_LEFT);

			Paragraph educationDescription = new Paragraph(education.getDescription(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			educationDescription.setAlignment(Element.ALIGN_LEFT);

			Phrase schoolDetails = new Phrase();
			schoolDetails.add(schoolName);
			schoolDetails.add("\n");
			schoolDetails.add(degreeDetails);
			schoolDetails.add("\n");
			schoolDetails.add(educationDescription);

			PdfPCell dateCell = new PdfPCell();
			dateCell.addElement(datePhrase);
			dateCell.setBorder(0);
			dateCell.setPadding(10);

			PdfPCell detailsCell = new PdfPCell();
			detailsCell.addElement(schoolDetails);
			detailsCell.setBorder(0);
			detailsCell.setPadding(10);

			educationTable.addCell(dateCell);
			educationTable.addCell(detailsCell);
		}

		document.add(educationTable);

		// print empty line in the document
		document.add(new Paragraph(Chunk.NEWLINE));

		Chunk experienceText = new Chunk();
		experienceText.append("Experience");
		// text.setUnderline(1, text.getWidthPoint());
		experienceText.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));

		Paragraph experienceSection = new Paragraph(2);
		experienceSection.add(experienceText);
		experienceSection.setAlignment(Element.ALIGN_LEFT);
		document.add(experienceSection);

		document.add(linebreak);

		PdfPTable experienceTable = new PdfPTable(2);
		experienceTable.setHorizontalAlignment(Element.ALIGN_LEFT);

		for (Experience experience : experienceList) {

			Paragraph experienceDate = new Paragraph(
					schoolDatesFormatter.format(experience.getFromDate()) + " - "
							+ schoolDatesFormatter.format(experience.getToDate()),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			experienceDate.setAlignment(Element.ALIGN_LEFT);

			Phrase datePhrase = new Phrase();
			datePhrase.add(experienceDate);

			Paragraph companyName = new Paragraph(experience.getComapnyName(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			companyName.setAlignment(Element.ALIGN_LEFT);

			Paragraph experienceDetails = new Paragraph(experience.getPosition(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			experienceDetails.setAlignment(Element.ALIGN_LEFT);

			Paragraph experienceDescription = new Paragraph(experience.getDescription(),
					new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL));
			experienceDescription.setAlignment(Element.ALIGN_LEFT);

			Phrase experienceDetailsPhrase = new Phrase();
			experienceDetailsPhrase.add(companyName);
			experienceDetailsPhrase.add("\n");
			experienceDetailsPhrase.add(experienceDetails);
			experienceDetailsPhrase.add("\n");
			experienceDetailsPhrase.add(experienceDescription);

			PdfPCell dateCell = new PdfPCell();
			dateCell.addElement(datePhrase);
			dateCell.setBorder(0);
			dateCell.setPadding(10);

			PdfPCell detailsCell = new PdfPCell();
			detailsCell.addElement(experienceDetailsPhrase);
			detailsCell.setBorder(0);
			detailsCell.setPadding(10);

			experienceTable.addCell(dateCell);
			experienceTable.addCell(detailsCell);
		}

		document.add(experienceTable);

		// print empty line in the document
		document.add(new Paragraph(Chunk.NEWLINE));

		Chunk skillsText = new Chunk();
		skillsText.append("Skills");
		skillsText.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL));

		Paragraph skillsSection = new Paragraph(2);
		skillsSection.add(skillsText);
		skillsSection.setAlignment(Element.ALIGN_LEFT);
		document.add(skillsSection);

		document.add(linebreak);

		List bulletPointedSkillsList = new List(skillsList.size());
		bulletPointedSkillsList.setListSymbol("\u2022");

		for (Skill skill : skillsList) {
			bulletPointedSkillsList.add(
					new ListItem(" " + skill.getSkillDesc(), new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL)));

		}

		document.add(bulletPointedSkillsList);

		document.close();

		return fileName;
	}

	private String fileNameGenerator() {
		String datePattern = "MM-dd-yyyy";
		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
		String formattedDate = dateFormat.format(new Date());
		return "resume-" + formattedDate + ".pdf";
	}

}
