package com.server.base.repository.userRepository;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class UserRepositoryImpl extends QuerydslRepositorySupport implements UserRepositoryCustom {
    public UserRepositoryImpl() {
        super(QUser.class);
    }

}
