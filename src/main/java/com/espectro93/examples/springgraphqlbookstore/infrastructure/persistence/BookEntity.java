package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

@Document
public record BookEntity(
    @Id String id,
    @Field String title,
    @Field List<String> authors,
    @Field String publishDate,
    @Field int pages,
    @Field String isbn,
    @Field String publisherName,
    @Field int stock
) {
    public static BookEntity fromDomain(Book book) {
        return new BookEntity(
            book.getId().id(),
            book.getTitle(),
            book.getAuthors(),
            book.getPublishDate(),
            book.getPages(),
            book.getIsbn(),
            book.getPublisherName(),
            book.getStock()
        );
    }

    public static Book toDomain(BookEntity entity) {
        return Book
            .builder()
            .id(new BookId(entity.id()))
            .title(entity.title())
            .authors(entity.authors())
            .publishDate(entity.publishDate())
            .pages(entity.pages())
            .isbn(entity.isbn())
            .publisherName(entity.publisherName())
            .stock(entity.stock())
            .build();
    }
}
