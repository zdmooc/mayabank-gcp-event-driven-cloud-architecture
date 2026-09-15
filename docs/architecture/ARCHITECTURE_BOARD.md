# Architecture Board Pack

Status date: 2026-09-15.

## Decision request

Approve the **POC architecture baseline** for a synthetic automotive financial-services relationship hub on GCP, subject to CI and GCP runtime validation. This is not a production-architecture approval.

## Business drivers

- consolidated customer/vehicle/contract relationship;
- explicit customer matching/data quality;
- mobile financial self-care;
- contextual renewal/Golden Moments and explainable recommendations;
- GCP microservices/event-driven/API architecture;
- HLD/LLD and documented technical arbitration.

## Proposed baseline

- Cloud Run for MVP application compute;
- Pub/Sub for the domain event backbone;
- Eventarc only for justified Google Cloud provider-event routing;
- Cloud SQL PostgreSQL for transactional state;
- BigQuery for analytics;
- API Gateway as economical POC gateway, Apigee as enterprise option;
- Terraform + GitHub Actions;
- GitHub OIDC/WIF, without a long-lived GCP key;
- OpenAPI/AsyncAPI contracts;
- structured logging and OpenTelemetry target;
- bounded retry, DLQ, replay and idempotency.

## Evidence available now

`LOCALLY_VALIDATED`:

- Customer/Vehicle/Contract domain invariants on Java 21;
- deterministic customer matching;
- Golden Moments/recommendation/residual-value rules;
- event envelope/publisher abstraction;
- duplicate/retry/DLQ/replay/stale-version reliability model.

`IMPLEMENTED but not CI/runtime validated`:

- Java 25/Spring Boot 4.1.1 composition service;
- Dockerfile;
- Terraform/WIF/IAM;
- GitHub Actions CI;
- structured HTTP correlation/logging;
- k6 test scenario.

`NOT_GCP_VALIDATED`:

- Cloud Run, Pub/Sub, Cloud SQL, BigQuery and API gateway runtime;
- WIF exchange and IAM negative tests;
- distributed tracing and dashboards;
- GCP performance/cost/HA/DR.

## NFR position

The public mission does not provide contractual traffic, availability, RTO or RPO. `NFR.md` therefore separates design hypotheses from measured values.

## Main open decisions before an enterprise target

1. actual data residency and landing region;
2. target RTO/RPO and regional DR topology;
3. datastore choice from real scale/access patterns;
4. API Gateway vs Apigee from enterprise API governance;
5. Pub/Sub topology/retention/order requirements from concrete event semantics;
6. private networking/VPC-SC/CMEK requirements;
7. deployment-unit split after measured scaling/ownership needs;
8. production observability/retention controls.

## Board recommendation

Proceed with a single-region, cost-controlled dev POC to gather runtime evidence. Add GKE, Kafka, Apigee, Dataflow or multi-region complexity only when an architecture driver or validation question requires it.
