package com.jmspoc.spi.producer;

public interface BookMessageProducer {
    void notify(String queue, BookMessage message);
}
