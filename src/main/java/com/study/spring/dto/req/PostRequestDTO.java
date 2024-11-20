package com.study.spring.dto.req;

import jakarta.validation.constraints.NotNull;

public record PostRequestDTO(
        @NotNull
        String title,
        @NotNull
        String userName,
        @NotNull
        String password,
        @NotNull
        String content,
        @NotNull
        String author
) {

}
