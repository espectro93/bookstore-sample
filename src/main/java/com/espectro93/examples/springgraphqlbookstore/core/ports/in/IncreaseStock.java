package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.book.IncreaseStockCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.Book;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface IncreaseStock extends UseCase<IncreaseStockCommand, Book> {
}
