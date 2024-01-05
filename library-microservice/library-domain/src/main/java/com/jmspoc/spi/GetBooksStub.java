package com.jmspoc.spi;

import com.jmspoc.hexarchitecture.PersistenceAdapter;
import com.jmspoc.model.Author;
import com.jmspoc.model.Book;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@PersistenceAdapter
public class GetBooksStub implements GetBooksPort {

    private final Random random = new Random();

    @Override
    public List<Book> getBooks() {
        return IntStream
                .range(0, 10)
                .mapToObj(this::book)
                .toList();
    }

    private Book book(int volume) {
        return new Book(
                UUID.randomUUID(),
                "SOLID VOLUME " + volume,
                List.of(new Author(UUID.randomUUID(), "Author #" + random.nextInt())),
                "Tecnologia",
                "Esse livro explica sobre SOLID usando exemplos fáceis de entender",
                random.nextDouble(),
                random.nextInt()
        );
    }

}
