package com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging;


import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JmsPublisher {

    private final JmsTemplate jmsTemplate;

    public void sendMessage(String topic, OutboxEntity entity) {
        jmsTemplate.convertAndSend(topic, entity.serialize());
    }
}
