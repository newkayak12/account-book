package com.server.base.baseTest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.Objects;

@SpringBootTest
@ActiveProfiles(value = "local")
//@RunWith(SpringRunner.class)
//@AutoConfigureWebMvc
//@WebMvcTest
@Slf4j
public class BaseTest {
    protected ObjectMapper objectMapper;
    protected HttpHeaders httpHeaders;
    protected  RestTemplate rt;
    protected  String authorization = "";
    protected  String refresh = "";
    protected  String prefix = "";


    @BeforeEach
    protected void setUp(){
        authorization = Constants.TEST_ACCESS_TOKEN;
        refresh = Constants.TEST_REFRESH_TOKEN;
        prefix=Constants.TEST_PREFIX;
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set(Constants.REFRESH_TOKEN, refresh);
        httpHeaders.set(HttpHeaders.AUTHORIZATION, authorization);
        rt = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
    }
    protected void get(String url, Object object){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(prefix+url);
//            UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(prefix+url);

            if(!Objects.isNull(object)){
                Field[] fields = object.getClass().getDeclaredFields();
                for(Field field : fields){
                    field.setAccessible(true);
                    builder.queryParam(field.getName(), field.get(object));
                }
            }

            String uri = builder.build(false).toUriString();
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
    protected void post(String url, Object object){
        try {
            String json = objectMapper.writeValueAsString(object);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(prefix+url, HttpMethod.POST, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    protected void patch(String url, Object object){
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
    protected void delete(String url, Object object){
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(prefix+url);

            if(!Objects.isNull(object)){
                Field[] fields = object.getClass().getDeclaredFields();
                for(Field field : fields){
                    field.setAccessible(true);
                    builder.queryParam(field.getName(), field.get(object));
                }
            }

            String uri = builder.toUriString();
            String json = objectMapper.writeValueAsString(object);
            log.error("URL !!!!!!::::::{}", uri);
            log.error("JSON!!!::{}", json);
            HttpEntity<String> entity = new HttpEntity<>(json, httpHeaders);
            ResponseEntity<Map> response = rt.exchange(uri, HttpMethod.DELETE, entity, Map.class);
            log.warn("[  HEADER  ]  ::: {}", response.getHeaders());
            log.error("\n\n");
            log.warn("[  BODY  ]  ::: {}", response.getBody());
        } catch (JsonProcessingException e) {
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}