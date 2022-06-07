package com.server.base.common.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.base.common.constants.Constants;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.boot.json.JsonParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.readers.parameter.ApiParamParameterBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classPath:/META-INF/resources/templates");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjar");
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getConsumeContentTypes())
                .produces(getProduceContentTypes())
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.server.base.controller"))
                .paths(PathSelectors.any())
                .build();
    }
//    https://lemontia.tistory.com/1027
//    private List<SecurityReference> auth(){
//        AuthorizationScope access = new AuthorizationScope("global", "accessToken");
//        AuthorizationScope refresh = new AuthorizationScope("global", "refreshToken");
//        AuthorizationScope[] acc = new AuthorizationScope[]{access};
//        AuthorizationScope[] re = new AuthorizationScope[]{refresh};
//        return Arrays.asList(new SecurityReference("access", acc),
//                new SecurityReference("RefreshToken", re));
//    }
//    private SecurityContext securityContext(){
//        return SecurityContext.builder()
//                .securityReferences(auth())
//                .build();
//    }
//    private List<ApiKey> keys(){
//
//    }

    private Set<String> getConsumeContentTypes(){
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json;charset=UTF-8");
        consumes.add("application/x-www-form-urlencoded");
        return consumes;
    }
    private Set<String> getProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json;charset=UTF-8");
        return produces;
    }
    private ApiInfo getApiInfo() {
        String date = "";
        if(Constants.IS_DEV_MODE) {
            try {
                String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
                File f = new File(path + "/src/main/java/com/server/base/common/configurations/jsonLog/log.json");
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> json = objectMapper.readValue(f, Map.class);
                date = (String) json.get("date");
            } catch (IOException e) {
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")).toString();
            }
        }

        return new ApiInfoBuilder()
                .title(Constants.PROJECT_NAME+" API")
                .description("Last Modified Date : "+date)
                .contact(new Contact("newkayak12", "https://github.com/newkayak12", "newkayak12@gmail.com"))
                .version("1.0")
                .build();
    }
}
