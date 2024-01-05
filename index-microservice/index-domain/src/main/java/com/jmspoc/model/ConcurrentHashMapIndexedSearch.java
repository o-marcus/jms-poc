package com.jmspoc.model;

import com.jmspoc.hexarchitecture.ValueObject;
import com.jmspoc.model.dto.SearchBookDTO;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@ValueObject
 public class ConcurrentHashMapIndexedSearch
        implements IndexedSearch {

    private final ConcurrentHashMap<String, Set<SearchBookDTO>> index =  new ConcurrentHashMap<>();

    @Override
    public void addDocument(SearchBookDTO document, String content) {
        String[] terms = content.toLowerCase().split(" ");
        for (String term : terms) {
            var documentContent = document.content().toLowerCase();
            if (documentContent.contains(term)) {
                index.computeIfAbsent(term, k -> new HashSet<>()).add(document);
            }
        }
    }

    @Override
    public Set<SearchBookDTO> getDocumentsForTerm(String term) {
        return index.getOrDefault(term, Collections.emptySet());
    }

    @Override
    public void printInvertedIndex() {
        for (Map.Entry<String, Set<SearchBookDTO>> entry : index.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
