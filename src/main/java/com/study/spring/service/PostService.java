package com.study.spring.service;

import com.study.spring.domain.Post;
import com.study.spring.domain.PostStatus;
import com.study.spring.dto.PostDTO;
import com.study.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post savePost(Post post) {
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
    public Post updatePost(Long postId, Post post) {

        Post updatePost = postRepository.findOne(postId);

        if (post.getPassword().equals(updatePost.getPassword())) {
            updatePost.setTitle(post.getTitle());
            updatePost.setContent(post.getContent());
        } else {
            throw new IllegalStateException("비밀번호를 확인해주세요.");
        }

        return updatePost;
    }

    @Transactional
    public void delete(Long postId, Post post) {
        Post deletePost = postRepository.findOne(postId);

        if (post.getPassword().equals(deletePost.getPassword())) {
            deletePost.setPostStatus(PostStatus.DELETE);
        } else {
            throw new IllegalStateException("비밀번호를 확인해주세요.");
        }
    }
}
