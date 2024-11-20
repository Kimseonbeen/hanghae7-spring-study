package com.study.spring.global;

import com.study.spring.global.response.ApiResponse;
import com.study.spring.global.response.CustomException;
import com.study.spring.global.response.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


/**
 * @RestControllerAdvice ?
 * @ControllerAdvice + @ResponseBody
 * 전체 애플리케이션 또는 특정 패키지/컨트롤러에 한정하여 예외 처리, 모델 속성 추가, 바인딩 설정 등을 전역적으로 처리가능
 *
 * @ControllerAdvice와 Interceptor 차이점
 *
 * 1. 동작 시점의 차이
 * @ControllerAdivce는 예외 발생 시 동작.
 * 예외가 발생하면 해당 예외를 처리하기 위해 @ControllerAdvice 내에서 정의된 @ExceptionHandler 메서드가 호출됨
 *
 * Interceptor는 요청을 컨트롤러가 실행되기 전에 또는 실행된 후에 가로채서 작업을 처리
 *
 * 2. 목적의 차이
 * @ControllerAdvice는 주로 예외 처리와 관련된 기능을 수행
 *
 * Interceptor는 요청 및 응답의 흐름을 가로채 전처리 및 후처리를 할 수 있는 기능 제공 (인증, 로깅, 성능 측정)
 *
 *
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 존재하지 않는 요청에 대한 예외
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ApiResponse<?> handleNoPageFoundException(Exception e) {
        log.error("GlobalExceptionHandler catch NoHandlerFoundException : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }


    // 커스텀 예외
    @ExceptionHandler(value = {CustomException.class})
    public ApiResponse<?> handleCustomException(CustomException e) {
        log.error("handleCustomException() in GlobalExceptionHandler throw CustomException : {}", e.getMessage());
        return ApiResponse.fail(e);
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse<?> handleException(Exception e) {
        log.error("handleException() in GlobalExceptionHandler throw Exception : {}", e.getMessage());
        e.printStackTrace();
        return ApiResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
