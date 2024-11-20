package com.study.spring.security;


import lombok.Getter;

@Getter
public class ErrorResponse<T> {

    private String message;
    private String code;

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }
    public static <T> ErrorResponse<T> failure(ErrorCode_2 errorCode2) {
        return new ErrorResponse<>(errorCode2.getMessage(), errorCode2.getCode());
    }

    public static <T> ErrorResponse<T> failure(String message, ErrorCode_2 errorCode2) {
        return new ErrorResponse<>(message, errorCode2.getCode());
    }

}