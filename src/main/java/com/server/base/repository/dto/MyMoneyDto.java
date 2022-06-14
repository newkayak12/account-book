package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.IsMain;
import com.server.base.common.validations.Validations;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class MyMoneyDto implements Serializable {
    public void setIsMain(Integer isMain) {
        this.isMain = Arrays.stream(IsMain.values()).filter(item->{
            return isMain.equals(item.getCode());
        }).findFirst().orElseGet(null);
    }

    @NotNull(message = "필수 항목입니다.", groups = {Validations.RemoveMyMoney.class})
    private  Long myMoneyNo;
    @JsonIgnore
    private  UserDto user;

    @NotNull(message = "필수 항목입니다.", groups = {Validations.SaveMyMoney.class})
    private  String totalprice;
    private IsMain isMain;

    private  CategoryDto category;

    @JsonIgnore
    private  LocalDate date;
}
