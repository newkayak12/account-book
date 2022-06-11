package com.server.base.common.authorizations.interceptor;

import com.server.base.common.authorizations.TokenManager;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import com.server.base.repository.dto.UserDto;
import com.server.base.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.*;

//@RequiredArgsConstructor
@NoArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private  UserService userService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            if(Constants.IS_DEV_MODE){
                System.out.println(" _____         _                                   _                ");
                System.out.println("|_   _|       | |                                 | |               ");
                System.out.println("  | |   _ __  | |_   ___  _ __   ___   ___  _ __  | |_   ___   _ __ ");
                System.out.println("  | |  | '_ \\ | __| / _ \\| '__| / __| / _ \\| '_ \\ | __| / _ \\ | '__|");
                System.out.println(" _| |_ | | | || |_ |  __/| |   | (__ |  __/| |_) || |_ | (_) || |   ");
                System.out.println(" \\___/ |_| |_| \\__| \\___||_|    \\___| \\___|| .__/  \\__| \\___/ |_|   ");
                System.out.println("                                           | |                      ");
                System.out.println("                                           |_|                      ");
            }
            String accessToken = request.getHeader("Authorization");
            String refreshToken = request.getHeader(Constants.REFRESH_TOKEN);
            Boolean isExpired = null;
            try{
                isExpired = TokenManager.isExpired(accessToken);
            } catch (ExpiredJwtException e){
                isExpired = true;
            }
            if(Objects.isNull(accessToken)||isExpired){ //access 토큰이 없거나 만료됐다면
                if(Objects.isNull(refreshToken)){ //refresh 토큰이 없다면
                    throw new ServiceException(Exceptions.TOKEN_EXPIRED); //error
                } else {//refresh 토큰이 있다면
                    UserDto userDto = TokenManager.decrypt(new UserDto(), refreshToken);//    토큰 decrypt
                    userDto = userService.getRefreshToken(userDto.getUserNo());//  userNo 가져와서
                    String userToken  = userDto.getAuthEntity().getRefreshToken();
                    //DB에서 userNo로 refresh token을 가져오고
                    if(!Objects.isNull(userToken)&&userToken.equals(refreshToken)){ ///DB의 토큰과 같다면
                        String accessTokenRemade = TokenManager.encrypt(userDto); //   재발급
                        response.addHeader(HttpHeaders.AUTHORIZATION, accessTokenRemade);
                        return HandlerInterceptor.super.preHandle(request, response, handler);
                    } else {
                        throw new ServiceException(Exceptions.TOKEN_EXPIRED);
                    }
                }
            }

            //access 토큰이 있고 만료가 안됐으면
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }
}

class MutableHttpServletRequest extends HttpServletRequestWrapper {
    private final Map<String,String> customHeaders;

    public MutableHttpServletRequest(HttpServletRequest request) {
        super(request);
        this.customHeaders = new HashMap<>();
    }
    public void putHeader(String name, String value){
        this.customHeaders.put(name, value);
    }

    public String getHeader(String name) {
        // check the custom headers first
        String headerValue = customHeaders.get(name);

        if (headerValue != null){
            return headerValue;
        }
        // else return from into the original wrapped object
        return ((HttpServletRequest) getRequest()).getHeader(name);
    }

    public Enumeration<String> getHeaderNames() {
        // create a set of the custom header names
        Set<String> set = new HashSet<String>(customHeaders.keySet());

        // now add the headers from the wrapped request object
        @SuppressWarnings("unchecked")
        Enumeration<String> e = ((HttpServletRequest) getRequest()).getHeaderNames();
        while (e.hasMoreElements()) {
            // add the names of the request headers into the list
            String n = e.nextElement();
            set.add(n);
        }

        // create an enumeration from the set and return
        return Collections.enumeration(set);
    }
}

