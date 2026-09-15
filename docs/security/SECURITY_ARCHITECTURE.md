# Security Architecture

Status: `DESIGNED / IMPLEMENTED IN IAC WHERE NOTED / NOT_GCP_VALIDATED`.

## Security objectives

1. No long-lived GCP service-account key in GitHub.
2. Separate deployment identity from runtime identities.
3. Least privilege by workload and data capability.
4. Authenticated service-to-service calls by default.
5. Secrets outside application source/configuration.
6. Traceable API/event actions through correlation and audit metadata.
7. Synthetic data only in the POC.

## GitHub to GCP

```text
GitHub Actions
     |
     | OIDC token
     v
Workload Identity Federation
     |
     v
Dedicated deployer service account
     |
     v
Terraform / Artifact Registry / Cloud Run / PubSub / data resources
```

`terraform/bootstrap/` implements the trust baseline with a repository attribute condition. A real deployment must further bind deployment privileges to the intended protected branch/GitHub Environment.

The current `google-github-actions/auth` latest major release checked on 2026-09-15 is v3.

## Runtime identity

The dev Terraform root creates separate logical service accounts:

- `maya-finance-runtime`: domain API/event workload;
- `maya-audit-runtime`: event analytics ingestion.

The MVP uses project-level roles for simplicity. Hardening should move permissions to resource-level IAM where the GCP service supports it, especially Pub/Sub topics/subscriptions, BigQuery datasets and Secret Manager secrets.

## Secrets

Secret Manager resources contain secret metadata only in Terraform. Secret values must be injected outside Git and must never be placed in `*.tfvars`, workflow YAML, README examples or evidence.

## Cloud Run

Target controls:

- dedicated service account;
- no anonymous `allUsers` invoker binding by default;
- authenticated internal/service calls;
- resource bounds and max instances;
- immutable image tag/digest for evidence runs;
- graceful shutdown;
- health/telemetry endpoints separated from business authorization as needed.

## APIs

The OpenAPI baseline declares bearer authentication. Final gateway/runtime configuration must also implement:

- authentication and authorization;
- correlation ID propagation;
- idempotency key on state-changing public commands where useful;
- bounded payloads and validation;
- RFC 9457/Problem Details-style errors;
- rate/quota policy at the chosen gateway;
- no sensitive data in error messages/logs.

## Pub/Sub

- producer and consumer identities are explicit;
- consumers assume redelivery and remain idempotent;
- DLQ/replay permissions are operational privileges, not broad runtime defaults;
- messages minimize identity/contact data;
- event contract evolution is governed through AsyncAPI/schema checks.

## Data

- Cloud SQL: relational transactional state; current lab module is low-cost/public-IP oriented and **not a production network design**;
- BigQuery: analytical projection; separate ingestion identity;
- production target would evaluate private connectivity, VPC Service Controls, CMEK, backup/PITR, retention and regional/data-residency requirements from actual NFRs.

## Supply chain

I11 target gates:

- Maven build/tests;
- contract checks;
- Terraform fmt/init/validate;
- dependency/container scanning;
- immutable image publication;
- later signing/attestation policy if runtime evidence warrants it.

## Negative tests required before `GCP_VALIDATED`

- unauthorized Cloud Run invocation denied;
- runtime identity cannot administer infrastructure;
- service without Pub/Sub role cannot publish/consume;
- service without Secret Accessor cannot read secret payload;
- GitHub identity from an untrusted repository/ref cannot federate;
- no static JSON key exists in repository/history/evidence pack.

## Current limitations

No authenticated GCP project is connected in this session. IAM, WIF, Cloud Run auth and negative tests therefore remain `NOT_GCP_VALIDATED`.
