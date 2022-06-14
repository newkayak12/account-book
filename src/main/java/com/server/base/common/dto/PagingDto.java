package com.server.base.common.dto;


import com.server.base.common.enums.Type;
import com.server.base.common.validations.Validations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto {
    @NotNull(message = "필수 항목입니다.", groups = {Validations.RegDate.class})
    public void setRegDate(String regDate) {
        String[] date = regDate.split(" ~ ");
        this.startDate = LocalDate.parse(date[0]);
        this.endDate = LocalDate.parse(date[0]);
        if(date.length>1){
            this.endDate = LocalDate.parse(date[1]);
        }
    }

    public void setType(Integer type) {
        this.type = Arrays.stream(Type.values()).filter(item->type.equals(item.getCode())).findFirst().orElseGet(null);
    }

    @NotNull(message = "페이지 번호는 필수 항목입니다.", groups = {Validations.Paging.class})
    private Integer page;
    @NotNull(message = "페이지 당 개수는 필수 항목입니다.", groups = {Validations.Paging.class})
    private Integer limit;
    private Type type;
    private String searchText;
    private LocalDate startDate;
    private LocalDate endDate;
}
