package com.jmspoc.dto;

import com.jmspoc.model.Author;

import java.util.List;

public record BookRequest(
        String title,
        List<Author> authors,
        String genre,
        String content,
        Double averageRating,
        int numberOfReviews
) {
}
