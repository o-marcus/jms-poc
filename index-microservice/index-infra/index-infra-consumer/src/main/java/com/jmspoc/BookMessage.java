package com.jmspoc;

import java.util.List;

public record BookMessage(
        String title,
        List<AuthorMessage> authors,
        String genre,
        String content,
        Double averageRating,
        int numberOfReviews
)  {
    public record AuthorMessage(String name) {
    }
}

