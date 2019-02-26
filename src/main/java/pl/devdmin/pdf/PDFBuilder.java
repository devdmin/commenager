package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;

public interface PDFBuilder {
    PDDocument build();
    File getFile();
}
