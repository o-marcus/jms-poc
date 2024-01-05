package com.jmspoc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmspoc.hexarchitecture.Producer;
import com.jmspoc.spi.producer.BookMessage;
import com.jmspoc.spi.producer.BookMessageProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;

@Producer
@RequiredArgsConstructor
public class BookProducer implements BookMessageProducer {

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper mapper;

    @Override
    public void notify(String queue, BookMessage message) {
        try {
            String bookJson = mapper.writeValueAsString(message);
            jmsTemplate.convertAndSend(queue, bookJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
