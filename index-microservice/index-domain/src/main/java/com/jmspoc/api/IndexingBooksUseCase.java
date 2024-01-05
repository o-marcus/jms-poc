package com.jmspoc.api;

import com.jmspoc.model.dto.SearchBookDTO;
import java.util.Set;

public interface IndexingBooksUseCase {
    void applyIndex(String term);
    Set<SearchBookDTO> indexedBooksFor(String term);
}
