package pl.devdmin.pdf;

import org.junit.Before;
import org.mockito.Mock;
import pl.devdmin.core.acquisition.Acquisition;
import pl.devdmin.core.product.Product;

import java.math.BigDecimal;

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


}
