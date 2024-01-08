package com.jmspoc.proxy;

import com.jmspoc.api.IndexingBooksUseCase;
import com.jmspoc.resilience.DefaultResilience;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@RequiredArgsConstructor
class IndexingBooksProxyTest {

    @MockBean
    private IndexingBooksUseCase service;
    @Autowired
    private DefaultResilience resilience;
    private IndexingBooksProxy proxy;

    @BeforeEach
    public void getProxy() {
        proxy = new IndexingBooksProxy(service, resilience);
    }

    @Test
    public void shouldOpenCircuitBreakerWhenFailMoreThanFiveTimes() {
        when(service.indexedBooksFor(any())).thenThrow(new RuntimeException());
        for (int i = 0; i < 50; i++) {
            try {
                proxy.applyIndex("solid book");
            } catch (Exception ignore) {

            }
        }
        verify(service, times(5)).applyIndex(any());
    }

    @Test
    public void shouldRetryWhenFail() {
        when(service.indexedBooksFor(any()))
                .thenThrow(new RuntimeException("First Error"))
                .thenThrow(new RuntimeException("Second Error"))
                .thenReturn(null);

        try {
            proxy.applyIndex("solid book");
        }
        catch (Exception e) {
            verify(service, times(3)).applyIndex(any());
        }
    }

}