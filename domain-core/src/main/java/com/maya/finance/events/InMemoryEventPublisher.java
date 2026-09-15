package com.maya.finance.events;

import java.util.ArrayList;
import java.util.List;

public final class InMemoryEventPublisher implements EventPublisher {
    private final List<EventEnvelope<?>> published = new ArrayList<>();
    @Override public void publish(EventEnvelope<?> event) { published.add(event); }
    public List<EventEnvelope<?>> publishedEvents() { return List.copyOf(published); }
}
