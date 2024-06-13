package com.semicolonafrica.GutendexBooks.data.repository;

import com.semicolonafrica.GutendexBooks.data.models.Book;
import com.semicolonafrica.GutendexBooks.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface
BookRepository extends JpaRepository<Book,Long> {
    List<Book> findBookByUser(User user);


}
