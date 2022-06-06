package com.server.base.common.converter;

import com.server.base.common.enums.RefBankCode;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class BankCodeConverter implements AttributeConverter<RefBankCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RefBankCode refBankCode) {
        if(Objects.isNull(refBankCode)){
            return null;
        }
        return refBankCode.getBcode();
    }

    @Override
    public RefBankCode convertToEntityAttribute(Integer integer) {
        return Arrays.stream(RefBankCode.values()).filter(item->integer.equals(item.getBcode())).findFirst().orElseGet(null);
    }
}
