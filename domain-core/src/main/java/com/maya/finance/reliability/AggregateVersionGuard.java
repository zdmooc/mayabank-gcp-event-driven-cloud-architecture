package com.maya.finance.reliability;

import java.util.concurrent.ConcurrentHashMap;

public final class AggregateVersionGuard {
    private final ConcurrentHashMap<String, Long> versions = new ConcurrentHashMap<>();

    public boolean accept(String aggregateId, long incomingVersion) {
        if (incomingVersion < 0) throw new IllegalArgumentException("version cannot be negative");
        return versions.compute(aggregateId,
                (key, current) -> current == null || incomingVersion > current ? incomingVersion : current) == incomingVersion;
    }

    public long current(String aggregateId) { return versions.getOrDefault(aggregateId, -1L); }
}
