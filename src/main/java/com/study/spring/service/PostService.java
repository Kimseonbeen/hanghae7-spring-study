package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.domain.PostStatus;
import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.dto.res.PostResponseDTO;
import com.study.spring.global.Exception.CustomException;
import com.study.spring.global.Exception.ErrorCode;
import com.study.spring.global.GlobalExceptionHandler;
import com.study.spring.jwt.JwtUtil;
import com.study.spring.repository.PostRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public Post savePost(PostRequestDTO requestDTO, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);

        if (token == null || !jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.TOKEN_IS_NOT_VALID);
        }

        Claims claims = jwtUtil.getUserInfoFromToken(token);

        Post post = Post.createPost(requestDTO, claims.getSubject());

        return postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));
    }

    @Transactional
    public Post updatePost(Long postId, PostRequestDTO requestDTO, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);

        if (token == null || !jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.TOKEN_IS_NOT_VALID);
        }

        Claims claims = jwtUtil.getUserInfoFromToken(token);

        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));

        if (updatePost.getUpdatedAt().equals(claims.getSubject())) {
            updatePost.setTitle(requestDTO.title());
            updatePost.setContent(requestDTO.content());
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }
        return updatePost;
    }

    @Transactional
    public void delete(Long postId, PostRequestDTO requestDTO, HttpServletRequest request) {

        String token = jwtUtil.resolveToken(request);

        if (token == null || !jwtUtil.validateToken(token)) {
            throw new CustomException(ErrorCode.TOKEN_IS_NOT_VALID);
        }

        Claims claims = jwtUtil.getUserInfoFromToken(token);

        Post deletePost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));

        if (deletePost.getUpdatedAt().equals(claims.getSubject())) {
            deletePost.setPostStatus(PostStatus.DELETE);
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }
    }
}
