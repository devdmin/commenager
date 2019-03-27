package pl.devdmin.core.model.order;

import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.*;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.Vat23;
import pl.devdmin.core.product.Product;
import pl.devdmin.snapshot.OrderSnapshot;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderCalculationTest {
    private OrderSnapshot order;
    private Product product;
    @Before
    public void setUp(){
        product = Product.builder().build();
        order = Order.builder()
                .product(product)
                .amount(1)
                .price(new BigDecimal("100.0"))
                .vatRateStrategy(new Vat23())
                .shippingCalculationStrategy(new AllegroInpostShippingCalculationStrategy())
                .build().toSnapshot();

    }
    @Test
    public void testTotalCostCalculation(){
        assertEquals(order.getTotalPrice(), (order.getPrice().multiply(new BigDecimal("0.23"))).add(order.getPrice()).add(new BigDecimal("8.99")));
    }
}
