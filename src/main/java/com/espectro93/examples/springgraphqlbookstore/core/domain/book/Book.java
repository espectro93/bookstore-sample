package com.espectro93.examples.springgraphqlbookstore.core.domain.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.AggregateRoot;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Book implements AggregateRoot<BookId, Book> {

    @Builder.Default
    private final BookId id = new BookId(Identifiable.generateId());

    private final String title;
    private final List<String> authors;
    private final String publishDate;
    private final int pages;
    private final String isbn;
    private final String publisherName;
    private final int stock;
    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

    public static Book createBook(String title, List<String> authors, String publishDate, int pages, String isbn, String publisherName, int stock) {
        var book = Book.builder()
                .title(title)
                .authors(authors)
                .publishDate(publishDate)
                .pages(pages)
                .isbn(isbn)
                .publisherName(publisherName)
                .stock(stock)
                .build();
        var bookAddedToCatalogEvent = book.createBookAddedToCatalogEvent();
        return book.applyEvent(bookAddedToCatalogEvent);
    }

    private BookAddedToCatalogEvent createBookAddedToCatalogEvent() {
        return BookAddedToCatalogEvent.builder()
                .title(title)
                .authors(authors)
                .publishDate(publishDate)
                .pages(pages)
                .isbn(isbn)
                .publisherName(publisherName)
                .stock(stock)
                .build();
    }
    public Book increaseStock(int quantity) {
        var stockIncreasedEvent = new StockIncreasedEvent(quantity);
        return applyEvent(stockIncreasedEvent);
    }

    public Book decreaseStock(int quantity) {
        var stockDecreasedEvent = new StockDecreasedEvent(quantity);
        return applyEvent(stockDecreasedEvent);
    }

    @Override
    public Book applyEvent(DomainEvent event) {
        var aggregate = switch (event) {
            case StockIncreasedEvent stockIncreasedEvent -> {
                var newStock = stock + stockIncreasedEvent.quantity();
                yield Book.builder()
                        .stock(newStock)
                        .build();
            }
            case StockDecreasedEvent stockDecreasedEvent -> {
                if (stockDecreasedEvent.quantity() <= stock) {
                    yield Book.builder()
                            .stock(stock - stockDecreasedEvent.quantity())
                            .build();
                } else throw new IllegalArgumentException(String.format("not enough stock for this book %s", title));
            }
            case BookAddedToCatalogEvent ignored -> this;
            default -> throw new IllegalArgumentException("event type is not supported");
        };
        aggregate.uncommittedEvents.add(event);
        return aggregate;
    }
}
