# Backlog — Maya Automotive Financial Services GCP EDA

Status vocabulary: `NOT_STARTED`, `DESIGNED`, `IMPLEMENTED`, `STATICALLY_VALIDATED`, `LOCALLY_VALIDATED`, `GCP_VALIDATED`, `MEASURED`, `BLOCKED`.

## Program roadmap

| Iteration | Scope | Status | Exit evidence |
|---|---|---|---|
| I0 | Offer, repository, public references, official sources, backlog | DESIGNED | Research/docs committed |
| I1 | Business domain, capabilities, bounded contexts, event map | DESIGNED | Domain architecture committed |
| I2 | HLD hardening, C4, ADR baseline | IN_PROGRESS | C1/C2/C3/deployment + ADR catalog |
| I3 | Customer, Vehicle, Contract core | NOT_STARTED | Code + unit/integration tests |
| I4 | Customer Matching | NOT_STARTED | Deterministic matching + explainable tests |
| I5 | Event backbone | NOT_STARTED | Pub/Sub abstraction + schemas + AsyncAPI |
| I6 | Relationship Hub | NOT_STARTED | Aggregated relationship API |
| I7 | Golden Moments / Renewal | NOT_STARTED | Deterministic rules + events |
| I8 | Recommendation / Residual Value | NOT_STARTED | Rules + synthetic calculation |
| I9 | Terraform | NOT_STARTED | Reusable modules + dev environment |
| I10 | Security | NOT_STARTED | IAM/WIF/Secret Manager design + tests |
| I11 | CI/CD | NOT_STARTED | GitHub Actions validation/build/deploy |
| I12 | GCP runtime | NOT_STARTED | Cloud Run/PubSub/data deployment evidence |
| I13 | Resilience | NOT_STARTED | Retry/DLQ/replay/idempotency evidence |
| I14 | Observability | NOT_STARTED | Logs/metrics/traces/dashboard evidence |
| I15 | Performance / FinOps | NOT_STARTED | k6 + measured cost/performance |
| I16 | Architecture Board | NOT_STARTED | HLD/LLD/ADR/NFR/risk package |
| I17 | Thomas Cook case study | NOT_STARTED | legacy-modernization document only |
| I18 | Portfolio | NOT_STARTED | recruiter README/interview/evidence pack |
| I19 | Final release | NOT_STARTED | clean CI, tag, final demo |

## I0 — baseline complete

- [x] Audit target repository and establish feature branch.
- [x] Analyze current CAT-AMANIA offer and correlated program advertisements.
- [x] Audit `gcparchitect` and reusable internal architecture POCs.
- [x] Benchmark relevant public Google/Terraform repositories.
- [x] Establish official Google source baseline.
- [x] Create requirements traceability and recruiter-oriented repository baseline.

## I1 — business architecture complete at design level

- [x] Business objective and actors/use cases.
- [x] Aggregate/domain model.
- [x] Capability map.
- [x] Bounded contexts/context map.
- [x] Domain-event map and initial event catalog.
- [x] Ubiquitous-language glossary.
- [x] Preliminary HLD/target architecture/NFR baseline.

## I2 — next architecture gates

- [ ] C4 C1 System Context.
- [ ] C4 C2 Container.
- [ ] C4 C3 key components.
- [ ] Deployment diagram.
- [ ] ADR-001 Pub/Sub vs Kafka.
- [ ] ADR-002 Cloud Run vs GKE.
- [ ] ADR-003 Eventarc role.
- [ ] ADR-004 data stores.
- [ ] ADR-005 API Gateway vs Apigee.
- [ ] ADR-006 idempotency.
- [ ] ADR-007 event versioning.
- [ ] Initial LLD resource catalog.

## Guardrails

- Do not provision paid GCP resources before budget/teardown controls.
- Do not create screenshots or evidence that did not come from execution.
- Keep `TARGET` separate from `MEASURED`.
- Do not identify or imply a real final client.
