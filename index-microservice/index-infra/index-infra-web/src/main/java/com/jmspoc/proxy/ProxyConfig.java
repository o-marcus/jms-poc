package com.jmspoc.proxy;

import com.jmspoc.api.IndexingBooksUseCase;
import com.jmspoc.application.IndexingService;
import com.jmspoc.resilience.DefaultResilience;
import com.jmspoc.model.IndexedSearch;
import com.jmspoc.spi.LoadBooksUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {
    @Bean
    public IndexingBooksUseCase getBookManager(
            LoadBooksUseCase loader,
            IndexedSearch index,
            DefaultResilience defaultResilience
    ) {
        return new IndexingBooksProxy(new IndexingService(loader, index), defaultResilience);
    }



}
