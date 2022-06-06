package com.server.base.repository.depositAccountRepository;

import com.server.base.common.enums.RefPaymentType;
import com.server.base.common.enums.RefRepeatType;
import com.server.base.common.enums.RefWeekday;
import com.server.base.repository.bankCodeRepository.BankCode;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

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
public class DepositAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "de_account_no")
    private Long depositAccountNo;
    private Long userNo;
    @Column(name = "de_account_price")
    private Long depositAccountPrice;
    @Column(name = "de_account_date")
    private LocalDate depositAccountDate;
    @Column(name = "de_account_s_date")
    private LocalDate depositAccountStartDate;
    @Column(name = "de_account_e_date")
    private LocalDate depositAccountEndDate;
    @OneToOne
    @JoinColumn(name = "de_account_bank_code", referencedColumnName = "bank_code_no")
    private BankCode bankCode;
    @Column(name = "de_account_contents")
    private String depositAccountContents;
//    converter
    @Column(name = "de_account_payment_type")
    private RefPaymentType paymentType;
    @Column(name = "de_account_loop_cnt_now")
    private Integer depositAccountLoopCountNow;
    @Column(name = "de_account_loop_cnt_T")
    private Integer depositAccountLoopCountTotal;
    @Column(name = "de_account_stts")
    private Boolean isDeleted;
//    converter
    @Column(name = "de_account_ref")
    private RefRepeatType repeatPeriod;
//    converter
    @Column(name = "de_account_day")
    private RefWeekday weekday;

}
