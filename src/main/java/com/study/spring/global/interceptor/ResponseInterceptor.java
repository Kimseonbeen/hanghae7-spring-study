package com.study.spring.global.interceptor;


import com.study.spring.domain.Post;
import com.study.spring.global.response.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice   // 전역 Controller 적용대상
/**
 * ResponseBodyAdvice : Spring에서 응답 본문을 처리하는 인터페이스로, HTTP 응답이
 * 클라이언트로 보내지기 전에 이를 처리하는 기능을 제공
 */
public class ResponseInterceptor implements ResponseBodyAdvice {

    /**
     * supoort 메서드는 이 RestControllerAdvice가 어떤 응답 바디에 대해 동작할지 정의하는 메서드
     * returnType : 메서드의 반환 타입
     * converterType : 사용하는 HTTP 메세지 변환기 타입
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {

        return true;
    }

    /**
     * beforeBodyWrite는 응답 본문이 클라이언트로 전송되기 전에 호출 되는 메서드
     * 이 메서드를 통해 응답 바디를 가공하거나 수정할 수 있음
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        System.out.println("returnType = " + returnType);
        System.out.println("returnType.getParameterType() = " + returnType.getParameterType());

        // 응답 바디가 ApiResponse 타입일 경우에만 해당 코드 실행
        // TestController 반환 타입이 ApiResponse이므로 반환됨
        if (returnType.getParameterType() == ApiResponse.class) {
            HttpStatus status = ((ApiResponse<?>) body).httpStatus();
            response.setStatusCode(status);
        }

        return body;
    }
}
