package com.jmspoc;

import com.jmspoc.api.BookManager;
import com.jmspoc.dto.BookRequest;
import com.jmspoc.dto.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static com.jmspoc.BookMapper.booksResponse;
import static com.jmspoc.BookMapper.toBook;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BooksController {

    private final BookManager service;

    @GetMapping
    public List<BookResponse> getBooks() {
        var books =  service.loadBooks();
        return booksResponse(books);
    }

    @PostMapping
    public ResponseEntity<String> addNewBook(@RequestBody BookRequest bookRequest) {
        service.addBook(toBook(bookRequest));
        return ResponseEntity.ok("inserted");
    }

}
