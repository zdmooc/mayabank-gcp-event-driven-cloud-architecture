package com.maya.finance.reliability;

import java.util.concurrent.atomic.AtomicInteger;

public final class ReliabilitySelfTest {
    public static void main(String[] args) {
        var store = new ProcessedEventStore();
        var dlq = new DeadLetterQueue();
        var processor = new ReliabilityProcessor(store, dlq);

        var effects = new AtomicInteger();
        var first = processor.process("evt-dup", 3, effects::incrementAndGet);
        var duplicate = processor.process("evt-dup", 3, effects::incrementAndGet);
        check(first.status() == ReliabilityProcessor.Status.SUCCESS
                && duplicate.status() == ReliabilityProcessor.Status.DUPLICATE_SKIPPED
                && effects.get() == 1, "idempotency");

        var transientAttempts = new AtomicInteger();
        var transientResult = processor.process("evt-transient", 3, () -> {
            if (transientAttempts.incrementAndGet() < 3) throw new IllegalStateException("transient");
        });
        check(transientResult.status() == ReliabilityProcessor.Status.SUCCESS && transientResult.attempts() == 3, "retry");

        var poison = processor.process("evt-poison", 3, () -> { throw new IllegalArgumentException("invalid-schema"); });
        check(poison.status() == ReliabilityProcessor.Status.DEAD_LETTERED && dlq.size() == 1, "dlq");
        var entry = dlq.poll().orElseThrow();
        var replay = processor.process(entry.eventId(), 3, () -> {});
        check(replay.status() == ReliabilityProcessor.Status.SUCCESS && dlq.size() == 0, "replay");

        var versions = new AggregateVersionGuard();
        check(versions.accept("CON-1", 1), "v1 accepted");
        check(versions.accept("CON-1", 3), "v3 accepted");
        check(!versions.accept("CON-1", 2) && versions.current("CON-1") == 3, "stale rejected");

        System.out.println("RELIABILITY_SELF_TEST=PASS idempotency=PASS retryAttempts="
                + transientResult.attempts() + " dlqReplay=PASS staleVersion=REJECTED");
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }
}
