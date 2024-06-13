package com.semicolonafrica.GutendexBooks.dto.Request;

import com.semicolonafrica.GutendexBooks.data.models.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BookSearchRequest {
    private String userEmail;
    private String title;
    private String  authorName;

}
