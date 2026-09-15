package com.maya.finance.events;

import java.time.Instant;
import java.util.Map;

public final class EventEnvelopeSelfTest {
    public static void main(String[] args) {
        var event = new EventEnvelope<>("1.0", "evt-123", "maya.finance.contract.updated.v1",
                "/contract-service", Instant.parse("2026-09-15T10:00:00Z"), "contract/CON-100",
                "application/json", "corr-456", "evt-122", 7,
                Map.of("contractId", "CON-100"), Map.of("schema", "contract-updated-v1"));
        var publisher = new InMemoryEventPublisher();
        publisher.publish(event);
        if (publisher.publishedEvents().size() != 1) throw new AssertionError("event not published");
        if (!publisher.publishedEvents().getFirst().type().endsWith(".v1")) throw new AssertionError("version missing");
        System.out.println("EVENT_ENVELOPE_SELF_TEST=PASS");
    }
}
