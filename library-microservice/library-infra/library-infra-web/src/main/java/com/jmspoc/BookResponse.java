package com.jmspoc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PRIVATE)
public class BookResponse {
    private String title;
    private List<String> authors;
    private String genre;
    private String content;
    private double averageRating;
    private int numberOfReviews;
}
