package com.server.base.repository.dto;

import com.server.base.common.enums.RefAccountCode;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountCodeDto implements Serializable {
    private final Long accountCodeNo;
    private final RefAccountCode cCode;
    private final String codeCode;
}
