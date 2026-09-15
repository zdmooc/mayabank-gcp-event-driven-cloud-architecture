# Transition Architecture

## T0 — Design and local domain proof

Current: domain, APIs/events, ADR/NFR, Java rules and local reliability evidence.

## T1 — CI-clean executable

- Java 25/Spring Boot build passes;
- Terraform fmt/validate passes;
- OpenAPI/AsyncAPI checks pass;
- container build/scanning is added.

## T2 — Minimal GCP vertical slice

- WIF established;
- Artifact Registry image;
- Cloud Run financial-hub composition service;
- Pub/Sub contract/customer event flow;
- Cloud SQL enabled for a bounded test where required;
- structured logs/correlation captured.

## T3 — Reliability/data evidence

- customer matching integrated with persistence/events;
- duplicate/retry/DLQ/replay on real Pub/Sub;
- BigQuery analytical projection;
- IAM negative tests;
- end-to-end trace.

## T4 — Architecture hardening

- load/cost measurements;
- API management decision validated;
- private networking/data protection decisions from real NFRs;
- multi-region/DR only if business RTO/RPO warrants it.

Complexity is added only when justified by a requirement or a validation question.
