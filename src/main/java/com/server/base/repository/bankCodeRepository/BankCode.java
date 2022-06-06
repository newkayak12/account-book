package com.server.base.repository.bankCodeRepository;

import com.server.base.common.converter.BankCodeConverter;
import com.server.base.common.enums.RefBankCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@ToString
@DynamicUpdate
@DynamicInsert
public class BankCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_code_no")
    private Long bankCodeNo;
    @Convert(converter = BankCodeConverter.class)
    @Column(name = "Bcode")
    private RefBankCode bankCode;
    @Column
    private String codeContents;
    @Builder
    public BankCode(RefBankCode bankCode, String codeContents) {
        this.bankCode = bankCode;
        this.codeContents = codeContents;
    }
}
