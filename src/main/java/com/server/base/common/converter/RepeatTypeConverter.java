package com.server.base.common.converter;

import com.server.base.common.enums.RefRepeatType;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class RepeatTypeConverter implements AttributeConverter<RefRepeatType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RefRepeatType refRepeatType) {
        if(Objects.isNull(refRepeatType)){
            return null;
        }
        return refRepeatType.getCode();
    }

    @Override
    public RefRepeatType convertToEntityAttribute(Integer integer) {
        return Arrays.stream(RefRepeatType.values()).filter(item->integer.equals(item.getCode())).findFirst().orElseGet(null);
    }
}
