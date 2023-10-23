package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;

public record OrderItem(BookId bookId, int quantity) {
}
