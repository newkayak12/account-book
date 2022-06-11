package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.RefAccountCode;
import com.server.base.common.enums.RefPaymentType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class MainAccountDto implements Serializable {
    private  Long mainAccountNo;
    private  Long userNo;
    private  String userId;
    private  LocalDateTime mainAccountDate;
    private  String mainAccountPrice;
    private  MyMoneyDto mainAccountTotalPrice;
    private  CategoryDto category;
    private  String mainAccountBankCode;
    private  String mainAccountBankContents;
    private  RefAccountCode mainAccountCode;
    private  RefPaymentType paymentType;
    private  String mainAccountContents;
}
