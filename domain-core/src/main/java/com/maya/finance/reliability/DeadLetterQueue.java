package com.maya.finance.reliability;

import java.util.ArrayDeque;
import java.util.Optional;

public final class DeadLetterQueue {
    public record Entry(String eventId, String reason, int attempts) {}
    private final ArrayDeque<Entry> entries = new ArrayDeque<>();
    public void add(Entry entry) { entries.addLast(entry); }
    public Optional<Entry> poll() { return Optional.ofNullable(entries.pollFirst()); }
    public int size() { return entries.size(); }
}
