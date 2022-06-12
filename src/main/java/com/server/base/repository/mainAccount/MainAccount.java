package com.server.base.repository.mainAccount;

import com.server.base.common.converter.AccountCodeConverter;
import com.server.base.common.converter.PaymentTypeConverter;
import com.server.base.common.converter.RepeatTypeConverter;
import com.server.base.common.enums.RefAccountCode;
import com.server.base.common.enums.RefPaymentType;
import com.server.base.common.enums.RefRepeatType;
import com.server.base.repository.categoryRepository.Category;
import com.server.base.repository.myMoneyRepository.MyMoney;
import com.server.base.repository.userRepository.User;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
@EntityListeners(AuditingEntityListener.class)
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

    @OneToOne
    @JoinColumn(name = "category_no", referencedColumnName = "category_no")
    private Category category;

    @Column(name = "main_account_bank_code")
    private String mainAccountBankCode;
    @Column(name = "main_account_bank_contents")
    private String mainAccountBankContents;
    @Convert(converter = AccountCodeConverter.class)
    @Column(name = "main_account_account_code")
    private RefAccountCode mainAccountCode;
    @Convert(converter = PaymentTypeConverter.class)
    @Column(name = "main_account_payment_type")
    private RefPaymentType paymentType;
    private String mainAccountContents;
    @Convert(converter = RepeatTypeConverter.class)
    @ColumnDefault(value = "0")
    @Column(name = "loop_type")
    private RefRepeatType loopType;
    @Column(name = "loop_end_date")
    private LocalDate loopEndDate;


    @Transient
    private Boolean isIncome;

    @Builder
    public MainAccount(LocalDateTime mainAccountDate, String mainAccountPrice, Category category,
                       String mainAccountBankCode,String mainAccountBankContents, RefAccountCode mainAccountCode,
                       RefPaymentType paymentType, String mainAccountContents) {
        this.mainAccountDate = mainAccountDate;
        this.mainAccountPrice = mainAccountPrice;
        this.category = category;
        this.mainAccountBankCode = mainAccountBankCode;
        this.mainAccountBankContents = mainAccountBankContents;
        this.mainAccountCode = mainAccountCode;
        this.paymentType = paymentType;
        this.mainAccountContents = mainAccountContents;
    }

    public void setUser(User user){
        this.userId = user.getUserId();
        this.userNo = user.getUserNo();
    }
    @PostLoad
    public void setIsIncome(){
        if(this.mainAccountPrice.contains("+")) {
            this.isIncome = true;
        } else {
            this.isIncome = false;
        }
    }


}
