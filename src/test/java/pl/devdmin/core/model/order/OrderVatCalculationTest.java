package pl.devdmin.core.model.order;

import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.Order;
import pl.devdmin.core.order.vatStrategies.*;
import pl.devdmin.core.product.Product;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderVatCalculationTest {
    private Order order;
    private Product product;

    @Before
    public void setUp(){
        product = Product.builder().build();
    }

    @Test
    public void Vat0Test(){
        order = Order.builder().product(product).amount(1).price(new BigDecimal("100.0")).vatRateStrategy(new Vat0()).build();
        assertEquals(order.getVatValue(), new BigDecimal("0"));

    }

    @Test
    public void Vat4Test(){
        order = Order.builder().product(product).amount(1).price(new BigDecimal("100.0")).vatRateStrategy(new Vat4()).build();
        assertEquals(order.getVatValue(), new BigDecimal("100.0").multiply(new BigDecimal("0.04")));
    }

    @Test
    public void Vat7Test(){
        order = Order.builder().product(product).amount(1).price(new BigDecimal("100.0")).vatRateStrategy(new Vat7()).build();
        assertEquals(order.getVatValue(), new BigDecimal("100.0").multiply(new BigDecimal("0.07")));
    }

    @Test
    public void Vat8Test(){
        order = Order.builder().product(product).amount(1).price(new BigDecimal("100.0")).vatRateStrategy(new Vat8()).build();
        assertEquals(order.getVatValue(), new BigDecimal("100.0").multiply(new BigDecimal("0.08")));
    }

    @Test
    public void Vat23Test(){
        order = Order.builder().product(product).amount(1).price(new BigDecimal("100.0")).vatRateStrategy(new Vat23()).build();
        assertEquals(order.getVatValue(), new BigDecimal("100.0").multiply(new BigDecimal("0.23")));
    }

}
