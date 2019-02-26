package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.order.pdf.PDFAcqusitionBuilder;
import pl.devdmin.core.product.Product;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PDFAcqusitionBuilderTest {

    private PDFBuilder pdfBuilder;

    private Acquisition acquisition;
    private Product product;
    @Before
    public void setUp(){
        product = new Product();
        product.setName("Product");
        product.setVatRate(23);
        acquisition = new Acquisition();
        acquisition.setAmount(2);
        acquisition.setProduct(product);
        acquisition.setPrice(new BigDecimal("9.99"));

        pdfBuilder = new PDFAcqusitionBuilder(acquisition);
    }

    @Test
    public void buildPDF(){
        PDDocument document = pdfBuilder.build();

        // test default version
        assertEquals(1.4f, document.getVersion(),0.0f);
    }
}
