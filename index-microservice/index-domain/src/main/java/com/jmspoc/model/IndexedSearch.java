package com.jmspoc.model;

import com.jmspoc.model.dto.SearchBookDTO;

import java.util.Set;

public interface IndexedSearch {
     void addDocument(SearchBookDTO document, String content);
    Set<SearchBookDTO> getDocumentsForTerm(String term);
    void updateCollection(SearchBookDTO document);
    void printInvertedIndex();
}
