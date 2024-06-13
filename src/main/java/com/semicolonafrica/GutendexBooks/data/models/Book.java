package com.semicolonafrica.GutendexBooks.data.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long bookId;
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Author> authors;
    @ElementCollection
    private List<String> subjects;
    @ElementCollection
    private List<String> bookshelves;
    @ElementCollection
    private List<String> languages;
    @ManyToOne
    private User user;
}
