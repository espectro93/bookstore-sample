package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.CustomerId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import java.util.List;

public record OrderBooksCommand(CustomerId customerId, List<BookId> bookIds)
    implements Command {}
