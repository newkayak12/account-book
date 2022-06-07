package com.server.base.repository.dto;

import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.enums.RefBankCode;
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
public class BankCodeDto implements Serializable {
    private  Long bankCodeNo;
    private  RefBankCode bankCode;
    private  String codeContents;
}
