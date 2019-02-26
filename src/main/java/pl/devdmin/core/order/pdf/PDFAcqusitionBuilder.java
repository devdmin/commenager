package pl.devdmin.core.order.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;
import java.io.IOException;

import java.util.Set;

public class PDFAcqusitionBuilder implements PDFBuilder<Acquisition> {
    private Set<Acquisition> acquisitionSet;
    private PDDocument document;

    public PDDocument build(Set<Acquisition> acquisitionSet) {
        this.acquisitionSet = acquisitionSet;
        this.document = createDocument();
        return document;
    }

    private PDDocument createDocument(){
        document = new PDDocument();
        createHeading(document);
        return document;
    }

    private void createHeading(PDDocument document) {
        PDPage page = new PDPage();
        document.addPage(page);

        try {
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.TIMES_ROMAN, 14);
            contentStream.beginText();
            contentStream.newLineAtOffset(250.0f,600.0f);
            contentStream.showText("TEKST TEKST TEKST");
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
