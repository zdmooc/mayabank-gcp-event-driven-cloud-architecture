# Load Test Plan

Status: `IMPLEMENTED_SCRIPT / NOT_EXECUTED` because k6 is not available in the current local environment and the Java 25 service has not yet been runtime-validated.

Script: `tests/performance/relationship.js`.

## Profiles

```bash
# 100 requests
ITERATIONS=100 VUS=10 BASE_URL=http://localhost:8080/v1 k6 run tests/performance/relationship.js

# 1,000 requests
ITERATIONS=1000 VUS=20 BASE_URL=http://localhost:8080/v1 k6 run tests/performance/relationship.js

# 10,000 requests — only after the small profiles pass and budget/runtime permits
ITERATIONS=10000 VUS=50 BASE_URL=https://.../v1 k6 run tests/performance/relationship.js
```

## Metrics

- requests/s;
- error rate;
- p50/p95/p99 latency;
- Cloud Run instance count/concurrency;
- CPU/memory and billable instance time;
- Pub/Sub events/s/backlog once event adapters are live;
- downstream database behavior.

The script currently defines a design threshold of p95 < 800ms and error rate <1%. These are POC hypotheses from `NFR.md`, not measured SLA commitments.

## Stop conditions

Stop/escalate the test if error rate grows unexpectedly, billing guardrails are at risk, Cloud SQL saturates, Pub/Sub backlog grows without recovery, or the 100/1,000 profiles fail. Do not run 10,000 simply to produce a larger number.
