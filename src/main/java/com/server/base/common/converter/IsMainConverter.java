package com.server.base.common.converter;

import com.server.base.common.enums.IsMain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter
public class IsMainConverter implements AttributeConverter<IsMain, Integer> {

    @Override
    public Integer convertToDatabaseColumn(IsMain isMain) {
        if(!Objects.isNull(isMain)){
            return null;
        }
        return isMain.getCode();
    }

    @Override
    public IsMain convertToEntityAttribute(Integer integer) {
        return Arrays.stream(IsMain.values())
                .filter(v->integer.equals(v.getCode())).findFirst().orElseGet(null);
    }
}
