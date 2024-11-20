package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.domain.PostStatus;
import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.global.response.CustomException;
import com.study.spring.global.response.ErrorCode;
import com.study.spring.repository.PostRepository;
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

    @Transactional
    public Post savePost(PostRequestDTO requestDTO) {

        Post post = Post.createPost(requestDTO, passwordEncoder);

        return postRepository.savePost(post);
    }

    public List<Post> findPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public Post findPost(Long postId) {
        return postRepository.findOne(postId);
    }

    @Transactional
    public Post updatePost(Long postId, PostRequestDTO requestDTO) {

        Post updatePost = postRepository.findOne(postId);

        if (requestDTO.password().equals(updatePost.getPassword())) {
            updatePost.setTitle(requestDTO.title());
            updatePost.setContent(requestDTO.content());
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }

        return updatePost;
    }

    @Transactional
    public void delete(Long postId, PostRequestDTO requestDTO) {
        Post deletePost = postRepository.findOne(postId);

        if (requestDTO.password().equals(deletePost.getPassword())) {
            deletePost.setPostStatus(PostStatus.DELETE);
        } else {
            throw new CustomException(ErrorCode.PASSWORD_ERROR);
        }
    }
}
