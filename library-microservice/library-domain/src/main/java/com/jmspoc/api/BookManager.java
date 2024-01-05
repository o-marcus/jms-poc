package com.jmspoc.api;

import com.jmspoc.model.Book;

import java.util.List;

public interface BookManager {
    List<Book> loadBooks();
    void addBook(Book book);
}
