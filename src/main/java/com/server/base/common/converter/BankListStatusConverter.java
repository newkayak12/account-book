package com.server.base.common.converter;

import com.server.base.common.enums.BankListStatus;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class BankListStatusConverter implements AttributeConverter<BankListStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(BankListStatus bankListStatus) {
        if(Objects.isNull(bankListStatus)){
            return null;
        }
        return bankListStatus.getCode();
    }

    @Override
    public BankListStatus convertToEntityAttribute(Integer integer) {
        return Arrays.stream(BankListStatus.values()).filter(item->integer.equals(item.getCode())).findFirst()
                .orElseGet(null);
    }
}
