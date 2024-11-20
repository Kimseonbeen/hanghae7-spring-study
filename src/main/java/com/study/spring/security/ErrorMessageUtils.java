package com.study.spring.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.springframework.http.MediaType;

public class ErrorMessageUtils {

    private ErrorMessageUtils() {
        throw new IllegalStateException("유틸리티 클래스는 인스턴스화 할 수 없습니다.");
    }

    public static void makeErrorResponseBody(HttpServletResponse response, ErrorCode_2 errorCode2)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(errorCode2.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(String.valueOf(StandardCharsets.UTF_8));
        mapper.writeValue(response.getWriter(), ErrorResponse.failure(errorCode2));

    }


}