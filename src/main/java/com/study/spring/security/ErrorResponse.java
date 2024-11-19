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
    public static <T> ErrorResponse<T> failure(ErrorCode errorCode) {
        return new ErrorResponse<>(errorCode.getMessage(), errorCode.getCode());
    }

    public static <T> ErrorResponse<T> failure(String message, ErrorCode errorCode) {
        return new ErrorResponse<>(message, errorCode.getCode());
    }

}