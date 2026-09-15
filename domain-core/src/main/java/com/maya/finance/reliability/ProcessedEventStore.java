package com.maya.finance.reliability;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class ProcessedEventStore {
    private final Set<String> processed = ConcurrentHashMap.newKeySet();
    public boolean isProcessed(String eventId) { return processed.contains(eventId); }
    public void markProcessed(String eventId) { processed.add(eventId); }
}
