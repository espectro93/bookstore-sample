package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookView;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;

public record BookQueryEntity(@Id String id,
                              @Field String title,
                              @Field List<String> authors,
                              @Field String publishDate,
                              @Field int pages,
                              @Field String isbn,
                              @Field String publisherName,
                              @Field int stock) {
    public static BookView toDomain(BookQueryEntity entity) {
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
}
