package com.server.base.repository.accountCodeRepository;

import com.server.base.common.converter.AccountCodeConverter;
import com.server.base.common.enums.RefAccountCode;
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
public class AccountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountCodeNo;
    @Convert(converter = AccountCodeConverter.class)
    @Column(name = "Ccode")
    private RefAccountCode cCode;
    @Column
    private String codeCode;

    @Builder
    public AccountCode(RefAccountCode cCode, String codeCode) {
        this.cCode = cCode;
        this.codeCode = codeCode;
    }

}
