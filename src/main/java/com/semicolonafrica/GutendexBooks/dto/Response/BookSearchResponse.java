package com.semicolonafrica.GutendexBooks.dto.Response;

import com.semicolonafrica.GutendexBooks.data.models.Author;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookSearchResponse {
    private Integer id;
    private String title;
    private Long bookId;
    private List<Author> authors;
    private List<String> subjects;
    private List<String> bookshelves;
    private List<String> languages;
}
