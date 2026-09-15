# Job Requirements Traceability

Only evidence produced by this repository can advance a capability beyond `DESIGNED`.

| Requirement from public offer/program signal | POC capability | Planned implementation | Evidence | Status |
|---|---|---|---|---|
| Study the solution need | Business/domain architecture | Business domain + capabilities + use cases | `docs/domain/` | DESIGNED |
| Scalable/resilient GCP architecture | Target GCP architecture | Cloud Run/PubSub/data/security baseline + ADRs | `docs/architecture/` | DESIGNED |
| Customer matching / data quality | Customer Matching context | exact + deterministic + scoring rules, quality metrics | tests + `evidence/matching/` | NOT_STARTED |
| HLD / LLD | Architecture package | HLD now; detailed LLD in I2/I16 | `docs/architecture/` | DESIGNED |
| Architecture diagrams | C4 + Mermaid/PlantUML | C1/C2/C3/deployment | versioned diagrams | NOT_STARTED |
| Technical arbitrations | ADR catalog | PubSub/Kafka, CloudRun/GKE, data stores, API management, etc. | ADR files | NOT_STARTED |
| Contract-data integration via API/SI | Contract + Integration contexts | REST API, adapters, events | OpenAPI + E2E tests | NOT_STARTED |
| Recommendation/personalization | Recommendation context | deterministic rules first | tests + evidence | NOT_STARTED |
| Microservices | Domain services | Java/Spring Boot services on Cloud Run | build/runtime evidence | NOT_STARTED |
| Event-driven architecture | Event backbone | Pub/Sub + CloudEvents-compatible envelope | AsyncAPI + Pub/Sub evidence | NOT_STARTED |
| APIs | API-first contracts | OpenAPI, correlation/idempotency/error model | CI validation | NOT_STARTED |
| GCP expertise | Platform mapping | Cloud Run, Pub/Sub, Cloud SQL, BigQuery, IAM, WIF, Secret Manager, Operations | Terraform + GCP evidence | NOT_STARTED |
| Two mobile apps (correlated Data role) | Mobile Backend | brand-neutral shared BFF `/me/*` | API/E2E evidence | NOT_STARTED |
| Golden Moments (correlated June ads) | Golden Moment / Renewal | rule-based detectors and events | tests + event evidence | NOT_STARTED |
| BigQuery/Dataflow (correlated Data roles) | Analytics | BigQuery tables/queries; Dataflow only if justified | queries + runtime evidence | NOT_STARTED |
| Terraform (correlated Aug Data role) | Infrastructure as Code | modules + dev/preprod/prod layouts | fmt/validate/plan/apply evidence | NOT_STARTED |
| Java 25/Spring Boot 4 (correlated backend role) | Application stack candidate | compatibility pinned before build | CI/build output | NOT_STARTED |
| Senior communication/writing | Architecture Board package | concise recruiter README + interview defense + decision records | repository docs | DESIGNED |

## Status policy

Allowed operational states used by this project: `NOT_STARTED`, `DESIGNED`, `IMPLEMENTED`, `STATICALLY_VALIDATED`, `LOCALLY_VALIDATED`, `GCP_VALIDATED`, `MEASURED`, `BLOCKED`.

A document or script by itself is not runtime proof.
