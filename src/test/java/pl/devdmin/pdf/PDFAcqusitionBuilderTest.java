package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.pdf.builder.PDFAcqusitionBuilder;
import pl.devdmin.core.product.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class PDFAcqusitionBuilderTest {

    private PDFBuilder pdfBuilder;
    private  Set<Acquisition> acquisitionSet;
    private Acquisition acquisition;
    private Product product;
    private PDDocument document;
    @Before
    public void setUp(){
        product = Product.builder().name("EXAMPLE PRODUCT NAME").vatRate(23).build();

        acquisitionSet = new HashSet<Acquisition>();

        for(int i = 0; i < 50;i++){
            acquisitionSet.add(Acquisition.builder().id(Long.valueOf(i)).product(product).buyDate(LocalDate.now().minusDays(i)).price(new BigDecimal(String.valueOf(i))).build());
        }

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
        for(Acquisition acquisition: acquisitionSet) {
            assertDocumentContains(document, acquisition.getProduct().getName());
            assertDocumentContains(document, String.valueOf(acquisition.getAmount()));
            assertDocumentContains(document, acquisition.getPrice().toString());
            assertDocumentContains(document, acquisition.getTotalPrice().toString());
        }

     }

     @Test
     public void testAmountOfPages() throws IOException{
         assertTrue(document.getNumberOfPages() >= (acquisitionSet.size()/36));
     }

    private void assertDocumentContains(PDDocument document, String text) throws IOException {
        assertThat(new PDFTextStripper().getText(document), containsString(text));
    }

}
