package com.espectro93.examples.springgraphqlbookstore.core.domain;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.BaseEntity;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import java.time.ZonedDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order implements BaseEntity<OrderId> {

    @Builder.Default
    private final OrderId id = new OrderId(Identifiable.generateId());

    private final CustomerId customerId;

    @Builder.Default
    private final ZonedDateTime date = ZonedDateTime.now();

    private final List<BookId> bookIds;
}
