package com.server.base.repository.userRepository;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
import com.server.base.common.baseEntity.AuthEntity;
import com.server.base.common.baseEntity.UserDateEntity;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class User extends UserDateEntity implements Serializable {
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
    @ColumnDefault(value = "0")
    @Column(name = "user_fail_cnt")
    private Integer userFailCnt;
    @Embedded
    private AuthEntity authEntity = new AuthEntity();

    @PostPersist
    private void setRefreshToken(){
        System.out.println("TOKEN!! "+TokenManager.refreshEncrypt(this.userNo));
       authEntity.setRefreshToken(TokenManager.refreshEncrypt(this.userNo));
    }
    public void setPasswordSub(String passwordSub){
        this.passwordSub = passwordSub;
    }
    public void subPasswordFail(){
        if(this.userFailCnt<=5){
            this.userFailCnt+=1;
        } else {
            this.userFailCnt = 0;
            lockDown();
        }


    }
}
