package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.Book;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import java.util.List;

@Document
public record BookEntity(@Id
                   String id,
                         @Field
                   String title,
                         @Field
                   List<String> authors,
                         @Field
                   String publishDate,
                         @Field
                   int pages,
                         @Field
                   String isbn,
                         @Field
                   String publisherName,
                         @Field
                   int stock
) {
    public static BookEntity fromDomain(Book book) {
        throw new UnsupportedOperationException("to be implemented");
    }
}
