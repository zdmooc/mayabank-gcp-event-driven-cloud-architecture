# ADR-001 — Pub/Sub vs Kafka

Status: **Accepted for the POC**.

## Context

The target mission is explicitly GCP and event-driven. The portfolio already contains Kafka expertise, but this POC must demonstrate a GCP-native architecture rather than reproduce a Kafka lab.

## Decision

Use **Google Cloud Pub/Sub** as the MVP domain event backbone.

## Reasons

- managed GCP service with low operational burden;
- fits a disposable/low-ops POC;
- integrates naturally with Cloud Run and GCP IAM/observability;
- lets the project focus on event contracts, idempotency, DLQ/replay and business flows rather than broker operations.

## Kafka would be preferred when

- Kafka ecosystem/compatibility is an enterprise standard;
- long replay/retention semantics or log-centric streaming dominate;
- portability across clouds/on-prem is a hard requirement;
- existing producers/consumers depend on Kafka APIs/schema tooling;
- organizational operating model already provides Kafka as a platform.

## Consequences

Application consumers still handle duplicate/redelivery semantics. Ordering/exactly-once features are not assumed globally; they are selected only when required and validated against current Pub/Sub constraints.
