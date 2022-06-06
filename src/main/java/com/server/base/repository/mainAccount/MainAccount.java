package com.server.base.repository.mainAccount;

import com.server.base.common.enums.RefAccountCode;
import com.server.base.common.enums.RefBankCode;
import com.server.base.common.enums.RefPaymentType;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.myMoneyRepository.MyMoney;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

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
public class MainAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mainAccountNo;
    private Long userNo;
    private String userId;
    @Column(nullable = false)
    private LocalDateTime mainAccountDate;
    @Column(nullable = false)
    private String mainAccountPrice;
    @Transient
    private Boolean isIncome;
    @OneToOne
    @JoinColumn(name = "my_money_no", referencedColumnName = "my_money_no")
    private MyMoney mainAccountTotalPrice;
    @OneToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;
//    Converter
    @Column(name = "main_account_bank_code")
    private RefBankCode mainAccountBankCode;
//    converter
    @Column(name = "main_account_account_code")
    private RefAccountCode mainAccountCode;
//    converter
    @Column(name = "main_account_payment_type")
    private RefPaymentType paymentType;

    private String mainAccountContents;

}
