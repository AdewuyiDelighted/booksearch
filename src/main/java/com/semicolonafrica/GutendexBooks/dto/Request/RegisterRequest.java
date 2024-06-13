package com.semicolonafrica.GutendexBooks.dto.Request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
}

