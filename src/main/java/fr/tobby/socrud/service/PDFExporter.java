package fr.tobby.socrud.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.List;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import fr.tobby.socrud.model.ProgramModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PDFExporter {

    public PDFExporter() {
    }

    private static void writeTableLine(PdfPTable table, String label, String value) throws DocumentException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.DARK_GRAY);
        cell.setPadding(5);

        PdfPCell contentCell = new PdfPCell();
        contentCell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setColor(Color.WHITE);

        Font fontContent = FontFactory.getFont(FontFactory.HELVETICA);
        fontContent.setColor(Color.BLACK);

        cell.setPhrase(new Phrase(label, font));
        table.addCell(cell);
        contentCell.setPhrase(new Phrase(value, fontContent));
        table.addCell(contentCell);
    }

    private void writeProgramInformation(Document document, ProgramModel programModel) throws DocumentException {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        writeTableLine(table, "Titre", programModel.getTitle());
        writeTableLine(table, "Description", programModel.getDescription());
        writeTableLine(table, "Campus", programModel.getCampus());
        writeTableLine(table, "Durée", programModel.getDurationMonths().toString());
        writeTableLine(table, "Type", programModel.getDegree());
        writeTableLine(table, "Tarif", String.valueOf(programModel.getPrice()));
        writeTableLine(table, "Présentiel", String.valueOf(programModel.getRemotePercentage()));
        writeTableLine(table, "Début", programModel.getStartDate().toString());

        document.add(table);
    }

    private void writeSemestersData(Document document, ProgramModel programModel) throws DocumentException {
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

    public void exportProgram(OutputStream outputStream, ProgramModel programModel) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, outputStream);

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
