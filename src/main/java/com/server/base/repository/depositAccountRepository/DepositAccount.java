package com.server.base.repository.depositAccountRepository;

import com.server.base.common.converter.PaymentTypeConverter;
import com.server.base.common.converter.RepeatTypeConverter;
import com.server.base.common.converter.WeekConverter;
import com.server.base.common.enums.RefPaymentType;
import com.server.base.common.enums.RefRepeatType;
import com.server.base.common.enums.RefWeekday;
import com.server.base.repository.userRepository.User;
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
    @Column(name = "de_account_contents")
    private String depositAccountContents;
    @Convert(converter = PaymentTypeConverter.class)
    @Column(name = "de_account_payment_type")
    private RefPaymentType paymentType;
    @Column(name = "de_account_loop_cnt_now")
    private Integer depositAccountLoopCountNow;
    @Column(name = "de_account_loop_cnt_T")
    private Integer depositAccountLoopCountTotal;
    @Column(name = "de_account_stts")
    private Boolean isDeleted;
    @Convert(converter = RepeatTypeConverter.class)
    @Column(name = "de_account_ref")
    private RefRepeatType repeatPeriod;
    @Convert(converter = WeekConverter.class)
    @Column(name = "de_account_day")
    private RefWeekday weekday;

    @Builder
    public DepositAccount(Long depositAccountPrice, LocalDate depositAccountDate, LocalDate depositAccountStartDate,
                          LocalDate depositAccountEndDate, String depositAccountContents,
                          RefPaymentType paymentType, Integer depositAccountLoopCountTotal, RefRepeatType repeatPeriod,
                          RefWeekday weekday) {
        this.depositAccountPrice = depositAccountPrice;
        this.depositAccountDate = depositAccountDate;
        this.depositAccountStartDate = depositAccountStartDate;
        this.depositAccountEndDate = depositAccountEndDate;
        this.depositAccountContents = depositAccountContents;
        this.paymentType = paymentType;
        this.depositAccountLoopCountTotal = depositAccountLoopCountTotal;
        this.repeatPeriod = repeatPeriod;
        this.weekday = weekday;
    }

    public void setUser(User user){
        this.userNo = user.getUserNo();
    }
    public void delete(){
        this.isDeleted = true;
    }
    public void setLoopCount(Integer count){
        if(this.depositAccountLoopCountTotal>=count){
            this.depositAccountLoopCountNow = count;
        }
    }
}
