package com.study.spring.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRoleType {
    USER("유저"), ADMIN("관리자")
    ;

    private final String roleType;
}
