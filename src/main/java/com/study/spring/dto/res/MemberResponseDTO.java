package com.study.spring.dto.res;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDTO {

    private String username;
    private String password;
}
