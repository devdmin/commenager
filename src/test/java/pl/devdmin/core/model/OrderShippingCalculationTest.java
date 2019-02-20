package pl.devdmin.core.model;

import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.Order;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;

import static org.junit.Assert.assertTrue;

public class OrderShippingCalculationTest {
    private Order order;

    @Before
    public void setUp(){
        order = new Order();
    }
    @Test
    public void testInpostShippingCalculationStrategy(){
        order.setShippingCalculationStrategy(new InpostShippingCalculationStrategy());
        double shippingCost = order.getShippingCost();
        assertTrue(13.76 == shippingCost);
    }

    @Test
    public void testAllegroInpostShippingCalculationStrategy(){
        order.setShippingCalculationStrategy(new AllegroInpostShippingCalculationStrategy());
        double shippingCost = order.getShippingCost();
        assertTrue(8.99 == shippingCost);
    }

    @Test
    public void testPocztaPolskaCalculationStrategy(){
        order.setShippingCalculationStrategy(new PocztaPolskaShippingCalculationStrategy());
        double shippingCost = order.getShippingCost();
        assertTrue(5.20 == shippingCost);
    }

    @Test
    public void testPocztaPolskaCOACalculationStrategy(){
        order.setShippingCalculationStrategy(new PocztaPolskaCOAShippingCalculationStrategy());
        double shippingCost = order.getShippingCost();
        assertTrue(16.80 == shippingCost);
    }
}
