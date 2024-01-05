package com.jmspoc.spi;

import com.jmspoc.model.Book;
import java.util.List;

public interface GetBooksPort {
    List<Book> getBooks();
}
