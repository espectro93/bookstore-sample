package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookCommandPersistenceAdapter implements BookCommandPort {

    private final DomainEventRepository domainEventRepository;

    @Override
    public Book loadBy(BookId bookId) {
        var events = domainEventRepository.findAllById(Collections.singleton(bookId.id()));
        return Book.rehydrate(events.stream().map(DomainEventEntity::data).toList());
    }

    @Override
    public void save(Book book) {
        domainEventRepository.saveAll(book.getUncommittedEvents().stream().map(DomainEventEntity::createFrom).toList());
    }
}
