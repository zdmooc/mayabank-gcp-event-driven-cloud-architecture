# Official and Public Source Baseline

Last checked: **2026-09-15**. Versions and product behavior must be rechecked before implementation/deployment.

## Public job/program evidence

- CAT-AMANIA Architecte Technique GCP/Event Driven, 2026-09-09: https://www.free-work.com/fr/tech-it/job-mission/architecte-technique/architecte-technique-gcp-event-driven-1
- CAT-AMANIA Architecte Solutions cloud scalable/résiliente, 2026-06-12: https://www.free-work.com/fr/tech-it/architecte-cloud/job-mission/architecte-solutions-cloud-scalable-resiliente
- CAT-AMANIA Java 25/Spring Boot 4/GCP, 2026-06-12: https://www.free-work.com/fr/tech-it/developpeur-java-kotlin-groovy-scala/job-mission/developpeur-back-end-java-25-springboot-4-gcp
- CAT-AMANIA Data Engineer GCP BigQuery/Dataflow, 2026-06-12: https://www.free-work.com/fr/tech-it/data-engineer/job-mission/data-engineer-gcp-bigquery-dataflow
- CAT-AMANIA Data Engineer GCP BigQuery/Dataflow, 2026-08-27: https://www.free-work.com/fr/tech-it/job-mission/data-engineer/data-engineer-gcp-big-query-dataflow

## Google Cloud — compute/event/security

- Cloud Run docs: https://cloud.google.com/run/docs
- Pub/Sub subscription semantics: https://cloud.google.com/pubsub/docs/subscription-overview
- Pub/Sub exactly-once delivery: https://cloud.google.com/pubsub/docs/exactly-once-delivery
- Pub/Sub dead-letter topics: https://cloud.google.com/pubsub/docs/dead-letter-topics
- Eventarc overview: https://cloud.google.com/eventarc/docs/overview
- Workload Identity Federation: https://cloud.google.com/iam/docs/workload-identity-federation
- WIF for deployment pipelines/GitHub: https://cloud.google.com/iam/docs/workload-identity-federation-with-deployment-pipelines
- Secret Manager docs: https://cloud.google.com/secret-manager/docs

## Google Cloud — data/operations

- Cloud SQL PostgreSQL overview: https://cloud.google.com/sql/docs/postgres/introduction
- BigQuery docs: https://cloud.google.com/bigquery/docs
- Cloud Monitoring docs: https://cloud.google.com/monitoring/docs
- Cloud Logging docs: https://cloud.google.com/logging/docs
- Cloud Trace docs: https://cloud.google.com/trace/docs

## Architecture implications confirmed by current docs

- Cloud Run is a fully managed/serverless application platform for containerized services/jobs/workers; it is a reasonable low-ops MVP candidate.
- Pub/Sub defaults to at-least-once delivery and no ordering guarantee; idempotency remains a first-class application concern. Ordering and exactly-once have explicit constraints and must be chosen deliberately.
- Eventarc is an event-routing service and should not automatically replace the domain Pub/Sub backbone.
- Workload Identity Federation supports GitHub/OIDC-style external workload identity and avoids long-lived service-account keys.
- Cloud SQL is a managed relational database candidate for PostgreSQL transactional state.

## Public implementation references

- https://github.com/GoogleCloudPlatform/bank-of-anthos
- https://github.com/GoogleCloudPlatform/microservices-demo
- https://github.com/GoogleCloudPlatform/cloud-run-samples
- https://github.com/GoogleCloudPlatform/eventarc-samples
- https://github.com/GoogleCloudPlatform/java-docs-samples
- https://github.com/terraform-google-modules/terraform-google-project-factory
- https://github.com/terraform-google-modules/terraform-google-pubsub

## Source governance

Official product documentation has priority over blog posts and old examples for supported behavior, versions and security. Public GitHub repositories are implementation references, not authority for the target architecture. Job-ad correlations are evidence of market/program requirements, not proof of a client identity.
