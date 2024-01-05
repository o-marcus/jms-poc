package com.jmspoc.model;

import com.jmspoc.hexarchitecture.ValueObject;
import com.jmspoc.model.dto.SearchBookDTO;
import org.apache.catalina.LifecycleState;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@ValueObject
 public class ConcurrentHashMapIndexedSearch
        implements IndexedSearch {

    private final ConcurrentHashMap<String, Set<SearchBookDTO>> index =  new ConcurrentHashMap<>();
    private final List<String> lastTerms = new ArrayList<>();

    @Override
    public void addDocument(SearchBookDTO document, String content) {
        String[] terms = content.toLowerCase().split(" ");
        for (String term : terms) {
            lastTerms.add(term);
            putContent(document, term);
        }
    }

    private void putContent(
            SearchBookDTO document,
            String token
    ) {
        var content = document.content().toLowerCase();
        if (content.contains(token)) {
            index.computeIfAbsent(token, k -> new HashSet<>()).add(document);
        }
    }

    @Override
    public Set<SearchBookDTO> getDocumentsForTerm(String term) {
        return index.getOrDefault(term, Collections.emptySet());
    }

    @Override
    public void updateCollection(SearchBookDTO document) {
        lastTerms.forEach(token -> {
            putContent(document, token);
        });
    }

    @Override
    public void printInvertedIndex() {
        for (Map.Entry<String, Set<SearchBookDTO>> entry : index.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
