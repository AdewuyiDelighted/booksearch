package com.semicolonafrica.GutendexBooks.services;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.InvalidDetailsException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.models.User;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.LoginRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequestTwo;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.LoginResponse;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.RegisterResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {

    RegisterResponse register(RegisterRequest registerRequest) throws UserNotFoundException;
    RegisterResponse register(RegisterRequestTwo registerRequest);

    LoginResponse login(LoginRequest loginRequest) throws InvalidDetailsException, UserNotFoundException;

    BookSearchResponse search(BookSearchRequest request) throws UserNotFoundException, BookNotFoundException;

    List<BookSearchResponse> readingLists(String email) throws UserNotFoundException;
    User findUserByEmail(String email) throws UserNotFoundException;
}
