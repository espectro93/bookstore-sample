package com.espectro93.examples.springgraphqlbookstore.core.application.order;

import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderEventPublisher {
    void publish(List<DomainEvent> events) {}
}
