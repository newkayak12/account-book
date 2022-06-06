package com.server.base.repository.bankCodeRepository;

import com.server.base.common.enums.RefBankCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@DynamicUpdate
@DynamicInsert
public class BankCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_code_no")
    private Long bankCodeNo;
//    converter
    @Column(name = "Bcode")
    private RefBankCode bankCode;
    @Column
    private String codeContents;
}
