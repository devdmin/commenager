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
        order = new Order();
        product = new Product();
        order.setProduct(product);
        order.setAmount(1);
        order.setPrice(new BigDecimal("100.0"));
    }

    @Test
    public void Vat0Test(){
        order.setVatRateStrategy(new Vat0());
        assertEquals(order.getVatValue(), new BigDecimal("0"));

    }

    @Test
    public void Vat4Test(){
        order.setVatRateStrategy(new Vat4());
        assertEquals(order.getVatValue(), order.getPrice().multiply(new BigDecimal("0.04")));
    }

    @Test
    public void Vat7Test(){
        order.setVatRateStrategy(new Vat7());
        assertEquals(order.getVatValue(), order.getPrice().multiply(new BigDecimal("0.07")));
    }

    @Test
    public void Vat8Test(){
        order.setVatRateStrategy(new Vat8());
        assertEquals(order.getVatValue(), order.getPrice().multiply(new BigDecimal("0.08")));
    }

    @Test
    public void Vat23Test(){
        order.setVatRateStrategy(new Vat23());
        assertEquals(order.getVatValue(), order.getPrice().multiply(new BigDecimal("0.23")));
    }

}
