package com.server.base.common.converter;

import com.server.base.common.enums.RefWeekday;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class WeekConverter implements AttributeConverter<RefWeekday, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RefWeekday refWeekday) {
        if(Objects.isNull(refWeekday)){
            return null;
        }
        return refWeekday.getCode();
    }

    @Override
    public RefWeekday convertToEntityAttribute(Integer integer) {
        return Arrays.stream(RefWeekday.values()).filter(item->integer.equals(item.getCode())).findFirst().orElseGet(null);
    }
}
