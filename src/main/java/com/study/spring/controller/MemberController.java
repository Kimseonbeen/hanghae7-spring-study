package com.study.spring.controller;

import com.study.spring.dto.AddUserRequest;
import com.study.spring.global.response.ApiResponse;
import com.study.spring.global.response.CustomException;
import com.study.spring.global.response.ErrorCode;
import com.study.spring.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<?> create(@RequestBody @Valid AddUserRequest addUserRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
            throw new CustomException(ErrorCode.CREATE_USER_NOT_VALID);
        }

        memberService.save(addUserRequest);

        return ApiResponse.ok("성공");
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid AddUserRequest addUserRequest) {
        memberService.login(addUserRequest);

        return ResponseEntity.ok("로그인 성공");

    }
}
