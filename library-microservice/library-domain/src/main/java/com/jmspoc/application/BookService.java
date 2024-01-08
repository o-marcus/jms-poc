package com.jmspoc.application;

import com.jmspoc.api.BookManager;
import com.jmspoc.hexarchitecture.UseCase;
import com.jmspoc.model.Book;
import com.jmspoc.spi.producer.BookMessage;
import com.jmspoc.spi.producer.BookMessageProducer;
import com.jmspoc.spi.persistence.GetBooksPort;
import com.jmspoc.spi.persistence.InsertBookPort;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class BookService implements BookManager {

    private final GetBooksPort loader;
    private final InsertBookPort insertion;
    private final BookMessageProducer producer;

    @Override
    public List<Book> loadBooks() {
        return loader.getBooks()
                .stream()
                .sorted(Comparator.comparing(Book::getAverageRating).reversed())
                .toList();
    }

    @Override
    public void addBook(Book book) {
        insertion.addBook(book);
        producer.notify("Queue.book", new BookMessage(book));
    }

}
