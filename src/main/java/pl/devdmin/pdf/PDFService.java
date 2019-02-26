package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

public interface PDFService {
    void save(PDDocument document, File file) throws IOException;
}
