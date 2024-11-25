package com.study.spring.controller;

import com.study.spring.domain.Post;
import com.study.spring.dto.res.PostResponseDTO;
import com.study.spring.global.response.ApiResponse;
import com.study.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/good")
public class TestController {

    private final PostService postService;

    @GetMapping("/posts")
    public ApiResponse<List<PostResponseDTO>> list() {

        List<Post> posts = postService.findPosts();

        List<PostResponseDTO> responseDTO = posts.stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());

        return ApiResponse.ok(responseDTO);
    }
}
