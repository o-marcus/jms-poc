package com.jmspoc.application;

import com.jmspoc.api.IndexingBooksUseCase;
import com.jmspoc.hexarchitecture.UseCase;
import com.jmspoc.model.IndexedSearch;
import com.jmspoc.model.dto.SearchBookDTO;
import com.jmspoc.spi.LoadBooksUseCase;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.Set;

@UseCase
@RequiredArgsConstructor
public class IndexingService
        implements IndexingBooksUseCase {

    private final LoadBooksUseCase loader;
    private final IndexedSearch index;

    public void applyIndex(String content) {
        List<SearchBookDTO> books = loader.loadAll();
        books.forEach(book -> {
            index.addDocument(book, content);
        });
        index.printInvertedIndex();
    }

    @Override
    public Set<SearchBookDTO> indexedBooksFor(String term) {
        return index.getDocumentsForTerm(term);
    }

    private List<SearchBookDTO> getooks() {
        return loader.loadAll();
    }
}
