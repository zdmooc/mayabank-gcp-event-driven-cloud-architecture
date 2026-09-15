package com.maya.finance.reliability;

public final class ReliabilityProcessor {
    @FunctionalInterface public interface Handler { void run() throws Exception; }
    public enum Status { SUCCESS, DUPLICATE_SKIPPED, DEAD_LETTERED }
    public record Result(Status status, int attempts) {}

    private final ProcessedEventStore processed;
    private final DeadLetterQueue dlq;

    public ReliabilityProcessor(ProcessedEventStore processed, DeadLetterQueue dlq) {
        this.processed = processed;
        this.dlq = dlq;
    }

    public Result process(String eventId, int maxAttempts, Handler handler) {
        if (processed.isProcessed(eventId)) return new Result(Status.DUPLICATE_SKIPPED, 0);
        Exception last = null;
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                handler.run();
                processed.markProcessed(eventId);
                return new Result(Status.SUCCESS, attempt);
            } catch (Exception e) {
                last = e;
            }
        }
        dlq.add(new DeadLetterQueue.Entry(eventId, last == null ? "unknown" : last.getMessage(), maxAttempts));
        return new Result(Status.DEAD_LETTERED, maxAttempts);
    }
}
