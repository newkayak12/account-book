package com.server.base.repository.dto;

import com.server.base.common.enums.BankListStatus;
import com.server.base.common.validations.Validations;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class BankListDto implements Serializable {
    private Long bankListNo;
    private Long userNo;
    private String userId;
    @NotNull(message = "필수 항목입니다.", groups = {Validations.saveBankList.class})
    private BankCodeDto bCode;
    @NotNull(message = "필수 항목입니다.", groups = {Validations.saveBankList.class})
    private BankListStatus bankListStatus;
}
