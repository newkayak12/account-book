package com.server.base.repository.bankCodeRepository;

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
    @Column(name = "Bcode")
    private String bankCode;
    @Column
    private String codeContents;
    @Builder
    public BankCode(String bankCode, String codeContents) {
        this.bankCode = bankCode;
        this.codeContents = codeContents;
    }
}
