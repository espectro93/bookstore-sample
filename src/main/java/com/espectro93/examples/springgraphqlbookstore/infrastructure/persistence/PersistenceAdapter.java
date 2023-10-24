package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.Order;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBook;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.LoadBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveBooks;
import com.espectro93.examples.springgraphqlbookstore.core.ports.out.SaveOrder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PersistenceAdapter implements LoadBook, LoadBooks, SaveBooks, SaveOrder
{
    private final DomainEventRepository eventRepository;
    @Override
    public Book loadBy(BookId bookId) {
        var events = eventRepository.findAllById(Collections.singleton(bookId.id()));
        return Book.addBookToCatalog();
    }

    @Override
    public Page<Book> loadByPageable(Pageable pageable) {
        return null;
    }

    @Override
    public List<Book> loadByIds(List<BookId> bookIds) {
        return null;
    }

    @Override
    public void save(List<Book> books) {

    }

    @Override
    public Order save(Order order) {
        return null;
    }
}
