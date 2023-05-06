package com.espectro93.examples.springgraphqlbookstore.core.domain;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;

public record OrderId(String id) implements Identifiable {}
