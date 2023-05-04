package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.BooksView;
import com.espectro93.examples.springgraphqlbookstore.core.application.GetBooksCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface GetBooks extends UseCase<GetBooksCommand, BooksView> {
}
