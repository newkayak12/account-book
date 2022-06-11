package com.server.base.common.converter;

import com.server.base.common.enums.RefPaymentType;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class PaymentTypeConverter implements AttributeConverter<RefPaymentType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RefPaymentType refPaymentType) {
         if(Objects.isNull(refPaymentType)){
             return null;
         }
         return refPaymentType.getCode();
    }

    @Override
    public RefPaymentType convertToEntityAttribute(Integer integer) {
        return Arrays.stream(RefPaymentType.values()).filter(item->integer.equals(item.getCode())).findFirst().orElseGet(null);
    }
}
