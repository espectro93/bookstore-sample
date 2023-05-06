package com.espectro93.examples.springgraphqlbookstore.core.application;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Command;

public record GetBooksPagedCommand(int page, int size) implements Command {}
