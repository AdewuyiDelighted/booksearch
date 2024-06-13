package com.semicolonafrica.GutendexBooks.controller;

import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.InvalidDetailsException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.LoginRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequest;
import com.semicolonafrica.GutendexBooks.dto.Request.RegisterRequestTwo;
import com.semicolonafrica.GutendexBooks.dto.Response.ApiResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.LoginResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.RegisterResponse;
import com.semicolonafrica.GutendexBooks.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class BookInfoController {
    private final UserService userService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) throws UserNotFoundException {
        System.out.println(request);
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest request) throws UserNotFoundException, InvalidDetailsException {
        System.out.println(request);
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/registerTownCrier")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequestTwo request) {
        System.out.println("I am here");
        return ResponseEntity.ok(userService.register(request));
    }


    @PostMapping("/search")

    public ResponseEntity<?> search(@RequestBody BookSearchRequest bookSearchRequest) {
        try {
            return ResponseEntity.ok(userService.search(bookSearchRequest));
        } catch (UserNotFoundException | BookNotFoundException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Failed"));
        }
    }

    @PostMapping("/getReadingList")
    public ResponseEntity<?> getReadingList(@RequestParam(name = "email") String email) {
        try {
            return ResponseEntity.ok(userService.readingLists(email));
        } catch (UserNotFoundException exception) {
            return ResponseEntity.badRequest().body(new ApiResponse<>("Failed"));
        }
    }



    @GetMapping("/getReadingLists")
    public ResponseEntity<?> getReadingLists() {
        System.out.println("Hit");
        return ResponseEntity.ok().body(new ApiResponse<>("created"));
    }
}


