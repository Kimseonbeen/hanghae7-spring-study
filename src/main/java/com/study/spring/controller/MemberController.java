package com.study.spring.controller;

import com.study.spring.dto.AddUserRequest;
import com.study.spring.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody @Valid AddUserRequest addUserRequest, BindingResult bindingResult) {

        String message = "success";

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        try {
            memberService.save(addUserRequest);
        } catch (IllegalArgumentException e) {  // 중복 예외 처리
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(message);
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid AddUserRequest addUserRequest) {
        memberService.login(addUserRequest);

        return ResponseEntity.ok("로그인 성공");

    }
}
