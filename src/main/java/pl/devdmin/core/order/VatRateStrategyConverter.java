package pl.devdmin.core.order;

import pl.devdmin.core.order.vatStrategies.*;

import javax.persistence.AttributeConverter;

public class VatRateStrategyConverter implements AttributeConverter<VatRateStrategy, String> {

    @Override
    public String convertToDatabaseColumn(VatRateStrategy vatRateStrategy) {
        return vatRateStrategy.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public VatRateStrategy convertToEntityAttribute(String dbData) {
        if(dbData.equals("vat0")){
            return new Vat0();
        } else if(dbData.equals("vat4")){
            return new Vat4();
        } else if(dbData.equals("vat7")){
            return new Vat7();
        } else if(dbData.equals("vat8")){
            return new Vat8();
        }else if(dbData.equals("vat23")){
            return new Vat23();
        }else {
            return null;
        }
    }
}
