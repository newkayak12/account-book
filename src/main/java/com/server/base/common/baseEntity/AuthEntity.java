package com.server.base.common.baseEntity;

import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter
public class AuthEntity {
    @Column(name = "user_refresh_token", length = 250)
    private String refreshToken;
}
