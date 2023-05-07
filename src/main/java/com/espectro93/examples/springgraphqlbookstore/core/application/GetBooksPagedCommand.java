package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;
import org.springframework.data.domain.PageRequest;

public record GetBooksPagedCommand(PageRequest pageRequest)
    implements Command {}
