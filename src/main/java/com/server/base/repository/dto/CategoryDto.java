package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
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
@AuthorizeDto
@Builder
@ToString
public class CategoryDto implements Serializable {
    @NotNull(message = "카테고리 No가 필요합니다.", groups = {Validations.removeMain.class, Validations.fetchSub.class})
    private  Long categoryNo;
    private  UserDto user;
    @NotNull(message = "카테고리 이름은 필수 항목입니다.", groups = {Validations.saveMain.class, Validations.saveSub.class})
    private  String category_etc1;
    private  List<CategorySubDto> category_etc2_list;
    private  Boolean isIncome;
    @NotNull(message = "아이콘은 필수 항목입니다.", groups = {Validations.saveMain.class})
    private  String categoryImage;
}
