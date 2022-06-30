package com.server.base.repository.userRepository;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.baseEntity.AuthEntity;
import com.server.base.common.baseEntity.UserDateEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = "userNo")
@ToString
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
@DynamicInsert
public class User extends UserDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_idx", nullable = false)
    private Long userNo;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "user_password", nullable = false, length = 300)
    private String  password;

    @Column(name = "user_password_sub", length = 300)
    private String passwordSub;


    @Column(name = "user_password_fail_cnt", nullable = false)
    private Integer passwordFailCnt;

    @Column(name = "user_start_page", nullable = false)
    private Integer userStartPage;

    @Column(name = "is_dark", nullable = false)
    private Boolean isDark = false;

    @Embedded
    private AuthEntity authEntity = new AuthEntity();



    @PostPersist
    private void setRefreshToken(){ authEntity.setRefreshToken(TokenManager.refreshEncrypt(this.userNo)); }
    public void setPasswordSub(String passwordSub){
        this.passwordSub = passwordSub;
    }
    public void setPassword(String password) { this.password = password; }
    public void subPasswordFail(){
        if(this.passwordFailCnt<=5){
            this.passwordFailCnt+=1;
        } else {
            this.passwordFailCnt = 0;
            lockDown();
        }
    }

}
