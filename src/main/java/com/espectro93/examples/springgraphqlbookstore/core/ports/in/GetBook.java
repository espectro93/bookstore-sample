package com.espectro93.examples.springgraphqlbookstore.core.ports.in;

import com.espectro93.examples.springgraphqlbookstore.core.application.BookView;
import com.espectro93.examples.springgraphqlbookstore.core.application.GetBookCommand;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.UseCase;

public interface GetBook extends UseCase<GetBookCommand, BookView> {
}
