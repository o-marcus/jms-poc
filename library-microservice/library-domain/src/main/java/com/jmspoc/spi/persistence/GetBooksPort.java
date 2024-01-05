package com.jmspoc.spi.persistence;

import com.jmspoc.model.Book;
import java.util.List;

public interface GetBooksPort {
    List<Book> getBooks();
}
