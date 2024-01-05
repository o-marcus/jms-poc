package com.jmspoc;

import com.jmspoc.model.dto.SearchBookDTO;

public class SearchBookMapper {
    static SearchBookDTO map(BookDTO dto) {
        return new SearchBookDTO(dto.title(), dto.content());
    }
}
