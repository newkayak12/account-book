package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(value = {"user"}, allowSetters = true, allowGetters = false)
public class CategoryDto implements Serializable {

    private  Long categoryNo;
    private  UserDto user;
    private  String cateName;
    private  Boolean cateFlag;
    private  Boolean cateIsBasic;
    private  String cateImage;
}
