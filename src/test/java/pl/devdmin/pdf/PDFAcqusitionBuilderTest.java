package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.acquisition.pdf.PDFAcqusitionBuilder;
import pl.devdmin.core.product.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class PDFAcqusitionBuilderTest {

    private PDFBuilder pdfBuilder;
    private  Set<Acquisition> acquisitionSet;
    private Acquisition acquisition, acquisition2;
    private Product product, product2;
    private PDDocument document;
    @Before
    public void setUp(){
        product = new Product();
        product.setName("EXAMPLE PRODUCT NAME");
        product.setVatRate(23);

        acquisition = new Acquisition();
        acquisition.setAmount(20);
        acquisition.setProduct(product);
        acquisition.setPrice(new BigDecimal("520.99"));

        acquisitionSet = new HashSet<Acquisition>(Arrays.asList(acquisition));

        pdfBuilder = new PDFAcqusitionBuilder();
        document = pdfBuilder.build(acquisitionSet);
    }

    @Test
    public void testDeafultVersion() throws IOException {
        // test default version
        assertEquals(1.4f, document.getVersion(),0.0f);
    }


    @Test
    public void testHeadingText() throws IOException{
        assertDocumentContains(document, "ACQUSITUION RAPORT");
    }

    @Test
    public void testHeadingDate() throws IOException{
        assertDocumentContains(document, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void testNameFile() throws IOException{
        assertThat(pdfBuilder.getFile().getName(), startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        assertThat(pdfBuilder.getFile().getName(), endsWith("acquisition.pdf"));
    }

    @Test
    public void testHeadingwTable() throws IOException{
        assertDocumentContains(document,"Product");
        assertDocumentContains(document, "Amount");
        assertDocumentContains(document, "Price");
        assertDocumentContains(document, "Total Price");
         }

    @Test
    public void testDrawTable() throws IOException{
        assertDocumentContains(document, acquisition.getProduct().getName());
        assertDocumentContains(document, String.valueOf(acquisition.getAmount()));
        assertDocumentContains(document, acquisition.getPrice().toString());
        assertDocumentContains(document, acquisition.getTotalPrice().toString());
    }

    private void assertDocumentContains(PDDocument document, String text) throws IOException {
        assertThat(new PDFTextStripper().getText(document), containsString(text));
    }

}
