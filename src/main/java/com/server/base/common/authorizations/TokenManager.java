package com.server.base.common.authorizations;

import com.server.base.common.authorizations.annotations.IgnoreEncrypt;
import com.server.base.common.constants.Constants;
import com.server.base.common.exception.Exceptions;
import com.server.base.common.exception.ServiceException;
import io.jsonwebtoken.*;
import org.modelmapper.ModelMapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;


public class TokenManager {
//    AccessToken

    /**
     * access secret
     * @return
     */
    private static String getSecretKey(){
        return Base64.getEncoder().encodeToString(Constants.SALT_VALUE.getBytes());
    }

    /**
     * access 암호화
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String  encrypt(T t){
        String projectName = Constants.PROJECT_NAME;
        Field[] fields = t.getClass().getDeclaredFields();
        Map<String,Object> map = new HashMap<>();

        for(Field field:fields){
            field.setAccessible(true);
            String fieldName = field.getName();
            Class<?> fieldType = field.getType();
            Annotation[] annotations = field.getAnnotations();
            Object fieldValue = null;
            try {
                fieldValue = field.get(t);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            Long ignoreCount = Arrays.stream(annotations).filter(item-> item.annotationType().equals(IgnoreEncrypt.class)).count();


            if(!fieldType.isInstance(java.time.LocalDateTime.now())&&!fieldType.isInstance(java.time.LocalDate.now())
                    &&!fieldType.isInstance(java.time.LocalTime.now())&&!(ignoreCount>0)){
                map.put(fieldName, fieldValue);
            }
        }
        return "Bearer "+Jwts.builder()
                .setHeaderParam(Header.TYPE, Constants.ACCESS_TOKEN)
                .setClaims(map)
                .setIssuer(projectName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+(1000*60L*60L*12)))
                .signWith(SignatureAlgorithm.HS512, getSecretKey())
                .compact();
    }

    /**
     * access 복호화
     * @param r
     * @param token
     * @param <R>
     * @return
     * @throws ServiceException
     */
    public static <R> R decrypt(R r, String token) throws ServiceException {

        ModelMapper modelMapper = new ModelMapper();
        Map<String, Object>  claims = null;
        try{
            if(!token.startsWith("Bearer ")){
                throw new ServiceException(Exceptions.INVALID_TOKEN);
            }
            token = token.replace("Bearer ","");
            claims =  Jwts.parser()
            .setSigningKey(getSecretKey())
            .parseClaimsJws(token)
            .getBody();
        } catch( ExpiredJwtException  e){
            claims =  e.getClaims();
            System.out.println("재발급!");
            if(!claims.get("iss").equals(Constants.PROJECT_NAME)){
                throw new ServiceException(Exceptions.INVALID_TOKEN);
            }
        }

        R result = r;
        modelMapper.map(claims, result);

        return result;
    }

    /**
     * access 만료 체크
     * @param token
     * @return
     */
    public static Boolean isExpired(String token){
        token = token.replace("Bearer ","");
        Date expiration = Jwts.parser()
                .setSigningKey(getSecretKey())
                .parseClaimsJws(token)
                .getBody().getExpiration();
        return new Date(System.currentTimeMillis()+1000*60L*60L*13).before(expiration);
    }

    /**
     * refreshSecret
     * @return
     */
    private static String getRefreshSecretKey(){
        return Base64.getEncoder().encodeToString(Constants.REFRESH_SALT_VALUE.getBytes());
    }

    /**
     * refresh 만들기
     * @param no
     * @return
     */
    public static String refreshEncrypt(Long no){
        Map<String, Object> map = new HashMap<>();
        map.put("userNo", no);
        return "Bearer "+Jwts.builder()
                .setHeaderParam(Header.TYPE, Constants.REFRESH_TOKEN)
                .setClaims(map)
                .setIssuer(Constants.PROJECT_NAME)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, getRefreshSecretKey())
                .compact();
    }
}
