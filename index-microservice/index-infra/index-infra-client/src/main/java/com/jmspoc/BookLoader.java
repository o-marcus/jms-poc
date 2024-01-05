package com.jmspoc;

import com.jmspoc.hexarchitecture.ClientAPI;
import com.jmspoc.model.dto.SearchBookDTO;
import com.jmspoc.spi.LoadBooksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;

@ClientAPI
@RequiredArgsConstructor
public class BookLoader implements LoadBooksUseCase {

    private final WebClient webClient;
    private static final String URL = "http://localhost:8081/api/books";
    private List<SearchBookDTO> cash;

    @Override
    public List<SearchBookDTO> loadAll() {
        if (cash != null && !cash.isEmpty()) return cash;
        cash = getBooks();
        return cash;
    }

    private List<SearchBookDTO> getBooks() {
        BookDTO[] result = webClient.get()
                .uri(URL)
                .retrieve()
                .bodyToMono(BookDTO[].class)
                .block();

        if (result == null || result.length == 0)
            throw new RuntimeException();

        return Arrays.stream(result).map(SearchBookMapper::map).toList();
    }

}
