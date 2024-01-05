package com.jmspoc.spi;

import com.jmspoc.model.dto.SearchBookDTO;

import java.util.List;

public interface LoadBooksUseCase {
    public List<SearchBookDTO> loadAll();
}
