package com.study.spring.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.global.Exception.CustomException;
import com.study.spring.global.Exception.ErrorCode;
import com.study.spring.global.Exception.ExceptionDto;
import com.study.spring.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;

public class ErrorMessageUtils {

    private ErrorMessageUtils() {
        throw new IllegalStateException("유틸리티 클래스는 인스턴스화 할 수 없습니다.");
    }

    public static void makeErrorResponseBody(HttpServletResponse response, ErrorCode errorCode)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        mapper.writeValue(response.getWriter(), ApiResponse.fail(new CustomException(errorCode)));
    }


}