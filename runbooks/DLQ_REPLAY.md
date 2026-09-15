# Runbook — DLQ Inspection and Controlled Replay

Status: local mechanism validated; GCP Pub/Sub procedure remains `NOT_GCP_VALIDATED`.

## Goal

Recover poison/failed events without creating duplicate business effects.

## Preconditions

- identify the failing subscription and DLQ;
- capture original event ID, type, aggregate ID/version, correlation ID and failure reason;
- determine whether the failure is transient, code/configuration related, or permanently invalid;
- fix the cause before replay;
- ensure the target consumer's idempotency state is understood.

## Procedure

1. Pause automated replay if one exists.
2. Inspect the DLQ message and consumer logs/traces.
3. Classify the failure.
4. For invalid business/schema payloads, do not mutate historical evidence silently. Create a corrected event/command according to governance and retain the link to the failed ID.
5. Replay to a controlled recovery topic/subscription or republish according to the final Pub/Sub implementation.
6. Verify one logical business effect.
7. Verify the event no longer re-enters DLQ.
8. Record timestamp, operator, original ID, replay ID (if changed), result and correlation ID in `evidence/replay/`.

## Safety checks

- replay is bounded and operator-visible;
- duplicate event handling must be active;
- stale aggregate versions must not overwrite newer state;
- never bulk-replay an unknown DLQ backlog without sampling/classification;
- never call a replay successful from a script alone—capture state before and after.
