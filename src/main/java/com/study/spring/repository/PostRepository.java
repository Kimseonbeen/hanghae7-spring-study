package com.study.spring.repository;

import com.study.spring.domain.Post;
import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.dto.res.PostResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public Post savePost(Post post) {
        if (post.getId() == null) {
            em.persist(post);  // 새로운 엔티티라면 persist로 저장
        } else {
            em.merge(post);    // 이미 존재하는 엔티티라면 merge로 병합
        }

        return post;
    }

    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    public Post findOne(Long id) {
        return em.find(Post.class, id);
    }
}
