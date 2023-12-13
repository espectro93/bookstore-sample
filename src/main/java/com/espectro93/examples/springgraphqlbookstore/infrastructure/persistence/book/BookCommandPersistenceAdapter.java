package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.BookCommandPort;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.DomainEventRepository;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BookCommandPersistenceAdapter implements BookCommandPort {

    private final DomainEventRepository domainEventRepository;
    private final OutboxRepository outboxRepository;

    @Override
    public Book loadBy(BookId bookId) {
        var events = domainEventRepository.findAllById(Collections.singleton(bookId.id()));
        return Book.rehydrate(events.stream().map(DomainEventEntity::data).toList());
    }

    @Override
    public void save(Book book) {
        domainEventRepository.saveAll(book.getUncommittedEvents().stream().map(DomainEventEntity::createFrom).toList());
        outboxRepository.saveAll(book.getUncommittedEvents().stream().map(OutboxEntity::new).toList());
    }
}
