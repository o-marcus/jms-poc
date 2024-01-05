package com.jmspoc;

import java.util.List;

public record BookDTO(
        String title,
        List<String> authors,
        String genre,
        String content,
        double averageRating,
        int numberOfReviews
) {

}