package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.UserStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class MyMoneyDto implements Serializable {
    private  Long myMoneyNo;
    private  UserDto user;
    private  CategoryDto category;
    private  String totalprice;
    private  LocalDate date;
}
