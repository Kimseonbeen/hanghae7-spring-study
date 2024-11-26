package com.study.spring.global.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Test Error
    TEST_ERROR(10000, HttpStatus.BAD_REQUEST, "테스트 에러입니다."),
    // 404 Not Found
    NOT_FOUND_END_POINT(40400, HttpStatus.NOT_FOUND, "존재하지 않는 API입니다."),
    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(50000, HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),
    // 비밀번호 오류
    PASSWORD_ERROR(50100, HttpStatus.BAD_REQUEST, "비밀번호를 확인해주세요."),
    // 아이디 중복 오류
    USERNAME_DUPLICATE(40900, HttpStatus.CONFLICT, "아이디가 이미 존재합니다."),
    // 아이디 유효성 오류
    CREATE_USER_NOT_VALID(40401, HttpStatus.BAD_REQUEST, "아이디 유효성을 확인해주세요."),
    // 게시글 존재 오류
    POST_NOT_EXIST(40401, HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."),
    // 권한 오류
    ACCESS_DENIED(40300,HttpStatus.NOT_FOUND , "권한이 없습니다."),
    // 인증 실패 오류
    AUTHENTICATION_FAIL(40301,HttpStatus.BAD_REQUEST , "인증이 실패했습니다."),

    // TEST
    DUPLICATED_MEMBERSHIP_REGISTER(40301,HttpStatus.BAD_REQUEST , "중복 요청 오류");

    private final Integer code;
    private final HttpStatus httpStatus;
    private final String message;
}
