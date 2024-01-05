package com.jmspoc;

import com.jmspoc.model.Author;
import com.jmspoc.model.Book;

import java.util.List;

public class BookMapper {
    public static List<BookResponse> booksResponse(List<Book> books) {
        return books.stream().map(BookMapper::toBookResponse).toList();
    }

    public static BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .title(book.getTitle())
                .genre(book.getGenre())
                .averageRating(book.getAverageRating())
                .numberOfReviews(book.getNumberOfReviews())
                .content(book.getContent())
                .authors(book.getAuthors().stream().map(Author::getName).toList())
                .build();
    }
}
