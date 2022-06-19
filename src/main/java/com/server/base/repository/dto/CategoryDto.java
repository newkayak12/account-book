package com.server.base.repository.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto implements Serializable {
    private final Integer id;
    private final UserDto user;
    private final String cateName;
    private final Boolean cateFlag;
    private final String cateImage;
}
