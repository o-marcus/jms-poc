package com.jmspoc;

import com.jmspoc.api.IndexingBooksUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/index")
public class IndexSearchController {
    private final IndexingBooksUseCase index;
    @GetMapping
    public void setIndexFor(@RequestBody String tokens) {
        index.applyIndex(tokens);
    }
}
