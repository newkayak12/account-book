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
public class CategorySubDto implements Serializable {
    private  Long categorySubNo;
    private  CategoryDto category;
    private  String category_etc2;
}
