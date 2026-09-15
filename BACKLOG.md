# Backlog — Maya Automotive Financial Services GCP EDA

Status vocabulary: `NOT_STARTED`, `DESIGNED`, `IMPLEMENTED`, `STATICALLY_VALIDATED`, `LOCALLY_VALIDATED`, `GCP_VALIDATED`, `MEASURED`, `BLOCKED`.

## Program roadmap

| Iteration | Scope | Status | Exit evidence |
|---|---|---|---|
| I0 | Offer, repository, public references, official sources, backlog | DESIGNED | Research/docs committed |
| I1 | Business domain, capabilities, bounded contexts, event map | IN_PROGRESS | Domain architecture committed and reviewed |
| I2 | HLD, C4, ADR baseline | NOT_STARTED | C1/C2/C3/deployment + ADR catalog |
| I3 | Customer, Vehicle, Contract core | NOT_STARTED | Code + unit/integration tests |
| I4 | Customer Matching | NOT_STARTED | Deterministic matching + explainable tests |
| I5 | Event backbone | NOT_STARTED | Pub/Sub abstraction + schemas + AsyncAPI |
| I6 | Relationship Hub | NOT_STARTED | Aggregated customer relationship API |
| I7 | Golden Moments / Renewal | NOT_STARTED | Deterministic rules + events |
| I8 | Recommendation / Residual Value | NOT_STARTED | Rules engine + synthetic calculation |
| I9 | Terraform | NOT_STARTED | Reusable modules + dev environment |
| I10 | Security | NOT_STARTED | IAM/WIF/Secret Manager design + tests |
| I11 | CI/CD | NOT_STARTED | GitHub Actions validation/build/deploy workflow |
| I12 | GCP runtime | NOT_STARTED | Real Cloud Run/Pub/Sub/data deployment evidence |
| I13 | Resilience | NOT_STARTED | Retry/DLQ/replay/idempotency evidence |
| I14 | Observability | NOT_STARTED | Logs/metrics/traces/dashboard evidence |
| I15 | Performance / FinOps | NOT_STARTED | k6 + measured cost/performance |
| I16 | Architecture Board | NOT_STARTED | HLD/LLD/ADR/NFR/risk package |
| I17 | Thomas Cook case study | NOT_STARTED | Legacy-modernization document only |
| I18 | Portfolio | NOT_STARTED | Recruiter README/interview/evidence pack |
| I19 | Final release | NOT_STARTED | Clean CI, release/tag, final demo |

## I0 — completed baseline

- [x] Confirm target repository baseline and create feature branch.
- [x] Reconstruct the public CAT-AMANIA job requirement.
- [x] Correlate prior architect, Java and Data Engineer advertisements without asserting a client identity.
- [x] Audit `zdmooc/gcparchitect` as a knowledge source.
- [x] Audit reusable architecture disciplines from IBM MQ, Kafka/DDD, Azure, Wero and AI architecture repositories.
- [x] Benchmark Bank of Anthos, Online Boutique, Cloud Run samples, Eventarc samples and Terraform Google modules.
- [x] Establish official Google documentation baseline.
- [x] Create job requirement traceability.

## I1 — current work

- [x] Define business objective: Digital Financial Relationship Hub.
- [x] Define core aggregates and bounded contexts.
- [x] Define first domain-event map.
- [x] Establish HLD/target architecture/NFR baseline.
- [ ] Add formal capability map and actor/use-case view.
- [ ] Add ubiquitous-language glossary.
- [ ] Review context boundaries against the first OpenAPI/AsyncAPI contracts.

## Next technical gates

1. I2: write ADR-001 Pub/Sub vs Kafka and ADR-002 Cloud Run vs GKE first.
2. Create C4 diagrams and LLD resource catalog.
3. I3: implement the smallest executable vertical slice: Customer + Contract + PostgreSQL.
4. I4/I5: add matching and Pub/Sub with CloudEvents-compatible envelope.
5. Do not provision paid GCP resources until Terraform budget/teardown controls exist.
6. Keep `TARGET` separate from `MEASURED`; never create synthetic runtime evidence.
