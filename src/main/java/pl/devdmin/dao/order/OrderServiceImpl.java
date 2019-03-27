package pl.devdmin.dao.order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devdmin.core.order.ShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ShippingCalculationStrategy shippingCalculationStrategy;
    private VatRateStrategy vatRateStrategy;
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
//        order.setShippingCalculationStrategy(chooseShippingStrategy(order));
//        order.setVatRateStrategy(chooseVatRateStrategy(order));
        orderRepository.save(order);
    }


    public Set<Order> findAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();
        return new HashSet<>(orders);
    }


}
