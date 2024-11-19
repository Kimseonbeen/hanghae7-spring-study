package com.study.spring.service;

import com.study.spring.domain.Member;
import com.study.spring.dto.AddUserRequest;
import com.study.spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member save(AddUserRequest addUserRequest) {
        // 1. username 중복 체크
        if (memberRepository.existsByUsername(addUserRequest.getUsername())) {
            // 중복된 username이 존재하면 400 Bad Request와 함께 오류 메시지 반환
            throw new IllegalArgumentException("Username already exists"); // 예외 발생
        }

        Member member = new Member();
        member.setUsername(addUserRequest.getUsername());
        member.setPassword(addUserRequest.getPassword());

        return memberRepository.save(member);
    }

    public void login(AddUserRequest addUserRequest) {

    }
}