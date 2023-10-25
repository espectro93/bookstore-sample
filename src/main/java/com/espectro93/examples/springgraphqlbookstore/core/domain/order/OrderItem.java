package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;

public record OrderItem(BookId bookId, String title, String isbn, int quantity) {
}
