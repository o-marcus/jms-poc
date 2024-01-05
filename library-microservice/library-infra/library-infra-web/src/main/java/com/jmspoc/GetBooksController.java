package com.jmspoc;

import com.jmspoc.api.BookManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static com.jmspoc.BookMapper.booksResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class GetBooksController {
    private final BookManager service;
    @GetMapping
    public List<BookResponse> getBooks() {
        var books =  service.loadBooks();
        return booksResponse(books);
    }

}
