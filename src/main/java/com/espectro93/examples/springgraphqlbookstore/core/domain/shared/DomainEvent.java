package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonSubTypes;
import com.couchbase.client.core.deps.com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookAddedToCatalogEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.StockDecreasedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.StockIncreasedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderCancelledEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.order.OrderPlacedEvent;
import java.time.Instant;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "_type"
)
@JsonSubTypes(
    {
        @JsonSubTypes.Type(
            value = BookAddedToCatalogEvent.class,
            name = "BookAddedToCatalogEvent"
        ),
        @JsonSubTypes.Type(
            value = OrderCancelledEvent.class,
            name = "OrderCancelledEvent"
        ),
        @JsonSubTypes.Type(
            value = OrderPlacedEvent.class,
            name = "OrderPlacedEvent"
        ),
        @JsonSubTypes.Type(
            value = StockDecreasedEvent.class,
            name = "StockDecreasedEvent"
        ),
        @JsonSubTypes.Type(
            value = StockIncreasedEvent.class,
            name = "StockIncreasedEvent"
        ),
    }
)
public interface DomainEvent {
    String eventId();
    Identifiable aggregateId();
    Instant eventTime();
    String eventType();
}
