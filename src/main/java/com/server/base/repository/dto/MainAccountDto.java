package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.RefPaymentType;
import com.server.base.common.enums.RefRepeatType;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class MainAccountDto implements Serializable {
    public void setPaymentType(Integer paymentType) {
        this.paymentType = Arrays.stream(RefPaymentType.values()).filter(item->paymentType.equals(item.getCode())).findFirst().orElseGet(null);
    }

    public void setLoopType(Integer loopType) {
        this.loopType = Arrays.stream(RefRepeatType.values()).filter(item->loopType.equals(item.getCode())).findFirst().orElseGet(null);
    }

    private  Long mainAccountNo;
    private  Long userNo;
    private  String userId;
    private  String mainAccountPrice;
    private  CategoryDto category;
    private  String mainAccountBankCode;
    private  String mainAccountBankContents;
    private  String mainAccountContents;
    private  RefPaymentType paymentType;
    private  RefRepeatType loopType;
    private  LocalDateTime mainAccountDate;
    private  LocalDate loopEndDate;
    private Boolean isIncome;
}
