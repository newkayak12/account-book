package com.server.base.common.baseEntity;

import com.server.base.common.converter.UserStatusConverter;
import com.server.base.common.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@DynamicUpdate
@DynamicInsert
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class UserDateEntity {
//    @ColumnDefault(value = "0")
    @Column(name = "user_stts")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus status;

    @Column(nullable = false, name = "user_reg_date")
    private LocalDateTime regDate;
    @Column(nullable = true, name = "user_login_date")
    private LocalDateTime lastLoginDate;
    @Column(name = "user_lock_date")
    private LocalDateTime userLockDate;
    @Column(nullable = true, name = "user_delete_date")
    private LocalDateTime withdrawalDate;

    @PrePersist
    public void signUp(){
        this.regDate=LocalDateTime.now();
        this.status=UserStatus.ACTIVATED;
    }
    @PostLoad
    public void signIn(){
        this.lastLoginDate = LocalDateTime.now();
    }
    @PreUpdate
    public void withdrawal(){
        System.out.println(this.status);
        if(this.status.getStatus().equals(-1)){
            this.withdrawalDate=LocalDateTime.now();
        }
    }
    public void lockDown(){
        this.userLockDate = LocalDateTime.now().plusMinutes(10L);
    }

}
