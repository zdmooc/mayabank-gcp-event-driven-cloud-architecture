package com.maya.finance.events;

import java.time.Instant;
import java.util.Map;

public record EventEnvelope<T>(String specversion, String id, String type, String source, Instant time,
                               String subject, String datacontenttype, String correlationId, String causationId,
                               long aggregateVersion, T data, Map<String, String> extensions) {
    public EventEnvelope {
        if (!"1.0".equals(specversion)) throw new IllegalArgumentException("CloudEvents specversion must be 1.0");
        if (id == null || id.isBlank()) throw new IllegalArgumentException("event id is required");
        if (type == null || type.isBlank()) throw new IllegalArgumentException("event type is required");
        if (source == null || source.isBlank()) throw new IllegalArgumentException("event source is required");
        if (time == null) throw new IllegalArgumentException("event time is required");
        if (aggregateVersion < 0) throw new IllegalArgumentException("aggregate version cannot be negative");
        extensions = extensions == null ? Map.of() : Map.copyOf(extensions);
    }
}
