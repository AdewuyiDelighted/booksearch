package com.semicolonafrica.GutendexBooks.service;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.models.Book;
import com.semicolonafrica.GutendexBooks.data.models.User;
import com.semicolonafrica.GutendexBooks.data.repository.BookRepository;
import com.semicolonafrica.GutendexBooks.dto.Request.AddToReadingListRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;
import com.semicolonafrica.GutendexBooks.services.BookInfoService;
import com.semicolonafrica.GutendexBooks.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class
BookInfoServiceTest {
    @Autowired
    private BookInfoService bookInfoService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserService userService;

    @Test
    public void testSearchByTitle() {
        BookSearchRequest bookSearchRequest = new BookSearchRequest();
        bookSearchRequest.setTitle("Great");
        try {
            Book book = bookInfoService.search(bookSearchRequest);
            System.out.println(book);
            assertThat(book).isNotNull();
        } catch (BookNotFoundException exception) {
            System.err.println(exception.getMessage());
        }
    }

    @Test
    public void testSearchByTitleAndAuthorNameFunction() {
        BookSearchRequest bookSearchRequest = new BookSearchRequest();
        bookSearchRequest.setAuthorName("dickens");
        bookSearchRequest.setTitle("Great");
        try {
            Book book = bookInfoService.search(bookSearchRequest);
            assertThat(book).isNotNull();
        } catch (BookNotFoundException exception) {
            System.err.println(exception.getMessage());
        }

    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testAddToReadingList() throws BookNotFoundException {
        Book book = bookInfoService.getBookBy(100L);
        try {
            User user = userService.findUserByEmail("xplorer@email.com");
            bookInfoService.addBookToReadingList(user, book);
            assertEquals(1, bookInfoService.getReadList(user).size());
        }catch (UserNotFoundException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Test
    @Sql(scripts = "/scripts/insert.sql")
    public void testThatAllBookInReadListCanBeFound() throws UserNotFoundException {
        Book book = new Book();
        Book book1 = new Book();
        User user = userService.findUserByEmail("semicolon@email.com");
        bookInfoService.addBookToReadingList(user, book);
        bookInfoService.addBookToReadingList(user, book1);
        List<Book> books = bookInfoService.getReadList(user);
        assertThat(books).hasSize(2);

    }

}
