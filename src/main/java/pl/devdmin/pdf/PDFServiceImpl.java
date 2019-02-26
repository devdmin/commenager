package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public class PDFServiceImpl implements PDFService {

    public void save(PDDocument document, File file) throws IOException {
        document.save(file);
        document.close();
    }
}
