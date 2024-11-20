package com.study.spring.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.spring.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
@DisplayName("게시판 Controller 단위 테스트")
class PostControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @BeforeEach
    void setup() {
        System.out.println("mockMvc = " + mockMvc);
    }
    
    
    @Test
    @DisplayName("게시글 등록")
    void create_post() throws Exception {
        // given

        // when
//        when(postService.)
        mockMvc.perform(post("/post")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(
                        // 응답 본문을 직접 JSON으로 작성 (ApiResponse 없이)
                        new HashMap<String, Object>() {{
                            put("success", true);
                            put("data", boardResponseDto);  // boardResponseDto 객체는 적절히 직렬화 가능해야 함
                            put("message", "게시글 등록 완료");
                            put("status", HttpStatus.CREATED.value());
                )))
        // then
    }
    

}