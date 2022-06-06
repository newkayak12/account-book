package com.server.base.repository.myMoneyRepository;

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
public class MyMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_money_no")
    private Long myMoneyNo;
    private Long userNo;
    private String totalprice;
    @Transient
    private Boolean isIncome;
}
