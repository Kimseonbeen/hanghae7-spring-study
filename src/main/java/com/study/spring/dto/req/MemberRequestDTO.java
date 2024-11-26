package com.study.spring.dto.req;


import jakarta.validation.constraints.*;
import lombok.*;

@Data
/**
 * 이 Lombok 애너테이션은 final 필드에 대해 생성자를 자동으로 생성합니다.
 * 즉, id, username, password 필드가 final로 선언되어 있기 때문에,
 * 이 애너테이션은 id, username, password를 파라미터로 받는 생성자를 자동으로 생성합니다.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequestDTO {

    @NotNull
    // 최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)
    @Size(min = 4, max = 10, message = "아이디는 최소 4자이상, 10자 이하입니다.")
    @Pattern(regexp = "^[a-z0-9]+$", message = "아이디 정규식 오류")
    private String username;
    @NotNull
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자이상, 15자 이하입니다.")
    // 최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 특수문자 포함
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]+$",
            message = "비밀번호 정규식 오류")
    private String password;
}
