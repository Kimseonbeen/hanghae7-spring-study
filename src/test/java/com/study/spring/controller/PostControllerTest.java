package com.study.spring.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("게시판 Controller 단위 테스트")
class PostControllerTest {
    

    @Test
    void 곱하기테스트() throws Exception {

        // given

        // when
        final int result = mutiply(2,3);
        // then
        assertThat(result).isEqualTo(6);
    }

    private int mutiply(int num1, int num2) {
        return num1 * num2;
    }

    

}