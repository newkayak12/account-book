package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.RefPaymentType;
import com.server.base.common.enums.RefRepeatType;
import com.server.base.common.enums.RefWeekday;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class DepositAccountDto implements Serializable {
    private  Long depositAccountNo;
    private  Long userNo;
    private  Long depositAccountPrice;
    private  LocalDate depositAccountDate;
    private  LocalDate depositAccountStartDate;
    private  LocalDate depositAccountEndDate;
    private  BankCodeDto bankCode;
    private  String depositAccountContents;
    private  RefPaymentType paymentType;
    private  Integer depositAccountLoopCountNow;
    private  Integer depositAccountLoopCountTotal;
    private  Boolean isDeleted;
    private  RefRepeatType repeatPeriod;
    private  RefWeekday weekday;
}
