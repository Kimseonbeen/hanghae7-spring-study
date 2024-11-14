package com.study.spring.controller;

import com.study.spring.domain.Post;
import com.study.spring.dto.PostDTO;
import com.study.spring.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<PostDTO> create(@RequestBody Post post) {
        Post savedPost = postService.savePost(post);

        PostDTO postDTO =  PostDTO.builder()
                .id(savedPost.getId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .author(savedPost.getAuthor()).build();

        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> list() {

        List<Post> posts = postService.findPosts();

        List<PostDTO> postsDTO = posts.stream()
                .map(post -> new PostDTO(post.getId(), post.getTitle(), post.getContent(), post.getAuthor()))
                .collect(Collectors.toList());

        return new ResponseEntity<>(postsDTO, HttpStatus.OK);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") Long postId) {
        Post post = postService.findPost(postId);

        PostDTO postDTO =  PostDTO.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor()).build();

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @PutMapping("/post/{postId}/edit")
    public ResponseEntity<PostDTO> updatePost(@PathVariable("postId") Long postId,
                                              @RequestBody Post post) {

        Post updatedPost = postService.updatePost(postId, post);

        PostDTO postDTO =  PostDTO.builder()
                .id(updatedPost.getId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .author(updatedPost.getAuthor()).build();

        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }

    @DeleteMapping("/post/{postId}/delete")
    public ResponseEntity<String> deletePost(@PathVariable("postId") Long postId,
                                             @RequestBody Post post) {

        postService.delete(postId, post);

        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
    }
}
