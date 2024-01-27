package com.espectro93.examples.springgraphqlbookstore.infrastructure.messaging;


import com.espectro93.examples.springgraphqlbookstore.infrastructure.persistence.outbox.OutboxEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JmsPublisher {

    private final JmsTemplate jmsTemplate;

    public JmsPublisher(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
        this.jmsTemplate.setPubSubDomain(true);
    }

    public void sendMessage(String topic, OutboxEntity entity) {
        jmsTemplate.convertAndSend(topic, entity.serializeEvent());
    }
}
