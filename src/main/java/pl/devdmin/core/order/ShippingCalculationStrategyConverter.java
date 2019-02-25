package pl.devdmin.core.order;

import pl.devdmin.core.order.shippingStrategies.AllegroInpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.InpostShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaCOAShippingCalculationStrategy;
import pl.devdmin.core.order.shippingStrategies.PocztaPolskaShippingCalculationStrategy;

import javax.persistence.AttributeConverter;

public class ShippingCalculationStrategyConverter implements AttributeConverter<ShippingCalculationStrategy, String>{
    @Override
    public String convertToDatabaseColumn(ShippingCalculationStrategy shippingCalculationStrategy) {
        return shippingCalculationStrategy.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public ShippingCalculationStrategy convertToEntityAttribute(String dbData) {
        if(dbData.equals("allegroinpostshippingcalculationstrategy")){
            return new AllegroInpostShippingCalculationStrategy();
        } else if(dbData.equals("inpostshippingcalculationstrategy")){
            return new InpostShippingCalculationStrategy();
        } else if(dbData.equals("pocztapolskacoashippingcalculationstrategy")){
            return new PocztaPolskaCOAShippingCalculationStrategy();
        } else if(dbData.equals("pocztapolskashippingcalculationstrategy")){
            return new PocztaPolskaShippingCalculationStrategy();
        }else {
            return null;
        }
    }
}
