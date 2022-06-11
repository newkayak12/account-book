package com.server.base.common.exception;

import lombok.Getter;

@Getter
public enum Exceptions {
//    TOKEN_EXCEPTIONS
    INVALID_TOKEN(-999,"비정상적인 접근입니다."),
    TOKEN_EXPIRED(-998, "접근 권한이 만료되었습니다."),
    INVALID_ACCESS(-998, "잘못된 접근입니다."),

//    USER_EXCEPTIONS
    NO_DATA(-1, "아이디 혹은 비밀번호가 잘못됐습니다."),
    SUB_PASSWORD_FAIL(-2, "비밀번호 오류 %d/5 \n 간편 비밀번호를 5회 이상 틀릴 경우 10분간 로그인을 제한합니다."),
    ALREADY_EXIST(-3, "이미 존재하는 계정입니다."),

    //    GENERAL
    EMPTY_DATA(-2000, "해당 데이터가 없습니다."),

// BINDINGERROR
    BINDING_ERROR(-2000, "파라미터 %s가 잘못됐습니다.");



    private Integer code;
    private String msg;
    Exceptions(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
