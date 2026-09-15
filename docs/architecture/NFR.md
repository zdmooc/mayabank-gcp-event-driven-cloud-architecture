# Non-Functional Requirements

All numeric values below are **design hypotheses for the POC**, not contractual SLAs. `MEASURED` remains empty until a test produces evidence.

| NFR | TARGET / hypothesis | MEASURED | Validation method | Status |
|---|---|---|---|---|
| API availability | architecture capable of managed redundancy; initial POC objective to be refined before runtime | — | uptime/failure tests | DESIGNED |
| Read latency | p95 relationship API <= 800 ms under agreed lab load | — | k6 + trace | NOT_MEASURED |
| Command latency | p95 synchronous API <= 500 ms excluding async completion | — | k6 | NOT_MEASURED |
| Throughput | prove 100, 1k and, budget permitting, 10k request test runs | — | k6 | NOT_MEASURED |
| Event processing | observable backlog and bounded retry behavior | — | publish/load tests | NOT_MEASURED |
| Idempotency | duplicate same event creates one logical business effect | — | duplicate-event test | NOT_VALIDATED |
| Retry | transient consumer failure retries then succeeds without duplicate effect | — | fault injection | NOT_VALIDATED |
| DLQ | poison message reaches DLQ after configured attempts | — | invalid-event test | NOT_VALIDATED |
| Replay | operator can inspect/correct/replay quarantined event safely | — | runbook exercise | NOT_VALIDATED |
| Ordering | stale aggregate version cannot silently overwrite newer state | — | out-of-order test | NOT_VALIDATED |
| Scalability | Cloud Run/PubSub scaling behavior observed under load | — | load test + metrics | NOT_MEASURED |
| Recoverability | RTO/RPO to be derived from business hypothesis before I16 | — | restore/failure exercise | NOT_VALIDATED |
| Security | least privilege, WIF, authenticated services, secrets externalized | — | IAM negative tests + scans | NOT_VALIDATED |
| Observability | E2E correlation through API -> service -> event -> consumer | — | trace/log evidence | NOT_VALIDATED |
| Maintainability | contracts, ADRs, CI validation and modular Terraform | — | repo/CI quality gates | DESIGNED |
| Data quality | matching decision/review/no-match and invalid contract metrics visible | — | test datasets/queries | NOT_VALIDATED |
| Cost | lab has budget guardrail and destroy path; measured run cost captured | — | billing export/estimate | NOT_MEASURED |
| GreenOps | scale-to-zero/resource/log-retention/region choices documented | — | review + runtime metrics | DESIGNED |

## SLO design rule

No SLO is finalized because a public job advertisement does not supply business traffic, availability, RTO or RPO. I2/I16 must record assumptions explicitly and separate:

- business requirement;
- architecture target;
- test threshold;
- measured result.

## Failure scenarios reserved for validation

1. Customer matching unavailable.
2. Contract service unavailable.
3. Database timeout.
4. Duplicate event.
5. Invalid schema.
6. Pub/Sub retry.
7. DLQ routing.
8. IAM denied.
9. Recommendation service unavailable.
10. Cloud Run instance/revision interruption.

A scenario is not marked validated until evidence records inputs, commands, expected result, actual result and timestamp/context.
