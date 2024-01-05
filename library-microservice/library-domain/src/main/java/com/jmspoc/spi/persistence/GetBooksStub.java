package com.jmspoc.spi.persistence;

import com.jmspoc.hexarchitecture.PersistenceAdapter;
import com.jmspoc.model.Author;
import com.jmspoc.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@PersistenceAdapter
public class GetBooksStub implements
        GetBooksPort, InsertBookPort {

    private final static Random random = new Random();
    private static final List<Book> list = new ArrayList<>();

    static  {
        var books =  IntStream
                .range(0, 10)
                .mapToObj(GetBooksStub::book)
                .toList();

        list.addAll(books);
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    private static Book book(int volume) {
        return new Book(
                UUID.randomUUID(),
                "SOLID VOLUME " + volume,
                List.of(new Author("Author #" + random.nextInt())),
                "Tecnologia",
                "Esse livro explica sobre SOLID usando exemplos fáceis de entender",
                random.nextDouble(),
                random.nextInt()
        );
    }

    @Override
    public void addBook(Book book) {
        list.add(book);
    }
}
