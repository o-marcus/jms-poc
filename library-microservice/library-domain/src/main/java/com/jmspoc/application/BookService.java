package com.jmspoc.application;

import com.jmspoc.api.BookManager;
import com.jmspoc.hexarchitecture.UseCase;
import com.jmspoc.model.Book;
import com.jmspoc.spi.GetBooksPort;
import lombok.RequiredArgsConstructor;
import java.util.Comparator;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class BookService implements BookManager {

    private final GetBooksPort loader;

    @Override
    public List<Book> loadBooks() {
        return loader.getBooks()
                .stream()
                .sorted(Comparator.comparing(Book::getAverageRating).reversed())
                .toList();
    }
}
