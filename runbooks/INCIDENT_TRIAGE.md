# Runbook — Event/API Incident Triage

1. Start from user/request `correlationId`, event ID or aggregate ID.
2. Check Cloud Run request/error rate and revision health.
3. Inspect structured logs without dumping sensitive payloads.
4. Follow distributed trace when available.
5. For async flows, inspect subscription backlog, oldest unacked age and DLQ.
6. Classify failure: API validation, IAM, service error, database, transient dependency, poison event, stale version or duplicate.
7. Apply bounded recovery: retry only safe operations; use DLQ/replay runbook for poisoned messages.
8. Verify business state, not only HTTP 2xx/message acknowledgement.
9. Record incident timeline, root cause and evidence.
10. Convert recurring failure into test/alert/ADR improvement.
