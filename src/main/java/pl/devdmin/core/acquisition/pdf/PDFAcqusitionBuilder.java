package pl.devdmin.core.acquisition.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class PDFAcqusitionBuilder implements PDFBuilder<Acquisition> {
    private Set<Acquisition> acquisitionSet;
    private PDDocument document;
    private final static String HEADING_TEXT = "ACQUSITUION RAPORT";

    public PDDocument build(Set<Acquisition> acquisitionSet) {
        this.acquisitionSet = acquisitionSet;
        this.document = createDocument();
        return document;
    }

    private PDDocument createDocument(){
        document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        createHeading(document, page);
        return document;
    }

    private void createHeading(PDDocument document, PDPage page) {
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println();
        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(200.0f,800.0f);
            contentStream.showText(HEADING_TEXT);
            contentStream.endText();

            contentStream.setFont(PDType1Font.TIMES_ROMAN, 10);
            contentStream.beginText();
            contentStream.newLineAtOffset(450.0f,800.0f);
            contentStream.showText("Generated: " + formattedDate);
            contentStream.endText();

            contentStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public File getFile() {
        return null;
    }
}
