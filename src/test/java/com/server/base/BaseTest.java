package com.server.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.base.common.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.util.Map;

@SpringBootTest
@ActiveProfiles(value = "local")
//@RunWith(SpringRunner.class)
//@AutoConfigureWebMvc
//@WebMvcTest
@Slf4j
public class BaseTest {
    ObjectMapper objectMapper;
    HttpHeaders httpHeaders;
    RestTemplate rt;
    String refresh = "Bearer eyJ0eXAiOiJyZiIsImFsZyI6IkhTNTEyIn0.eyJ1c2VyTm8iOjEsImlzcyI6IlNQUklOR19CQVNFIiwiaWF0IjoxNjU1MDIzNzcyfQ.AfPvdWWvqvwSXqX84fmX6d5yPpkFXmw4Xc489FZ-soyNm-qa7RC9LCUTN1nZf1_PDQCJvx5yPsjqFVpiZtYzRg";
    String authorization =  "Bearer eyJ0eXAiOiJhYyIsImFsZyI6IkhTNTEyIn0.eyJ1c2VyTnVtIjoiMDEwMTIzNDEyMzQiLCJ1c2VyU3RhdHVzIjoiQUNUSVZBVEVEIiwidXNlck5vIjoxLCJpc3MiOiJTUFJJTkdfQkFTRSIsInVzZXJOYW1lIjoidGVzdDEiLCJleHAiOjE2NTUwNjY5NzIsInVzZXJJZCI6InRlc3QxMiIsImlhdCI6MTY1NTAyMzc3Mn0.OaQvvV6MqafItVNsqZuDNkDRnbR9cUHmyoGN7TE6QVRntF0DMIfd5TKCVyJHXXwTMClic_jDF4Sllzb76YRVsw";
    String prefix = "http://localhost:8080/api/";

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(Constants.REFRESH_TOKEN, refresh);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, authorization);
        rt = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
    void get(String url, Object object){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(prefix+url);

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
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.POST, entity, Map.class);
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
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.PATCH, entity, Map.class);
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
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.DELETE, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        }
    }
}
