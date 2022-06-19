package com.server.base.common.configurations;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.server.base.common.authorizations.interceptor.AuthInterceptor;
import com.server.base.common.constants.Constants;
import com.server.base.common.validations.BindingErrorChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class Configurations implements WebMvcConfigurer {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
    @Bean
    public ModelMapper modelMapper(){return new ModelMapper();}
    @Bean
    public AuthInterceptor authInterceptor(){return new AuthInterceptor();}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = List.of("/**/user/signUp", "/**/user/signIn", "/**/user/signOut", "/error/*");
        List<String> apiList = Constants.API_PATH;
        List<String> includePath = apiList.stream().map(item-> "/api/"+item+"/*").collect(Collectors.toList());
        String include = "/api/**/*";
//        , "**/**/swagger-ui.html"
//        registry.addInterceptor(authInterceptor()).addPathPatterns(includePath).excludePathPatterns(excludePath);
        registry.addInterceptor(authInterceptor()).addPathPatterns(include).excludePathPatterns(excludePath);
    }
    @Bean
    public DateLogger dateLogger() {return new DateLogger();}
    @Bean
    public BindingErrorChecker bindingErrorChecker() {return new BindingErrorChecker();}

    @Autowired
    EntityManager em;
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }
}
