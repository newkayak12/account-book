package com.server.base.repository.bankListRepository;

import com.server.base.common.converter.BankListStatusConverter;
import com.server.base.common.enums.BankListStatus;
import com.server.base.repository.bankCodeRepository.BankCode;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Builder
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
    @Convert(converter = BankListStatusConverter.class)
    @Column(name = "stts")
    private BankListStatus bankListStatus;

    @Builder
    public BankList(Long bankListNo, BankCode bCode, BankListStatus bankListStatus) {
        this.bankListNo = bankListNo;
        this.bCode = bCode;
        this.bankListStatus = bankListStatus;
    }
    public void setUser(User user){
        this.userId = user.getUserId();
        this.userNo = user.getUserNo();
    }
}
