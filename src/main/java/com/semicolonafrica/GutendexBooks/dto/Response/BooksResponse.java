package com.semicolonafrica.GutendexBooks.dto.Response;

import com.semicolonafrica.GutendexBooks.data.models.Book;
import lombok.*;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BooksResponse {
    private int count;
    private String next;
    private String previous;
    private List<Book> results;
}
