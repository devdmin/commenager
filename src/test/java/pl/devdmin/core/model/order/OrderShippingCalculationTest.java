package pl.devdmin.core.model.order;

import org.assertj.core.api.BigDecimalAssert;
import org.junit.Before;
import org.junit.Test;
import pl.devdmin.core.order.Order;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderShippingCalculationTest {
    private Order order;

    @Test
    public void testInpostShippingCalculationStrategy(){
        order = Order.builder().shippingCalculationStrategy(new InpostShippingCalculationStrategy()).build();
        BigDecimal shippingCost = order.getShippingCost();
        assertEquals(shippingCost, new BigDecimal("13.76"));
    }

    @Test
    public void testAllegroInpostShippingCalculationStrategy(){
        order = Order.builder().shippingCalculationStrategy(new AllegroInpostShippingCalculationStrategy()).build();
        BigDecimal shippingCost = order.getShippingCost();
        assertEquals(shippingCost, new BigDecimal("8.99"));
    }

    @Test
    public void testPocztaPolskaCalculationStrategy(){
        order = Order.builder().shippingCalculationStrategy(new PocztaPolskaShippingCalculationStrategy()).build();
        BigDecimal shippingCost = order.getShippingCost();
        assertEquals(shippingCost, new BigDecimal("5.20"));
    }

    @Test
    public void testPocztaPolskaCOACalculationStrategy(){
        order = Order.builder().shippingCalculationStrategy(new PocztaPolskaCOAShippingCalculationStrategy()).build();
        BigDecimal shippingCost = order.getShippingCost();
        assertEquals(shippingCost, new BigDecimal("16.80"));
    }
}
