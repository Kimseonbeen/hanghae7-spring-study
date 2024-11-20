package com.study.spring.global.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;

/**
 * record ?
 * 1. 불변객체
 * - ApiResponse는 응답 객체로, 일반적으로 응답이 한 번 생성되면 그 값을 변경하지 않는 불변 객체여야함.
 * 불변객체는 상태를 변경할 수 없으므로, 멀티스레딩 환경에서의 안전성, 예기치 않은 상태 변경을 방지
 *
 * 2. 데이터 전달 객체(DTO)로서의 특성
 * - ApiResponse는 DTO로 일반적으로 상태를 변경하지 않고, 단순히 데이터를 전달하는 역할을 하기에 적합
 *
 * 3. 자동 생성되는 메서드
 * record는 toString(), eqauls(), getter 제공
 */
public record ApiResponse<T>(
        @JsonIgnore
        HttpStatus httpStatus,
        boolean success,
        @Nullable T data,
        @Nullable ExceptionDto error
) {

    public static <T> ApiResponse<T> ok(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.OK, true, data, null);
    }

    public static <T> ApiResponse<T> created(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.CREATED, true, data, null);
    }

    public static <T> ApiResponse<T> fail(final CustomException e) {
        return new ApiResponse<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()));
    }
}
