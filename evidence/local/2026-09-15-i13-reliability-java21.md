# I13 Reliability — Local Evidence

Date: 2026-09-15. Runtime: OpenJDK/Javac 21.0.11.

Observed result:

```text
RELIABILITY_SELF_TEST=PASS idempotency=PASS retryAttempts=3 dlqReplay=PASS staleVersion=REJECTED
```

Validated locally in dependency-free Java:

- duplicate event produces one logical business effect;
- transient handler failure succeeds on attempt 3;
- poison event reaches an in-memory DLQ after bounded attempts;
- corrected event can be replayed;
- aggregate version 2 is rejected after version 3 was accepted.

Status: `LOCALLY_VALIDATED` for the local reliability model.

Not proven: Pub/Sub subscription retry policy, Pub/Sub dead-letter topic permissions/routing, exactly-once behavior, persisted idempotency transaction, Cloud SQL failure recovery or Cloud Run restart behavior.
