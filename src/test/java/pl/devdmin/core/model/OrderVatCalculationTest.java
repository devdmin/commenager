package pl.devdmin.core.model;

import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.Order;
import pl.devdmin.core.order.vatStrategies.*;
import pl.devdmin.core.product.Product;
import static org.junit.Assert.*;

public class OrderVatCalculationTest {
    private Order order;
    private Product product;

    @Before
    public void setUp(){
        order = new Order();
        product = new Product();
        order.setProduct(product);
        order.setPrice(100.0);
    }

    @Test
    public void Vat0Test(){
        order.setVatRateStrategy(new Vat0());
        assertEquals(0, order.getVatValue(), 0);
    }

    @Test
    public void Vat4Test(){
        order.setVatRateStrategy(new Vat4());
        assertEquals(order.getPrice() * .04,order.getVatValue() , 0);
    }

    @Test
    public void Vat7Test(){
        order.setVatRateStrategy(new Vat7());
        assertEquals(order.getPrice() * .07,order.getVatValue() , 0);
    }

    @Test
    public void Vat8Test(){
        order.setVatRateStrategy(new Vat8());
        assertEquals(order.getPrice() * .08,order.getVatValue() , 0);
    }

    @Test
    public void Vat23Test(){
        order.setVatRateStrategy(new Vat23());
        assertEquals( order.getPrice() * .23,order.getVatValue() ,0);
    }

}
