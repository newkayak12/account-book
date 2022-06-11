package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class CategoryDto implements Serializable {
    private  Long categoryNo;
    private  Long userNo;
    private  String category_etc1;
    private  String category_etc2;
    private  Boolean categoryIsDefault;
}
