package com.espectro93.examples.springgraphqlbookstore.core.application.book;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;

public record DecreaseStockCommand(BookId bookId, int amount) implements Command {
}
