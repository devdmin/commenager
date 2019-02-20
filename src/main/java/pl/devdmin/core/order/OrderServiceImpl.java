package pl.devdmin.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;

@Component
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private ShippingCalculationStrategy shippingCalculationStrategy;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        switch (order.getShippingMethod()) {
            case INPOST:
                shippingCalculationStrategy = new InpostShippingCalculationStrategy();
                break;
            case ALLEGROINPOST:
                shippingCalculationStrategy = new AllegroInpostShippingCalculationStrategy();
                break;
            case POCZTAPOLSKA_COA:
                shippingCalculationStrategy = new PocztaPolskaCOAShippingCalculationStrategy();
                break;
            case POCZTAPOLSKA:
                shippingCalculationStrategy = new PocztaPolskaShippingCalculationStrategy();
                break;

        }
        order.setShippingCalculationStrategy(shippingCalculationStrategy);
        orderRepository.save(order);
    }

}
