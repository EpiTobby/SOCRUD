package fr.tobby.socrud.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import fr.tobby.socrud.model.ProgramModel;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;

public class PDFExporter {

    private static void writeProgramInformation(Document document, ProgramModel programModel) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);

        PdfPCell contentCell = new PdfPCell();
        contentCell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        Font fontContent = FontFactory.getFont(FontFactory.HELVETICA);
        fontContent.setColor(Color.BLACK);

        cell.setPhrase(new Phrase("Titre", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getTitle(), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getDescription(), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Campus", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getCampus(), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Durée", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getDurationMonths(), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Type", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getDegree(), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Tarif", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(String.valueOf(programModel.getPrice()), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Présentiel", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(String.valueOf(programModel.getRemotePercentage()), fontContent));
        table.addCell(contentCell);

        cell.setPhrase(new Phrase("Début", font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(programModel.getStartDate().toString(), fontContent));
        table.addCell(contentCell);

        document.add(table);
    }

    private static void writeSemestersData(Document document, ProgramModel programModel) throws DocumentException {
        programModel.getSubjects().forEach((semester, subjects) -> {
            Paragraph semesterTitle = new Paragraph("Semestre " + semester, FontFactory.getFont(FontFactory.HELVETICA_BOLD));


            List subjectList = new List(true, false, 10);
            subjects.forEach(subject -> {
                subjectList.add(new ListItem(subject.getTitle() + " : " + subject.getDescription()));
            });

            semesterTitle.add(subjectList);
            document.add(semesterTitle);
        });
    }

    public static void export(HttpServletResponse response, ProgramModel programModel) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        Paragraph title = new Paragraph("Parcours", font);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        writeProgramInformation(document, programModel);

        Paragraph semesters = new Paragraph("Modules", font);
        document.add(semesters);
        writeSemestersData(document, programModel);

        document.close();
    }
}
