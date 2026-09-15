# ADR-013 — Retry, DLQ and Replay

Status: **Accepted; local model validated; Pub/Sub runtime not validated**.

## Decision

- classify errors before retry;
- bounded retry for transient failures;
- poison/permanent failures go to DLQ;
- replay is controlled and audited;
- business idempotency remains mandatory;
- stale aggregate versions cannot silently overwrite newer state.

Local evidence: `evidence/local/2026-09-15-i13-reliability-java21.md`.

GCP evidence must demonstrate actual Pub/Sub retry/dead-letter configuration and permissions, not infer them from the Java model.
