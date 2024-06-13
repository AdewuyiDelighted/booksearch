package com.semicolonafrica.GutendexBooks.dto.Request;

import com.semicolonafrica.GutendexBooks.data.models.Book;
import lombok.Data;

@Data
public class AddToReadingListRequest {
    private Book book;

}
