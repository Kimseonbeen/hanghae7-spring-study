package com.study.spring.controller;

import com.study.spring.dto.req.MemberRequestDTO;
import com.study.spring.global.response.ApiResponse;
import com.study.spring.global.Exception.CustomException;
import com.study.spring.global.Exception.ErrorCode;
import com.study.spring.jwt.JwtUtil;
import com.study.spring.service.MemberService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/member/signup")
    public ApiResponse<String> create(@RequestBody @Valid MemberRequestDTO memberRequestDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new CustomException(ErrorCode.CREATE_USER_NOT_VALID);
        }

        memberService.save(memberRequestDTO);

        return ApiResponse.ok("회원가입 성공");
    }

    @PostMapping("/member/login")
    public ApiResponse<String> login(@RequestBody @Valid MemberRequestDTO memberRequestDTO, HttpServletResponse response) {
        memberService.login(memberRequestDTO, response);

        return ApiResponse.ok("로그인 성공");
    }
}
