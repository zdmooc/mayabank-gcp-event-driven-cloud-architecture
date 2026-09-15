package com.maya.finance.events;

public interface EventPublisher {
    void publish(EventEnvelope<?> event);
}
