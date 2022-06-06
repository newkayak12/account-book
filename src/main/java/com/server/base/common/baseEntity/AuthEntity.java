package com.server.base.common.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Setter
public class AuthEntity {
    @Column(name = "user_refresh_token", length = 250, nullable = false)
    private String refreshToken;
}
