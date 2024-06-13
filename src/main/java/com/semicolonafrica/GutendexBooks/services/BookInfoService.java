package com.semicolonafrica.GutendexBooks.services;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.models.Book;
import com.semicolonafrica.GutendexBooks.data.models.User;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;

import java.util.List;


public interface BookInfoService {
    Book search(BookSearchRequest bookSearchRequest) throws BookNotFoundException;
//    BookSearchResponse getBookBy(Long id);
//    void addBookToReadingList(String email,Book book) throws UserNotFoundException;
//
//    List<Book> getReadList(String email) throws UserNotFoundException;
    Book getBookBy(Long id) throws BookNotFoundException;
    void addBookToReadingList(User user,Book book) throws UserNotFoundException;

    List<Book> getReadList(User user) throws UserNotFoundException;
}
