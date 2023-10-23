package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import lombok.Builder;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record BookAddedToCatalogEvent(String eventId, Instant eventTime, String title, List<String> authors,
                                      String publishDate,
                                      int pages,
                                      String isbn,
                                      String publisherName,
                                      int stock) implements DomainEvent {

    @Builder
    public BookAddedToCatalogEvent(String title, List<String> authors,
                                   String publishDate,
                                   int pages,
                                   String isbn,
                                   String publisherName,
                                   int stock) {
        this(UUID.randomUUID().toString(), Instant.now(), title, authors, publishDate, pages, isbn, publisherName, stock);
    }
}
