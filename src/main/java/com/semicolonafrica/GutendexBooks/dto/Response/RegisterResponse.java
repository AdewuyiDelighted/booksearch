package com.semicolonafrica.GutendexBooks.dto.Response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RegisterResponse {
    private Long id;
    private String message;
    private boolean status;

    public RegisterResponse(String message){
        this.message = message;
    }}
