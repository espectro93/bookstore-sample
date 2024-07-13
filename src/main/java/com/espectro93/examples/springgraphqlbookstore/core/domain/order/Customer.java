package com.espectro93.examples.springgraphqlbookstore.core.domain.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.Identifiable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Customer {

    @Builder.Default
    private final CustomerId id = new CustomerId(Identifiable.generateId());

    private final String email;
    private final String firstName;
    private final String lastName;
}
