package pl.devdmin.core.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;
import pl.devdmin.core.order.vatStrategies.*;

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
        order.setShippingCalculationStrategy(chooseShippingStrategy(order));
        order.setVatRateStrategy(chooseVatRateStrategy(order));
        orderRepository.save(order);
    }

    private VatRateStrategy chooseVatRateStrategy(Order order){
        switch (order.getProduct().getVatRate()){
            case 0:
                vatRateStrategy = new Vat0();
                break;
            case 4:
                vatRateStrategy = new Vat4();
                break;
            case 7:
                vatRateStrategy = new Vat7();
                break;
            case 8:
                vatRateStrategy = new Vat8();
                break;
            case 23:
                vatRateStrategy = new Vat23();
                break;

        }
        return vatRateStrategy;
    }
    private ShippingCalculationStrategy chooseShippingStrategy(Order order){
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
        return shippingCalculationStrategy;
    }
}
