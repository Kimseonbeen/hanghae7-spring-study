package com.study.spring.dto;

import com.study.spring.domain.Post;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
//    private Long memberId; // 실제 `Member` 엔티티 대신 ID만 직렬화

    @Builder
    public PostDTO(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }

}
