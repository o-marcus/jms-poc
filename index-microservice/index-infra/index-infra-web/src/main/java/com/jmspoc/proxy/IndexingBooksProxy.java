package com.jmspoc.proxy;

import com.jmspoc.api.IndexingBooksUseCase;
import com.jmspoc.resilience.ResilientService;
import com.jmspoc.model.dto.SearchBookDTO;
import lombok.RequiredArgsConstructor;
import java.util.Set;

@RequiredArgsConstructor
public class IndexingBooksProxy
        implements IndexingBooksUseCase {

    private final IndexingBooksUseCase service;
    private final ResilientService resilience;

    @Override
    public void applyIndex(String term) {
        resilience.process(() -> {
            service.applyIndex(term);
            return null;
        });
    }

    @Override
    public Set<SearchBookDTO> indexedBooksFor(String term) {
        return service.indexedBooksFor(term);
    }

}
