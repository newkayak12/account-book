package com.server.base.common.dto;


import com.server.base.common.validations.Validations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagingDto {
    public void setStartDate(String startDate) {
        if(Objects.isNull(startDate)||startDate.equals("")){
            this.startDate=null;
            return ;
        }
        this.startDate = LocalDate.parse(startDate);
    }
    public void setEndDate(String endDate) {
        if(Objects.isNull(endDate)||endDate.equals("")){
            this.endDate=null;
            return ;
        }
        this.endDate = LocalDate.parse(endDate);
    }
    @NotNull(message = "페이지 번호는 필수 항목입니다.", groups = {Validations.paging.class})
    private Integer page;
    @NotNull(message = "페이지 당 개수는 필수 항목입니다.", groups = {Validations.paging.class})
    private Integer limit;
    private String searchText;
    private LocalDate startDate;
    private LocalDate endDate;
}
