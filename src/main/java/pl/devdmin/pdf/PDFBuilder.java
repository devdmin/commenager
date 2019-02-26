package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.util.Set;

public interface PDFBuilder<T> {
    PDDocument build(Set<T> t);
    File getFile();
}
