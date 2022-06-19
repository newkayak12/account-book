package com.server.base.repository.dto;

import com.server.base.common.enums.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
public class UserDto implements Serializable {
    private  UserStatus status;
    private  LocalDateTime regDate;
    private  LocalDateTime lastLoginDate;
    private  LocalDateTime userLockDate;
    private  LocalDateTime withdrawalDate;
    private  Long userNo;
    private  String userId;
    private  String password;
    private  String passwordSub;
    private  String passwordNew;
    private  Integer passwordFailCnt;
    private  Integer userStartPage;
    private  Boolean isDark;
    private  AuthEntityDto authEntity;
}
