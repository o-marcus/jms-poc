package com.jmspoc.spi.producer;

import com.jmspoc.model.Book;

import java.io.Serializable;
import java.util.List;

public record BookMessage(
        String title,
        List<AuthorMessage> authors,
        String genre,
        String content,
        Double averageRating,
        int numberOfReviews
) {

    public BookMessage(Book book) {
        this(
                book.getTitle(),
                book.getAuthors().stream().map(author -> new BookMessage.AuthorMessage(author.getName())).toList(),
                book.getGenre(),
                book.getContent(),
                book.getAverageRating(),
                book.getNumberOfReviews()
        );
    }
    public record AuthorMessage(String name) {
    }
}

