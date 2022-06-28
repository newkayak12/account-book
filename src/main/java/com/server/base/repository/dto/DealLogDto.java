package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties(value = {"user"}, allowSetters = true, allowGetters = false)
@Builder
public class DealLogDto implements Serializable {
    private  Long dealLogNo;
    private  UserDto user;
    private  CategoryDto category;
    private  Integer dealPrice;
    private  String dealContent;
    private  Boolean isOutcome;
    private  LocalDate dealDate;
}
