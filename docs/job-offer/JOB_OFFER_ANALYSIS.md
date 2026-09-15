# Job Offer Analysis — CAT-AMANIA GCP / Event Driven

Last checked: **2026-09-15**.

## Primary public offer

CAT-AMANIA — **Architecte Technique (GCP / Event Driven)** — Paris — published 2026-09-09.

Freelance listing: 12 months renewable, 550–600 EUR/day, hybrid, >10 years experience.

Source: https://www.free-work.com/fr/tech-it/job-mission/architecte-technique/architecte-technique-gcp-event-driven-1

The public advertisement describes a program for a **digital financial relationship hub** intended to provide a complete customer experience. Explicit responsibilities include:

- solution-needs study;
- scalable/resilient cloud architecture design;
- target architecture plus customer matching/data quality;
- HLD and LLD production;
- architecture diagrams and technical arbitration;
- contract-data integration through APIs and information-system connections;
- recommendation-engine personalization and data exploitation;
- microservices, event-driven and API patterns.

The requested profile explicitly includes solution architecture, GCP cloud architecture, microservices/event-driven/API patterns, HLD/LLD, communication/writing and seniority.

## Correlated CAT-AMANIA advertisements

These advertisements are treated as **public program signals**, not as proof of a client identity.

| Date | Role | Strong common signals | Confidence it belongs to same/adjacent program |
|---|---|---|---|
| 2026-06-12 | Architecte Solutions cloud scalable / résiliente | contract visibility, Golden Moments, advanced personalization, customer journeys, financial relationship hub, HLD/LLD, microservices/event-driven/APIs, GCP Data Platform | HIGH |
| 2026-06-12 | Back-end Java 25 / Spring Boot 4 / GCP | same contract/Golden Moments/personalization/hub wording; REST, microservices, Kubernetes, GCP Data Platform | HIGH |
| 2026-06-12 | Data Engineer GCP BigQuery Dataflow | same contract/Golden Moments/personalization/hub wording; ingestion, pipelines, data quality | HIGH |
| 2026-08-27 | Data Engineer GCP BigQuery Dataflow | explicitly integrates financial services into two mobile applications; contract APIs/SI, self-care, recommendation, data quality, GCP, Terraform, Python/Airflow, BigQuery/dbt | HIGH |

Sources:

- https://www.free-work.com/fr/tech-it/architecte-cloud/job-mission/architecte-solutions-cloud-scalable-resiliente
- https://www.free-work.com/fr/tech-it/developpeur-java-kotlin-groovy-scala/job-mission/developpeur-back-end-java-25-springboot-4-gcp
- https://www.free-work.com/fr/tech-it/data-engineer/job-mission/data-engineer-gcp-bigquery-dataflow
- https://www.free-work.com/fr/tech-it/job-mission/data-engineer/data-engineer-gcp-big-query-dataflow

## Reconstructed program context

The most defensible reconstruction from public evidence is:

1. A digital hub centralizes financial relationship/contract visibility.
2. Contract data arrives from existing information systems through APIs/integration.
3. Customer identity/matching and data quality are architecture concerns.
4. Two mobile applications are a downstream experience channel in a related Data Engineer advertisement.
5. Contextual renewal moments (`Golden Moments`) trigger personalization/recommendations.
6. GCP is the cloud/data platform; BigQuery/Dataflow appear in Data roles.
7. Java 25/Spring Boot 4/Kubernetes appears in a correlated back-end role.
8. Terraform appears explicitly in the later Data Engineer role.

## What cannot be asserted

The public offer does **not** establish the final client. Similarities may suggest an automotive financial-services context, but this repository must not state that it is an architecture for Mobilize Financial Services, RCI Banque, Renault Group, Dacia, Alpine or another real organization.

Therefore the POC is named **Maya Automotive Financial Services** and all data are synthetic.

## Architecture implications for the POC

| Offer signal | Architecture response |
|---|---|
| Financial relationship hub | Customer Relationship bounded context and consolidated API |
| Contract integration | Contract service + legacy/external adapters + API/event contracts |
| Customer matching/data quality | Explainable deterministic matching service + quality metrics |
| Golden Moments/renewal | Event-driven renewal detection and business rules |
| Recommendation/personalization | Deterministic recommendation engine first; ML documented only as future option |
| Mobile apps | Brand-neutral Mobile BFF with `/me/*` APIs |
| GCP | Cloud Run, Pub/Sub, Cloud SQL, BigQuery, IAM/WIF, Cloud Operations |
| HLD/LLD | Versioned architecture package with C4/ADR/NFR/resource catalog |
| Scalable/resilient | Explicit NFRs, retry/DLQ/replay/idempotency, load/failure tests with evidence |
| Data platform | Event analytics to BigQuery; Dataflow only if justified by volume/cost |
| IaC | Terraform modules and disposable dev environment |

## Expected architect deliverables

- business/context analysis;
- HLD and LLD;
- target and transition architecture;
- C4/deployment diagrams;
- API and event contracts;
- ADRs and NFR catalog;
- data and integration design;
- security/IAM design;
- Terraform platform design;
- resilience/observability/FinOps design;
- evidence-backed technical arbitrations.
