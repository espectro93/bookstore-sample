package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.*;
import java.util.List;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

@Builder(toBuilder = true)
public record BookQueryEntity(
    @Id String id,
    @Field String title,
    @Field List<String> authors,
    @Field String publishDate,
    @Field int pages,
    @Field String isbn,
    @Field String publisherName,
    @Field int stock
) {
    public static BookView toView(BookQueryEntity entity) {
        return new BookView(
            new BookId(entity.id()),
            entity.title(),
            entity.isbn(),
            entity.authors(),
            entity.publishDate(),
            entity.publisherName(),
            entity.pages(),
            entity.stock()
        );
    }

    public static BookQueryEntity fromEvent(
        BookAddedToCatalogEvent bookAddedToCatalogEvent
    ) {
        return new BookQueryEntity(
            bookAddedToCatalogEvent.aggregateId().id(),
            bookAddedToCatalogEvent.title(),
            bookAddedToCatalogEvent.authors(),
            bookAddedToCatalogEvent.publishDate(),
            bookAddedToCatalogEvent.pages(),
            bookAddedToCatalogEvent.isbn(),
            bookAddedToCatalogEvent.publisherName(),
            bookAddedToCatalogEvent.stock()
        );
    }

    public BookQueryEntity fromEvent(StockDecreasedEvent stockDecreasedEvent) {
        return this.toBuilder()
            .stock(stock - stockDecreasedEvent.quantity())
            .build();
    }

    public BookQueryEntity fromEvent(StockIncreasedEvent stockIncreasedEvent) {
        return this.toBuilder()
            .stock(stock + stockIncreasedEvent.quantity())
            .build();
    }
}
