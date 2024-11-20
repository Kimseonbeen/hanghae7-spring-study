package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.domain.PostStatus;
import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.dto.res.PostResponseDTO;
import com.study.spring.global.Exception.CustomException;
import com.study.spring.global.Exception.ErrorCode;
import com.study.spring.global.GlobalExceptionHandler;
import com.study.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Post savePost(PostRequestDTO requestDTO) {

        Post post = Post.createPost(requestDTO, passwordEncoder);

        return postRepository.save(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post findPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));
    }

    @Transactional
    public Post updatePost(Long postId, PostRequestDTO requestDTO) {

        Post updatePost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));

        if (passwordEncoder.matches(requestDTO.password(), updatePost.getPassword())) {
            updatePost.setTitle(requestDTO.title());
            updatePost.setContent(requestDTO.content());
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }

        return updatePost;
    }

    @Transactional
    public void delete(Long postId, PostRequestDTO requestDTO) {
        Post deletePost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_EXIST));

        if (requestDTO.password().equals(deletePost.getPassword())) {
            deletePost.setPostStatus(PostStatus.DELETE);
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }
    }
}
