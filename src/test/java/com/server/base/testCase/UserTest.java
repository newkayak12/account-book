package com.server.base.testCase;

import com.server.base.baseTest.BaseTest;
import com.server.base.repository.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest extends BaseTest {

    @BeforeEach
    void settings(){
        super.prefix += "/user";
    }

    @Test
    void signUpTest(){
        System.out.println("data:image/png;base64,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".length());
        UserDto userDto = UserDto.builder().userId("test@test.com")
                .password("test").build();
        super.post("/signUp", userDto);
    }
    @Test
    void signInTest(){
        UserDto userDto = UserDto.builder().userId("test@test.com")
                .password("test").build();
        this.get("/signIn", userDto);
    }
    @Test
    void easySignUpTest(){
        UserDto userDto = UserDto.builder().userId("test@test.com")
                .password("test").passwordSub("1234").build();
        this.patch("/easySignUp", userDto);
    }

    @Test
    void easySignInTest(){
        UserDto userDto = UserDto.builder().userId("test@test.com")
                .password("test").passwordSub("1234").build();
        this.get("/easySignIn", userDto);
    }
}