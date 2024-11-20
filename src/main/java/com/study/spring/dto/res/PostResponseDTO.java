package com.study.spring.dto.res;

import com.study.spring.domain.Post;

public record PostResponseDTO(
        Long id,
        String title,
        String username,
        String content
) {

    public static PostResponseDTO from(Post post) {
        return new PostResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getUsername(),
                post.getContent()
        );
    }
}
