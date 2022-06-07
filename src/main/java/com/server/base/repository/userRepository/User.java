package com.server.base.repository.userRepository;

import com.server.base.common.baseEntity.AuthEntity;
import com.server.base.common.baseEntity.UserDateEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.util.ObjectUtils;

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
public class User extends UserDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, name = "user_no")
    private Long userNo;
    @Column(name = "user_id", length = 50)
    private String userId;
    @Column(name = "user_pw", length = 300)
    private String password;
    @Column(name = "user_pw_sub", length = 300)
    private String passwordSub;
    @Column(name = "user_name", length = 12)
    private String userName;
    @Column(name = "user_num", length = 12)
    private String userNum;
    @Column(name = "user_fail_cnt")
    private Integer userFailCnt;
    @Embedded
    private AuthEntity authEntity;

    public void setRefreshToken(String value){
        if(ObjectUtils.isEmpty(this.authEntity.getRefreshToken())){
            authEntity.setRefreshToken(value);
        }
    }

    public void subPasswordFail(){
        if(this.userFailCnt<=5){
            this.userFailCnt+=1;
        } else {
            this.userFailCnt = 0;
            lockDown();
        }


    }
//    LOCK
}
