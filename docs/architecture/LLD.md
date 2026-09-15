# Low-Level Design — Initial Resource Catalog

Status: `DESIGNED`. Names are provisional until Terraform I9.

## Cloud Run logical services

| Service | Responsibility | Ingress | Main dependencies |
|---|---|---|---|
| `customer-service` | customer CRUD/references | internal/API Gateway | Cloud SQL, Pub/Sub |
| `customer-matching-service` | explainable matching | internal/API Gateway | Cloud SQL, Pub/Sub |
| `vehicle-service` | vehicle lifecycle | internal/API Gateway | Cloud SQL, Pub/Sub |
| `contract-service` | contracts and lifecycle | internal/API Gateway | Cloud SQL, Pub/Sub |
| `relationship-service` | consolidated read model | internal | Cloud SQL, Pub/Sub |
| `renewal-service` | Golden Moments/renewal | internal/event | Cloud SQL, Pub/Sub |
| `recommendation-service` | deterministic recommendations | internal/event | Cloud SQL, Pub/Sub |
| `residual-value-service` | synthetic calculation | internal/API/event | Cloud SQL, Pub/Sub |
| `mobile-bff` | `/me/*` channel view | API Gateway | relationship/recommendation |
| `audit-service` | event analytics ingestion | event only | Pub/Sub, BigQuery |

The implementation can merge low-value deployables during the MVP.

## Candidate topic families

- `customer-events-v1`
- `vehicle-events-v1`
- `contract-events-v1`
- `engagement-events-v1`
- `audit-events-v1`

Consumer-owned subscriptions and DLQs will be named from the consumer purpose, not the producer implementation name.

## Transactional schema ownership

Candidate PostgreSQL schemas:

- `customer`
- `vehicle`
- `contract`
- `matching`
- `relationship`
- `engagement`
- `platform` for technical idempotency/outbox data if needed.

Direct cross-schema access across bounded contexts is forbidden unless explicitly documented as an MVP consolidation and hidden behind repository interfaces.

## BigQuery dataset

Candidate dataset: `maya_finance_analytics`.

Tables/projections:

- `customers`
- `contracts`
- `vehicles`
- `events`
- `recommendations`
- `golden_moments`
- `residual_values`

## Timeouts / retry baseline

Values remain configuration hypotheses until load/failure testing.

- synchronous downstream calls: explicit connect/read timeouts, no infinite retry;
- HTTP retry only for classified transient/idempotent operations;
- Pub/Sub redelivery handled by consumer idempotency;
- poison messages routed to DLQ after bounded attempts;
- replay is operator-controlled and audited.

## Service accounts

Candidate separation:

- deployer CI service account via WIF;
- runtime service account per service or small trust group;
- dedicated analytics ingestion identity;
- no default broad Editor roles.

## Configuration

- environment variables for non-sensitive config;
- Secret Manager references for secrets;
- Terraform variables for environment-specific infrastructure;
- application configuration versioned without secrets.

## Environments

`dev` must be the first real deployment. `preprod` and `prod` are structural examples until explicitly provisioned.
