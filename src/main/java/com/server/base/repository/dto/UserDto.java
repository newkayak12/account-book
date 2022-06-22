package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
import com.server.base.common.enums.UserStatus;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AuthorizeDto
@JsonIgnoreProperties(value = {"password", "passwordSub", "authEntity"}, allowGetters = false, allowSetters = true)
public class UserDto implements Serializable {
    private  Long userNo;
    private  String userId;
    @IgnoreEncrypt
    private  String password;
    @IgnoreEncrypt
    private  String passwordSub;
    @IgnoreEncrypt
    private  String passwordNew;
    @IgnoreEncrypt
    private  Integer passwordFailCnt;
    @IgnoreEncrypt
    private  Integer userStartPage;
    @IgnoreEncrypt
    private  Boolean isDark;
    @IgnoreEncrypt
    private  AuthEntityDto authEntity;
    @IgnoreEncrypt
    private  UserStatus status;
    @IgnoreEncrypt
    private  LocalDateTime regDate;
    @IgnoreEncrypt
    private  LocalDateTime lastLoginDate;
    @IgnoreEncrypt
    private  LocalDateTime userLockDate;
    @IgnoreEncrypt
    private  LocalDateTime withdrawalDate;
}
