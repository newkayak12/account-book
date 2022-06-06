package com.server.base.repository.bankListRepository;

import com.server.base.common.converter.BankListStatusConverter;
import com.server.base.common.enums.BankListStatus;
import com.server.base.repository.bankCodeRepository.BankCode;
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
public class BankList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankListNo;
    @Column(name = "user_no")
    private Long userNo;
    private String userId;
    @OneToOne
    @JoinColumn(name = "Bcode", referencedColumnName = "bank_code_no")
    private BankCode bCode;
//converter
    @Convert(converter = BankListStatusConverter.class)
    @Column(name = "stts")
    private BankListStatus bankListStatus;
}
