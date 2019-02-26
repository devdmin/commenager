package pl.devdmin.core.order.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.pdf.PDFBuilder;

import java.io.File;

public class PDFAcqusitionBuilder implements PDFBuilder {
    private Acquisition acquisition;

    public PDFAcqusitionBuilder(Acquisition acquisition) {
        this.acquisition = acquisition;
    }

    public PDDocument build() {
        return new PDDocument();
    }

    public File getFile() {
        return null;
    }
}
