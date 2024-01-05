package com.jmspoc.spi;

import com.jmspoc.model.dto.SearchBookDTO;

import java.util.List;

public interface LoadBooksUseCase {
     List<SearchBookDTO> loadAll();
      void updateCollection(SearchBookDTO document);
}
