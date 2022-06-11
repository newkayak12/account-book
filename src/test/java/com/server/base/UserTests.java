package com.server.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.base.common.constants.Constants;
import com.server.base.repository.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Map;


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
    void subPasswordSignIn(){
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPasswordSub("0626");
        patch("/user/easySignUp", v);
    }
    @Test
    void subPasswordSign(){
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
