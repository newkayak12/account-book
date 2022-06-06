package com.server.base.repository.accountCodeRepository;

import com.server.base.common.enums.RefAccountCode;
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
public class AccountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountCodeNo;
//converter
    @Column(name = "Ccode")
    private RefAccountCode cCode;
    @Column
    private String codeCode;
}
