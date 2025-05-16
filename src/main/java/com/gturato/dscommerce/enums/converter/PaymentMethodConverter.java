package com.gturato.dscommerce.enums.converter;

import com.gturato.dscommerce.enums.PaymentMethod;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PaymentMethodConverter implements AttributeConverter<PaymentMethod, String> {
    @Override
    public String convertToDatabaseColumn(PaymentMethod paymentMethod) {
        if(paymentMethod == null){
            return null;
        }
        return paymentMethod.getCode();
    }

    @Override
    public PaymentMethod convertToEntityAttribute(String s) {
        if(s == null) return null;

        return Stream.of(PaymentMethod.values())
                .filter(c -> c.getCode().equals(s))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }
}
