package com.server.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.constants.Constants;
import com.server.base.repository.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "local")
@Slf4j
//@RunWith(SpringRunner.class)
//@AutoConfigureWebMvc
//@WebMvcTest
class BaseApplicationTests {
    ObjectMapper objectMapper;
    HttpHeaders httpHeaders;
    RestTemplate rt;
    String refresh = "Bearer eyJ0eXAiOiJyZiIsImFsZyI6IkhTNTEyIn0.eyJ1c2VyTm8iOjEsImlzcyI6IlNQUklOR19CQVNFIiwiaWF0IjoxNjU0Nzc2NDMxfQ.zFQrdsMMqK-MlVkHovH2XXE7T6-1U_ihSAa_XX6gAR2UANUFeD7jXPb-ruWS2K5wwvVEthEQlVZQGP8BF9v1OQ";
    String authorization =  "Bearer eyJ0eXAiOiJhYyIsImFsZyI6IkhTNTEyIn0.eyJ1c2VyTnVtIjoiMDEwMTIzNDEyMzQiLCJ1c2VyU3RhdHVzIjoiQUNUSVZBVEVEIiwiYXV0aEVudGl0eSI6eyJyZWZyZXNoVG9rZW4iOiJCZWFyZXIgZXlKMGVYQWlPaUp5WmlJc0ltRnNaeUk2SWtoVE5URXlJbjAuZXlKMWMyVnlUbThpT2pFc0ltbHpjeUk2SWxOUVVrbE9SMTlDUVZORklpd2lhV0YwSWpveE5qVTBOemMyTkRNeGZRLnpGUXJkc01NcUstTWxWa0hvdkgyWFhFN1Q2LTFVX2loU0FhX1hYNmdBUjJVQU5VRmVEN2pYUGItcnVXUzJLNXd3dlZFdGhFUWxWWlFHUDhCRjl2MU9RIn0sInVzZXJObyI6MSwiaXNzIjoiU1BSSU5HX0JBU0UiLCJ1c2VyTmFtZSI6InRlc3QxIiwiZXhwIjoxNjU0ODE5NjMxLCJ1c2VySWQiOiJ0ZXN0MTIiLCJ1c2VyRmFpbENudCI6bnVsbCwiaWF0IjoxNjU0Nzc2NDMxfQ.4NGDb8gm3pdzZt8zXRquTadpQWeZDiB8_9l5khKjF5HC4zl8M3M5o5AQ8KReTB_FUwgBLS2iOqHIl1AjCdNjKw";
    String userPrefix = "http://localhost:8080/api/user/";

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(Constants.REFRESH_TOKEN, refresh);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, authorization);
        rt = new RestTemplate();
    }


    void get(String url, Object object){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userPrefix+url);

            Field[] fields = object.getClass().getDeclaredFields();
            for(Field field : fields){
                field.setAccessible(true);
                builder.queryParam(field.getName(), field.get(object));
            }
            String uri = builder.toUriString();
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(uri, HttpMethod.GET, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException | IllegalAccessException e) {
            e.printStackTrace();
        }


    }
    void post(String url, Object object){
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(userPrefix+url, HttpMethod.POST, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }
    void patch(String url, Object object){
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(userPrefix+url, HttpMethod.PATCH, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }
    void delete(String url, Object object){
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(userPrefix+url, HttpMethod.DELETE, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }

    @Test
    void signUpTest() {
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPassword("qwer1234");
        v.setUserName("test1");
        v.setUserNum("01012341234");
        post("signUp", v);
        log.error(">>>>>>>>>>");
//        TokenManager.isExpired(response.getHeaders().get("Authorization").get(0));
    }
    @Test
    void signInTest(){
        UserDto v = new UserDto();
        v.setUserId("test12");
        v.setPassword("qwer1234");
        get("signIn", v);
    }

}
