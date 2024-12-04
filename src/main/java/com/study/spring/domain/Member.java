package com.study.spring.domain;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleType role;

    @OneToMany(mappedBy = "member")
    List<Post> posts = new ArrayList<>();
}
