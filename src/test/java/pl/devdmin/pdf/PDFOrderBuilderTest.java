package pl.devdmin.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.Order;
import pl.devdmin.pdf.builder.PDFOrderBuilder;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.Vat23;
import pl.devdmin.core.product.Product;
import pl.devdmin.snapshot.OrderSnapshot;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PDFOrderBuilderTest {
    private PDFBuilder pdfBuilder;
    private Set<OrderSnapshot> orderSet;
    private Product product;
    private PDDocument document;
    @Before
    public void setUp(){
        product = Product.builder().name("EXAMPLE PRODUCT NAME").vatRate(23).build();

        orderSet = new HashSet<OrderSnapshot>();

        orderSet.add(Order.builder()
                .product(product)
                .shippingCalculationStrategy(new AllegroInpostShippingCalculationStrategy())
                .amount(3)
                .address("Warszawska 3 43-143 Poznan")
                .client("Joe Doe")
                .price(new BigDecimal("4.02"))
                .vatRateStrategy(new Vat23())
                .build().toSnapshot());

        pdfBuilder = new PDFOrderBuilder();
        document = pdfBuilder.build(orderSet);
    }

    @Test
    public void testDeafultVersion() throws IOException {
        // test default version
        assertEquals(1.4f, document.getVersion(),0.0f);
    }

    @Test
    public void testHeadingText() throws IOException{
        assertDocumentContains(document, "ORDER RAPORT");
    }

    @Test
    public void testHeadingDate() throws IOException{
        assertDocumentContains(document, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Test
    public void testNameFile() throws IOException{
        assertThat(pdfBuilder.getFile().getName(), startsWith(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        assertThat(pdfBuilder.getFile().getName(), endsWith("order.pdf"));
    }

    @Test
    public void testHeadingwTable() throws IOException{
        assertDocumentContains(document,"Date");
        assertDocumentContains(document, "Product");
        assertDocumentContains(document, "Amount");
        assertDocumentContains(document, "Price");
        assertDocumentContains(document, "VAT");
        assertDocumentContains(document, "Shipping Cos");
        assertDocumentContains(document, "Total Price");
        assertDocumentContains(document, "Address");
    }

    @Test
    public void testDrawTable() throws IOException{
        for(OrderSnapshot order: orderSet) {
            assertDocumentContains(document, order.getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            assertDocumentContains(document, order.getProduct().getName());
            assertDocumentContains(document, String.valueOf(order.getAmount()));
            assertDocumentContains(document, order.getPrice().toString());
            assertDocumentContains(document, String.valueOf(order.getVatValue()));
            assertDocumentContains(document, order.getShippingCost().toString());
            assertDocumentContains(document, order.getTotalPrice().toString());
            assertDocumentContains(document, order.getAddress());
        }

    }

    @Test
    public void testSumTotalPrice() throws IOException{
        BigDecimal sumTotalPrice = new BigDecimal("0");
        for (OrderSnapshot order : orderSet) {
            sumTotalPrice.add(order.getTotalPrice());
        }

        assertDocumentContains(document, sumTotalPrice.toString());
    }

    @Test
    public void testAmountOfPages() throws IOException{
        document.save("test.pdf");
        assertTrue(document.getNumberOfPages() >= (orderSet.size()/36));
    }


    private void assertDocumentContains(PDDocument document, String text) throws IOException {
        assertThat(new PDFTextStripper().getText(document), containsString(text));
    }


}
