package pl.devdmin.core.model.order;

import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.*;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.Vat23;
import pl.devdmin.core.product.Product;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderCalculationTest {
    private Order order;
    private Product product;
    @Before
    public void setUp(){
        order = new Order();
        product = new Product();
        order.setProduct(product);
        order.setAmount(1);
        order.setPrice(new BigDecimal("100.0"));
        order.setVatRateStrategy(new Vat23());
        order.setShippingCalculationStrategy(new AllegroInpostShippingCalculationStrategy());
    }

    @Test
    public void testTotalCostCalculation(){
        assertEquals(order.getTotalPrice(), (order.getPrice().multiply(new BigDecimal("0.23"))).add(order.getPrice()).add(new BigDecimal("8.99")));
    }
}
