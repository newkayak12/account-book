package com.server.base.common.converter;

import com.server.base.common.enums.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.Objects;

@Converter(autoApply = true)
public class UserStatusConverter implements AttributeConverter<UserStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserStatus userStatus) {
        if(Objects.isNull(userStatus)){
            return null;
        }
        return userStatus.getStatus();
    }

    @Override
    public UserStatus convertToEntityAttribute(Integer integer) {
        return Arrays.stream(UserStatus.values())
                .filter(v->integer.equals(v.getStatus())).findFirst().orElseGet(null);
    }
}
