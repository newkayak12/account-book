package com.server.base.common.converter;

import com.server.base.common.enums.RefAccountCode;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class AccountCodeConverter implements AttributeConverter<RefAccountCode, String> {

    @Override
    public String convertToDatabaseColumn(RefAccountCode refAccountCode) {
        if(!Objects.isNull(refAccountCode)){
            return null;
        }
        return refAccountCode.getCCode();
    }

    @Override
    public RefAccountCode convertToEntityAttribute(String string) {
        return Arrays.stream(RefAccountCode.values())
                .filter(v->string.equals(v.getCCode())).findFirst().orElseGet(null);
    }
}
