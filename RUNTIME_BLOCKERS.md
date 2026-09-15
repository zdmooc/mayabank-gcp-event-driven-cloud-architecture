# Runtime Blockers

Last updated: 2026-09-15.

The repository must continue to progress without inventing cloud evidence.

## External GCP blockers

No authenticated GCP project/billing context is available to the current execution session. Therefore these statuses remain unproven:

- Terraform `plan/apply` against GCP;
- Workload Identity Federation handshake;
- Artifact Registry push;
- Cloud Run deployment/invocation;
- Pub/Sub retry/DLQ/replay runtime;
- Cloud SQL connectivity;
- BigQuery ingestion/query evidence;
- IAM negative tests;
- Cloud Monitoring/Logging/Trace dashboards;
- measured GCP cost.

## Local tool constraints

The current execution environment has OpenJDK 21 but does not have Maven, Terraform or k6 installed. Pure-Java domain tests were executed locally; Java 25/Maven, Terraform and k6 are delegated to CI or a future equipped runtime and are not claimed as locally validated.

## Work that is not blocked

- business/domain architecture;
- HLD/LLD/C4/ADR/NFR;
- Java source and dependency-free rules tests;
- OpenAPI/AsyncAPI contracts;
- Dockerfile;
- Terraform source;
- WIF/IAM design;
- GitHub Actions workflows;
- resilience test harnesses;
- k6 scenarios;
- runbooks, evidence templates and interview/portfolio material.
