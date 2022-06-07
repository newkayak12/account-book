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
public class MyMoneyDto implements Serializable {
    private  Long myMoneyNo;
    private  Long userNo;
    private  String totalprice;
}
