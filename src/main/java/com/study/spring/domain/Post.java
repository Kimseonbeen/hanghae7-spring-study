package com.study.spring.domain;

import com.study.spring.dto.req.PostRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter 
@Builder
@NoArgsConstructor
@AllArgsConstructor
// @DynamicInsert  // entity를 save시 null 값은 배제하고 insert 실행
@EntityListeners(AuditingEntityListener.class)  // 자동 날짜 생성
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String username;
    private String content;
    private String password;
    private String author;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // columnDefinition 제약사항만 넣어줄뿐, null 값을 defalut value로 치환해주지는 않음
    @Column(name = "POST_STATUS")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    public static Post createPost(PostRequestDTO requestDTO, PasswordEncoder passwordEncoder) {
        return Post.builder()
                .title(requestDTO.title())
                .username(requestDTO.userName())
                .password(passwordEncoder.encode(requestDTO.password()))
                .content(requestDTO.content())
                .author(requestDTO.author())
                .postStatus(PostStatus.ACTIVE)
                .build();
    }
}


