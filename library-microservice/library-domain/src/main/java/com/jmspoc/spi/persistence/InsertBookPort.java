package com.jmspoc.spi.persistence;

import com.jmspoc.model.Book;

public interface InsertBookPort {
    void addBook(Book book);
}
