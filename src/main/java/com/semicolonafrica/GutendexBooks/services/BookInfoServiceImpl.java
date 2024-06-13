package com.semicolonafrica.GutendexBooks.services;

import com.semicolonafrica.GutendexBooks.Exception.BookInfoException;
import com.semicolonafrica.GutendexBooks.Exception.BookNotFoundException;
import com.semicolonafrica.GutendexBooks.Exception.UserNotFoundException;
import com.semicolonafrica.GutendexBooks.data.models.Author;
import com.semicolonafrica.GutendexBooks.data.models.Book;
import com.semicolonafrica.GutendexBooks.data.models.User;
import com.semicolonafrica.GutendexBooks.data.repository.BookRepository;
import com.semicolonafrica.GutendexBooks.dto.Request.BookSearchRequest;
import com.semicolonafrica.GutendexBooks.dto.Response.BooksResponse;
import com.semicolonafrica.GutendexBooks.dto.Response.BookSearchResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpRequest;
import java.util.List;


@Service
@AllArgsConstructor
public class BookInfoServiceImpl implements BookInfoService {
    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
//    private final UserService userService;


    @Override
    public Book search(BookSearchRequest bookSearchRequest) throws BookNotFoundException {
        String uri = "";
            if (bookSearchRequest.getAuthorName() != null) {
                uri = searchByAuthorAndTitle(bookSearchRequest);
            } else {
                uri = searchByTitle(bookSearchRequest);
            }
            BooksResponse response = restTemplate.getForObject(uri, BooksResponse.class);
            if (response != null){
                return response.getResults().get(0);

            }
        throw new BookNotFoundException("Failed");
    }

//
//    @Override
//    public void addBookToReadingList(String email,Book book) throws UserNotFoundException {
////        book.setUser(userService.findUserByEmail(email));
//        bookRepository.save(book);
//    }
//
//    @Override
//    public List<Book> getReadList(String email) throws UserNotFoundException {
//       return bookRepository.findBookByUser(userService.findUserByEmail(email));
//
//    }
    @Override
    public Book getBookBy(Long id) throws BookNotFoundException {
       return bookRepository.findById(id).orElseThrow(()-> new BookNotFoundException("Book not found"));
    }

    @Override
    public void addBookToReadingList(User user,Book book) throws UserNotFoundException {
        book.setUser(user);
        bookRepository.save(book);
    }

    @Override
    public List<Book> getReadList(User user) throws UserNotFoundException {
       return bookRepository.findBookByUser(user);

    }


    private String searchByAuthorAndTitle(BookSearchRequest bookSearchRequest) {
        return "https://gutendex.com/books?search" + bookSearchRequest.getAuthorName() + "%20" + bookSearchRequest.getTitle();
    }

    private String searchByTitle(BookSearchRequest bookSearchRequest) {
        return String.format("https://gutendex.com/books?search=%s", bookSearchRequest.getTitle());
    }

}
