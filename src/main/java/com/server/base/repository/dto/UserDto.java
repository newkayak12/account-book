package com.server.base.repository.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
import com.server.base.common.enums.UserStatus;
import com.server.base.common.validations.Validations;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@AuthorizeDto
@Builder
@ToString
public class UserDto implements Serializable {
    private Long userNo;

    @NotNull(message = "아이디를 입력해주세요", groups = {Validations.FirstSign.class, Validations.SignUp.class})
    private String userId;
    @NotNull(message = "이름을 입력해주세요", groups = { Validations.SignUp.class})
    private  String userName;

    @NotNull(message = "전화번호를 입력해주세요", groups = { Validations.SignUp.class})
    private  String userNum;
    @IgnoreEncrypt
    @JsonIgnoreProperties(allowSetters = true, allowGetters = false)
    private  Integer userFailCnt;
//    @JsonIgnore(value = false)
    @IgnoreEncrypt
    @JsonIgnoreProperties(allowSetters = true, allowGetters = false)
    @NotNull(message = "비밀번호를 입력해주세요", groups = {Validations.FirstSign.class,  Validations.SignUp.class, Validations.ChangePassword.class})
    private String password;
    @IgnoreEncrypt
    @JsonIgnoreProperties(allowSetters = true, allowGetters = false)
    @NotNull(message = "변경할 비밀번호를 입력해주세요", groups = {Validations.ChangePassword.class})
    private String passwordNew;
    @IgnoreEncrypt
    @JsonIgnoreProperties(allowSetters = true, allowGetters = false)
    @NotNull(message = "간편 비밀번호를 올바르게 입력하세요", groups = {Validations.SecondSign.class})
    @Length(message = "간편 비밀번호를 올바르게 입력하세요", max = 4, min = 1, groups = {Validations.SecondSign.class})
    private String passwordSub;
    @IgnoreEncrypt
    @JsonIgnore
    @JsonIgnoreProperties(allowSetters = true, allowGetters = false)
    private AuthEntityDto authEntity=new AuthEntityDto();
    private UserStatus userStatus;
    @IgnoreEncrypt
    private LocalDateTime regDate;
    @IgnoreEncrypt
    private LocalDateTime lastLoginDate;
    @IgnoreEncrypt
    private LocalDateTime userLockDate;
    @IgnoreEncrypt
    private LocalDateTime withdrawalDate;

}



