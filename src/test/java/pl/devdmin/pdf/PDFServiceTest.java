package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class PDFServiceTest {

    @Mock
    private PDDocument pdfDocument;

    private PDFService pdfService;

    @Mock
    private File file;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        pdfService = new PDFServiceImpl();
    }


    @Test
    public void testSaving() throws IOException {
        doNothing().when(pdfDocument).save(isA(File.class));

        pdfService.save(pdfDocument, file);

        Mockito.verify(pdfDocument, Mockito.times(1)).save(any(File.class));
        Mockito.verify(pdfDocument, Mockito.times(1)).close();
    }


}
