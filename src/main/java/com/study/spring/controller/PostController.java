package com.study.spring.controller;

import com.study.spring.domain.Post;
import com.study.spring.dto.PostDTO;
import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.dto.res.PostResponseDTO;
import com.study.spring.global.response.ApiResponse;
import com.study.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ApiResponse<PostResponseDTO> create(@RequestBody PostRequestDTO requestDTO) {
        Post savedPost = postService.savePost(requestDTO);

        PostResponseDTO responseDTO = PostResponseDTO.from(savedPost);

        return ApiResponse.ok(responseDTO);
    }

    @GetMapping("/posts")
    public ApiResponse<List<PostResponseDTO>> list() {

        List<Post> posts = postService.findPosts();

        List<PostResponseDTO> responseDTO = posts.stream()
                .map(PostResponseDTO::from)
                .collect(Collectors.toList());

        return ApiResponse.ok(responseDTO);
    }

    @GetMapping("/post/{postId}")
    public ApiResponse<PostResponseDTO> getPost(@PathVariable("postId") Long postId) {
        Post findPost = postService.findPost(postId);

        PostResponseDTO responseDTO = PostResponseDTO.from(findPost);

        return ApiResponse.ok(responseDTO);
    }

    @PutMapping("/post/{postId}/edit")
    public ApiResponse<PostResponseDTO> updatePost(@PathVariable("postId") Long postId,
                                              @RequestBody PostRequestDTO requestDTO) {

        Post updatedPost = postService.updatePost(postId, requestDTO);

        PostResponseDTO responseDTO = PostResponseDTO.from(updatedPost);

        return ApiResponse.ok(responseDTO);
    }

    @DeleteMapping("/post/{postId}/delete")
    public ApiResponse<String> deletePost(@PathVariable("postId") Long postId,
                                             @RequestBody PostRequestDTO requestDTO) {

        postService.delete(postId, requestDTO);

        return ApiResponse.ok("게시글이 삭제되었습니다.");
    }
}
