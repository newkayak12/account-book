package com.server.base.common.authorizations.aspect;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.authorizations.annotations.AuthorizeDto;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.reflections.Reflections;
import org.springframework.stereotype.Component;
import java.lang.reflect.Parameter;
import java.util.*;

@Component
@Aspect
public class Authentication {

    @Around("@annotation(com.server.base.common.authorizations.annotations.Authorization)")
    public Object decrypt(ProceedingJoinPoint joinPoint) throws Throwable {
        if(Constants.IS_DEV_MODE){
            System.out.println("  ___   _____ ______ ");
            System.out.println(" / _ \\ |  _  || ___ \\");
            System.out.println("/ /_\\ \\| | | || |_/ /");
            System.out.println("|  _  || | | ||  __/ ");
            System.out.println("| | | |\\ \\_/ /| |    ");
            System.out.println("\\_| |_/ \\___/ \\_|    ");
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Object[] parameterValues = joinPoint.getArgs();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        Reflections reflections = new Reflections("com.server.base.repository.dto");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(AuthorizeDto.class);

        Iterator<Class<?>> targetOne = classSet.iterator();
        Class<?> target = targetOne.next();
        for(int i = 0; i<parameters.length; i++){
            Long isExist = Arrays.asList(parameters[i].getDeclaredAnnotations())
                    .stream().filter(item-> {
                        return item.annotationType().equals(org.springframework.web.bind.annotation.RequestHeader.class);
                    }).count();
            if(isExist>0){
                Object  token = parameterValues[i];
                if(Objects.isNull(token)){
                    throw new ServiceException(Exceptions.INVALID_ACCESS);
                }
                parameterValues[i] = TokenManager.decrypt(target.getConstructor().newInstance(), (String) token);
                break;
            }
        }
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
