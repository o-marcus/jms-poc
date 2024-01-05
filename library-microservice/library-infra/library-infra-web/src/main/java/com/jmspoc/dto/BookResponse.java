package com.jmspoc.dto;

import java.util.List;

public record BookResponse (
     String title,
    List<String> authors,
    String genre,
    String content,
    double averageRating,
    int numberOfReviews
) {

}
