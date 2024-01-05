package com.jmspoc.model;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Book {
    private UUID id;
    private String title;
    private List<Author> authors;
    private String genre;
    private String content;
    private Double averageRating;
    private int numberOfReviews;
}
