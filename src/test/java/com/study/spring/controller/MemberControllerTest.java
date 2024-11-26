package com.study.spring.controller;

import com.study.spring.domain.Member;
import com.study.spring.dto.req.MemberRequestDTO;
import com.study.spring.dto.res.MemberResponseDTO;
import com.study.spring.service.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {

    @InjectMocks
    private MemberController memberController;

    @Mock
    private MemberService memberService;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    void 회원가입성공() throws Exception {
        // given
        MemberRequestDTO request = memberRequestDTO();
        Member response = member();

        doReturn(response).when(memberService)
                .save(any(MemberRequestDTO.class));

        // when
//        ResultActions resultActions = mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/member/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new GsonTester().toString(request))
//        );

        // then
    }

    private MemberRequestDTO memberRequestDTO() {
        return MemberRequestDTO.builder()
                .username("test1")
                .password("1234")
                .build();
    }

    private MemberResponseDTO memberResponseDTO() {
        return MemberResponseDTO.builder()
                .username("test1")
                .password("1234")
                .build();
    }

    private Member member() {
        return Member.builder()
                .username("test1")
                .password("1234")
                .build();
    }
}
