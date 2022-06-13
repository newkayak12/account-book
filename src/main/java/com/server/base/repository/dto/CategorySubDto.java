package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.base.common.validations.Validations;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class CategorySubDto implements Serializable {
    public void setUser(UserDto user) {
        if(!Objects.isNull(user)){
            this.user = user;
        }
    }

    @NotNull(message = "카테고리 No가 필요합니다.", groups = {Validations.removeSub.class})
    private  Long categorySubNo;
    @JsonIgnoreProperties(allowSetters = false, allowGetters = false)
    @JsonIgnore
    private  UserDto user;
    private  Long categoryNo;
    @NotNull(message = "서브 카테고리 이름은 필수 항목입니다.", groups = {Validations.saveSub.class})
    private  String category_etc2;
}
