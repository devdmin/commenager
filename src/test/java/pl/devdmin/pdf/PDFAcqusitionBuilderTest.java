package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.order.pdf.PDFAcqusitionBuilder;
import pl.devdmin.core.product.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


public class PDFAcqusitionBuilderTest {

    private PDFBuilder pdfBuilder;
    private  Set<Acquisition> acquisitionSet;
    private Acquisition acquisition;
    private Product product;
    private PDDocument document;
    @Before
    public void setUp(){
        product = new Product();
        product.setName("Product");
        product.setVatRate(23);
        acquisition = new Acquisition();
        acquisition.setAmount(2);
        acquisition.setProduct(product);
        acquisition.setPrice(new BigDecimal("9.99"));
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
    public void testHeading() throws IOException{
        assertHeadingContains(document, "Hello World");
    }

    private void assertHeadingContains(PDDocument document, String headingText) throws IOException {
        assertThat(new PDFTextStripper().getText(document), containsString(headingText));
    }

}
