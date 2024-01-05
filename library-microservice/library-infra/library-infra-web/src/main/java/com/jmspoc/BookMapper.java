package com.jmspoc;

import com.jmspoc.dto.BookRequest;
import com.jmspoc.dto.BookResponse;
import com.jmspoc.model.Author;
import com.jmspoc.model.Book;

import java.util.List;

public class BookMapper {

    public static List<BookResponse> booksResponse(List<Book> books) {
        return books
                .stream()
                .map(BookMapper::toBookResponse)
                .toList();
    }

    public static BookResponse toBookResponse(Book book) {
        return new BookResponse(
                book.getTitle(),
                book.getAuthors().stream().map(Author::getName).toList(), book.getGenre(),
                book.getContent(),
                book.getAverageRating(),
                book.getNumberOfReviews()
        );
    }

    public static Book toBook(BookRequest book) {
        return Book.builder()
                .title(book.title())
                .authors(book.authors())
                .content(book.content())
                .averageRating(book.averageRating())
                .numberOfReviews(book.numberOfReviews())
                .build();
    }

}
