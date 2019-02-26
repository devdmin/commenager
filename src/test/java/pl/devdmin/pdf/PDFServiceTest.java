package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import static org.mockito.Matchers.any;

public class PDFServiceTest {

    @Mock
    private PDDocument pdfDocument;

    private PDFService pdfService;


    @Before
    public void setUp(){
        pdfService = new PDFServiceImpl;
    }


    @Test
    public void testSaving() throws IOException {
        pdfService.save(pdfDocument, File file);
        Mockito.verify(pdfDocument, Mockito.times(1)).save(any(File.class));
        Mockito.verify(pdfDocument, Mockito.times(1)).close();
    }


}
