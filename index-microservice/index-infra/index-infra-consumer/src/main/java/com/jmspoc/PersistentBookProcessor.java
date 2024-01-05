package com.jmspoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmspoc.hexarchitecture.Consumer;
import com.jmspoc.model.dto.SearchBookDTO;
import com.jmspoc.spi.LoadBooksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;

@Consumer
@RequiredArgsConstructor
public class PersistentBookProcessor
        implements BookConsumerProcessor {

    private final LoadBooksUseCase loader;
    private final ObjectMapper objectMapper;

    @JmsListener(destination = "Queue.book")
    @Override
    public void process(String bookJson) {
        try {
            var message = objectMapper.readValue(bookJson, BookMessage.class);
            loader.updateCollection(new SearchBookDTO(message.title(), message.content()));
        }
        catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
