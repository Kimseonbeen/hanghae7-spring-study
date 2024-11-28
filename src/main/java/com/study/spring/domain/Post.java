package com.study.spring.domain;

import com.study.spring.dto.req.PostRequestDTO;
import com.study.spring.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Getter
@Setter 
@Builder
@NoArgsConstructor  // 기본 생성자 자동 생성 
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자 자동 생성
// @RequiredArgsConstructor -> 필수 인자를 받는 생성자를 자동으로 생성 final 필드 또는 @NonNUll 붙은 필드만 생성
// @DynamicInsert  // entity를 save시 null 값은 배제하고 insert 실행
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String username;
    private String content;
//    private String password;
    private String author;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // columnDefinition 제약사항만 넣어줄뿐, null 값을 defalut value로 치환해주지는 않음
    @Column(name = "POST_STATUS")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    public static Post createPost(PostRequestDTO requestDTO, String username) {
        return Post.builder()
                .title(requestDTO.title())
                .username(username)
//                .password(passwordEncoder.encode(requestDTO.password()))
                .content(requestDTO.content())
                .author(requestDTO.author())
                .postStatus(PostStatus.ACTIVE)
                .build();
    }
}


