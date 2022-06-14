package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.base.common.validations.Validations;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class CategoryDto implements Serializable {

    @NotNull(message = "카테고리 No가 필요합니다.", groups = {Validations.RemoveMain.class, Validations.FetchSub.class})
    private  Long categoryNo;
    @JsonIgnoreProperties(allowSetters = false, allowGetters = true)
    private  UserDto user;
    @NotNull(message = "카테고리 이름은 필수 항목입니다.", groups = {Validations.SaveMain.class, Validations.SaveSub.class})
    private  String category_etc1;
    @JsonIgnoreProperties(allowSetters = false, allowGetters = true)
    private  List<CategorySubDto> category_etc2_list;
    @NotNull(message = "수입/지출은 필수 항목입니다.", groups = {Validations.SaveMain.class})
    private  Boolean isIncome;
    @NotNull(message = "아이콘은 필수 항목입니다.", groups = {Validations.SaveMain.class})
    private  String categoryImage;
}
