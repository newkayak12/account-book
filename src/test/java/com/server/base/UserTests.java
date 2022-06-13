package com.server.base;

import com.server.base.repository.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;


@Slf4j

class UserTests extends BaseTest {

    @Test
    void signUpTest() {
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPassword("qwer1234");
        v.setUserName("test1");
        v.setUserNum("01012341234");
        post("/user/signUp", v);
        log.error(">>>>>>>>>>");
    }
    @Test
    void signInTest(){
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPassword("qwer1234");
        get("/user/signIn", v);
    }
    @Test
    void subPasswordSignUp(){
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPasswordSub("0626");
        patch("/user/easySignUp", v);
    }
    @Test
    void subPasswordSignIn(){
        UserDto v = new UserDto();
        v.setPasswordSub("0626");
        get("/user/easySignIn", v);
    }
    @Test
    void changePassword(){
        UserDto v = new UserDto();
        v.setPassword("qwer1234");
        v.setPasswordNew("qwer1234");
        patch("/user/changePw", v);
    }
}
