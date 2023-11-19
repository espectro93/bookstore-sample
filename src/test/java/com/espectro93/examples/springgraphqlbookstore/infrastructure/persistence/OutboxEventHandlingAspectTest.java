package com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence;

import com.espectro93.examples.springgraphqlbookstore.core.domain.book.BookId;
import com.espectro93.examples.springgraphqlbookstore.core.domain.book.StockIncreasedEvent;
import com.espectro93.examples.springgraphqlbookstore.core.domain.shared.DomainEvent;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxEntity;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxEventHandlingAspect;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxRepository;
import com.espectro93.examples.springgraphqlbookstore.infrastructure.outbox.OutboxState;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class OutboxEventHandlingAspectTest {

    @Mock
    private OutboxRepository outboxRepository;

    @InjectMocks
    private OutboxEventHandlingAspect outboxEventHandlingAspect;

    @Captor
    private ArgumentCaptor<OutboxEntity> outboxEntityCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAfterEventMethod_Success() {
        DomainEvent domainEvent = new StockIncreasedEvent("123",new BookId("123"), "StockDecreasedEvent", Instant.now(), 1);
        Object[] args = { domainEvent };

        when(outboxRepository.findById(anyString())).thenReturn(Optional.of(new OutboxEntity(domainEvent)));
        outboxEventHandlingAspect.afterEventMethod(createJoinPoint(args));

        verify(outboxRepository).findById(domainEvent.eventId());
        verify(outboxRepository).save(outboxEntityCaptor.capture());

        OutboxEntity capturedEntity = outboxEntityCaptor.getValue();
        assertEquals(OutboxState.PROCESSED, capturedEntity.state());
    }

    @Test
    void testAfterEventMethod_Failure() {
        DomainEvent domainEvent = new StockIncreasedEvent("123",new BookId("123"), "StockDecreasedEvent", Instant.now(), 1);
        Exception exception = new RuntimeException("Test Exception");
        Object[] args = { domainEvent, exception };

        when(outboxRepository.findById(anyString())).thenReturn(Optional.of(new OutboxEntity(domainEvent)));

        outboxEventHandlingAspect.afterEventMethod(createJoinPoint(args));

        verify(outboxRepository).findById(domainEvent.eventId());
        verify(outboxRepository).save(outboxEntityCaptor.capture());

        OutboxEntity capturedEntity = outboxEntityCaptor.getValue();
        assertEquals(OutboxState.FAILURE, capturedEntity.state());
    }

    private JoinPoint createJoinPoint(Object[] args) {
        JoinPoint joinPoint = mock(JoinPoint.class);
        when(joinPoint.getArgs()).thenReturn(args);
        return joinPoint;
    }
}
