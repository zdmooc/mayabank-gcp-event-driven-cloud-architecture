# ADR-014 — Observability

Status: **Accepted baseline / not GCP validated**.

Use:

- Spring structured JSON logging to stdout;
- correlation IDs at HTTP/event boundaries;
- OpenTelemetry semantic conventions for traces/metrics;
- Cloud Logging, Monitoring and Trace as the GCP observability backend;
- business metrics alongside technical golden signals.

Google Cloud Java telemetry is opt-in, so client-library traces/metrics must be explicitly enabled. No distributed-trace claim is made until a real exported trace exists.
