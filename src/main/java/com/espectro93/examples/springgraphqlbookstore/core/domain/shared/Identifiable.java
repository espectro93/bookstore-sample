package com.espectro93.examples.springgraphqlbookstore.core.domain.shared;

import java.util.UUID;

public interface Identifiable {
    static String generateId() {
        return UUID.randomUUID().toString();
    }

    String id();
}
