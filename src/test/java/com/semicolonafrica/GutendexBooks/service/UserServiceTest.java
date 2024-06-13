package com.semicolonafrica.GutendexBooks.service;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.InvalidDetailsException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.repository.UserRepository;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.LoginRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.LoginResponse;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.RegisterResponse;
import com.semicolonafrica.GutendexBooks.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;


    @Test public void newUserCanRegister() throws UserNotFoundException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Test");
        registerRequest.setEmail("testBook@email.com");
        registerRequest.setPassword("Test@1234");
        RegisterResponse response =  userService.register(registerRequest);
        assertThat(response).isNotNull();
        assertThat(response.getId()).isNotNull();
    }
    @Test public void newUserCanRegisterAndLogin() throws UserNotFoundException, InvalidDetailsException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("semicolons");
        registerRequest.setEmail("semicolon@email.com");
        registerRequest.setPassword("Test@1234");
        userService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("semicolon@email.com");
        loginRequest.setPassword("Test@1234");
        LoginResponse response =  userService.login(loginRequest);
        assertThat(response).isNotNull();
    }
    @Test public void testThatUserCanSearchForBook() throws UserNotFoundException, InvalidDetailsException, BookNotFoundException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("xplorer");
        registerRequest.setEmail("xplorer@email.com");
        registerRequest.setPassword("Test@1234");
        userService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("xplorer@email.com");
        loginRequest.setPassword("Test@1234");
        userService.login(loginRequest);
        BookSearchRequest request = new BookSearchRequest();
        request.setUserEmail("xplorer@email.com");
        request.setTitle("Great");
        BookSearchResponse bookSearchResponse = userService.search(request);
        assertThat(bookSearchResponse).isNotNull();
    }
    @Test public void testThatUserCanGetReadingList() throws UserNotFoundException, InvalidDetailsException, BookNotFoundException {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("obodo");
        registerRequest.setEmail("obodo@email.com");
        registerRequest.setPassword("Test@1234");
        userService.register(registerRequest);
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("obodo@email.com");
        loginRequest.setPassword("Test@1234");
        userService.login(loginRequest);
        BookSearchRequest request = new BookSearchRequest();
        request.setUserEmail("obodo@email.com");
        request.setTitle("Great");
        userService.search(request);
        List<BookSearchResponse> readingList = userService.readingLists("obodo@email.com");
        assertThat(readingList).hasSize(1);
    }

}
