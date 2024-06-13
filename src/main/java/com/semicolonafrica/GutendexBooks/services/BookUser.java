package com.semicolonafrica.GutendexBooks.services;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.InvalidDetailsException;
import com.semicolonafrica.GutendexBooks.Exception.NoBookFoundException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.models.Book;
import com.semicolonafrica.GutendexBooks.data.models.User;
import com.semicolonafrica.GutendexBooks.data.repository.UserRepository;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.LoginRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequestTwo;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.LoginResponse;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.RegisterResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookUser implements UserService {
    private final UserRepository userRepository;
    private final BookInfoService bookInfoService;
    private final ModelMapper modelMapper;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) throws UserNotFoundException {
        User user = new User();
        if (!userExist(registerRequest.getEmail())) {
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(registerRequest.getPassword());
            userRepository.save(user);
            RegisterResponse response = new RegisterResponse();
            response.setId(user.getId());
            response.setMessage(registerRequest.getUsername() + " register successfully");
            return response;
        }
        throw new UserNotFoundException("User already exist");

    }


    @Override
    public RegisterResponse register(RegisterRequestTwo registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        userRepository.save(user);
        RegisterResponse response = new RegisterResponse();
        response.setId(user.getId());
        response.setMessage(registerRequest.getFullName() + " register successfully");
        response.setStatus(true);
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws InvalidDetailsException, UserNotFoundException {
        User user = findUserByEmail(loginRequest.getEmail());
        if (user.getPassword().equals(loginRequest.getPassword())) {
            LoginResponse loginResponse = new LoginResponse();
            user.setLocked(false);
            userRepository.save(user);
            loginResponse.setMessage("Account unlocked");
            loginResponse.setStatus(true);
            return loginResponse;
        }
        throw new InvalidDetailsException("Details are invalid");
    }


    @Override
    public BookSearchResponse search(BookSearchRequest request) throws UserNotFoundException, BookNotFoundException {
        User user = findUserByEmail(request.getUserEmail());
        Book book = bookInfoService.search(request);
        bookInfoService.addBookToReadingList(user, book);
        System.out.println(book);
        userRepository.save(user);
        return modelMapper.map(book, BookSearchResponse.class);


    }

    @Override
    public List<BookSearchResponse> readingLists(String email) throws UserNotFoundException {
        User user = findUserByEmail(email);
        List<Book> books = bookInfoService.getReadList(user);
        return books.stream()
                .map(book -> modelMapper.map(book, BookSearchResponse.class))
                .toList();
    }

    @Override
    public User findUserByEmail(String email) throws UserNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User doesnt exist"));
    }

    private boolean userExist(String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) throw new UserNotFoundException("User Already Exist");
        return false;
    }

}
