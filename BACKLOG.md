# Backlog — Maya Automotive Financial Services GCP EDA

Status vocabulary: `NOT_STARTED`, `DESIGNED`, `IMPLEMENTED`, `STATICALLY_VALIDATED`, `LOCALLY_VALIDATED`, `GCP_VALIDATED`, `MEASURED`, `BLOCKED`.

## Program roadmap

| Iteration | Scope | Status | Exit evidence |
|---|---|---|---|
| I0 | Offer/repository/public/official research | DESIGNED | committed research |
| I1 | Business architecture | DESIGNED | domain/capability/context/event docs |
| I2 | HLD/C4/ADR/LLD baseline | DESIGNED | C4 + ADR-001..007 + LLD |
| I3 | Customer, Vehicle, Contract core | IN_PROGRESS | code + unit/integration tests |
| I4 | Customer Matching | NOT_STARTED | deterministic matching tests |
| I5 | Event backbone | NOT_STARTED | Pub/Sub abstraction + schemas + AsyncAPI |
| I6 | Relationship Hub | NOT_STARTED | relationship API |
| I7 | Golden Moments / Renewal | NOT_STARTED | rules + events |
| I8 | Recommendation / Residual Value | NOT_STARTED | rules + synthetic calc |
| I9 | Terraform | NOT_STARTED | modules + dev env |
| I10 | Security | NOT_STARTED | IAM/WIF/secrets |
| I11 | CI/CD | NOT_STARTED | GitHub Actions |
| I12 | GCP runtime | NOT_STARTED | real deployment evidence |
| I13 | Resilience | NOT_STARTED | retry/DLQ/replay/idempotency evidence |
| I14 | Observability | NOT_STARTED | logs/metrics/traces/dashboard |
| I15 | Performance/FinOps | NOT_STARTED | measured load/cost |
| I16 | Architecture Board | NOT_STARTED | final HLD/LLD/ADR/NFR/risk |
| I17 | Thomas Cook case | NOT_STARTED | doc only |
| I18 | Portfolio | NOT_STARTED | recruiter/interview/evidence |
| I19 | Final release | NOT_STARTED | CI/tag/demo |

## Completed design gates

- [x] I0 offer/program/public/internal research.
- [x] I1 business domain, capability map, bounded contexts, event map, glossary.
- [x] C4 C1/C2/C3 and deployment view.
- [x] Initial LLD resource catalog.
- [x] ADR-001 Pub/Sub vs Kafka.
- [x] ADR-002 Cloud Run vs GKE.
- [x] ADR-003 Eventarc role.
- [x] ADR-004 data stores.
- [x] ADR-005 API Gateway vs Apigee.
- [x] ADR-006 idempotency principle.
- [x] ADR-007 event versioning.

## I3 immediate slice

- [ ] Pin compatible Java/Spring Boot baseline from official sources.
- [ ] Create Maven multi-module or deliberately small service structure.
- [ ] Implement Customer aggregate/API.
- [ ] Implement Vehicle aggregate/API.
- [ ] Implement Contract aggregate/API.
- [ ] Add validation/error/correlation model.
- [ ] Add PostgreSQL persistence strategy and tests.

## Guardrails

No cloud/runtime claim without evidence. No real-client identity. No paid GCP provisioning before budget/teardown controls.
