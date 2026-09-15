# Observability Architecture

Status: `IMPLEMENTED_BASELINE / NOT_GCP_VALIDATED`.

## Signals

The target follows OpenTelemetry semantic conventions and Google Cloud Operations integration:

- logs: JSON structured output to stdout;
- metrics: API latency/error/throughput, JVM/runtime, Pub/Sub backlog/DLQ, business outcomes;
- traces: API -> domain service -> event publish -> consumer -> recommendation/relationship projection;
- audit: immutable event/business decision metadata where appropriate.

Spring Boot 4.1 supports JSON structured logging out of the box; the dev application enables ECS console output. The `CorrelationIdFilter` places `correlationId`, request duration and HTTP status into MDC, which Spring structured logging includes in JSON.

## Required log fields

- timestamp;
- service;
- environment;
- correlationId;
- eventId/eventType when processing events;
- aggregateId/version when applicable;
- durationMs;
- HTTP/status/result;
- retry attempt / DLQ reason when applicable.

No raw sensitive identity/contact payload should be written merely for troubleshooting.

## Target dashboards

1. API requests, p50/p95/p99 latency and 4xx/5xx.
2. Cloud Run instances/concurrency/startups.
3. Pub/Sub subscription backlog and oldest unacked message.
4. DLQ message count and replay activity.
5. Customer matching outcomes (`MATCHED`, `REVIEW_REQUIRED`, `NO_MATCH`).
6. Golden Moments detected by category.
7. Recommendations generated/viewed/accepted/rejected.
8. Cloud SQL connections/errors when enabled.
9. BigQuery ingestion failures.

## Trace correlation

HTTP requests carry `X-Correlation-Id` independently of tracing. On Cloud Run, the final runtime will also correlate logs with Cloud Trace by propagating trace context and exporting OpenTelemetry spans. Google Cloud Java telemetry is opt-in, so trace/metric instrumentation must be explicitly enabled rather than assumed.

## SLO/alert principle

Alerts will be derived from measured SLOs and error budgets, not arbitrary product defaults. Until load/runtime testing exists, dashboard/alert thresholds remain `DESIGNED`.

## Evidence required

To advance to `GCP_VALIDATED` capture:

- one end-to-end trace for Contract -> Matching -> Relationship -> Recommendation;
- correlated structured logs carrying one correlation ID/event ID;
- dashboard screenshots/exported JSON backed by real metrics;
- injected failure and recovery timeline;
- Pub/Sub backlog/DLQ signal during a controlled test.
