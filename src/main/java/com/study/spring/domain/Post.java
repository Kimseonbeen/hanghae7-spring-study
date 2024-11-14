package com.study.spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@DynamicInsert  // entity를 save시 null 값은 배제하고 insert 실행
@EntityListeners(AuditingEntityListener.class)  // 자동 날짜 생성
public class Post {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;
    private String content;
    private String password;
    private String author;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // columnDefinition 제약사항만 넣어줄뿐, null 값을 defalut value로 치환해주지는 않음
    @Column(name = "POST_STATUS", columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", postStatus=" + postStatus +
                '}';
    }
}


