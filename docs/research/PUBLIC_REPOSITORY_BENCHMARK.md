# Public Repository Benchmark

Checked 2026-09-15. Goal: reuse public patterns intellectually, not copy projects wholesale.

## 1. GoogleCloudPlatform/bank-of-anthos

https://github.com/GoogleCloudPlatform/bank-of-anthos

Useful signals:

- realistic synthetic banking domain;
- service decomposition with Java/Python/PostgreSQL;
- load generator and operational documentation;
- Cloud SQL, Workload Identity and CI/CD deployment options.

Limit for this POC: Bank of Anthos is primarily GKE/HTTP-oriented and models payment/account flows, not the target automotive-finance relationship hub or Pub/Sub-first event backbone.

Reuse: documentation quality, synthetic-domain discipline, service/evidence mindset. Do not clone its architecture.

## 2. GoogleCloudPlatform/microservices-demo (Online Boutique)

https://github.com/GoogleCloudPlatform/microservices-demo

Useful signals:

- explicit microservice catalog and architecture diagram;
- multi-service interaction and recommendation service;
- load generation;
- Terraform/deployment/observability variants.

Limit: e-commerce domain and predominantly GKE/gRPC architecture.

Reuse: service catalog readability, load-test/demo mechanics, telemetry/deployment patterns where applicable.

## 3. GoogleCloudPlatform/cloud-run-samples

https://github.com/GoogleCloudPlatform/cloud-run-samples

Useful for small, canonical Cloud Run patterns, including Java services, structured logging and Pub/Sub push handling. Use samples to validate implementation mechanics, not to define the business architecture.

## 4. GoogleCloudPlatform/eventarc-samples

https://github.com/GoogleCloudPlatform/eventarc-samples

Useful for understanding Eventarc-to-Cloud-Run/Workflows patterns and the distinction between event routing and the domain messaging backbone. Eventarc is optional in this POC and must be justified by an ADR.

## 5. GoogleCloudPlatform/java-docs-samples

https://github.com/GoogleCloudPlatform/java-docs-samples

Useful implementation reference for Java client usage across Pub/Sub, Eventarc, Cloud Run, IAM, Secret Manager and monitoring. Production versions will be pinned from current official support matrices at implementation time.

## 6. terraform-google-modules

https://github.com/terraform-google-modules

Candidate modules and patterns include project factory and Pub/Sub modules. The POC should prefer explicit, reviewable Terraform modules and pin versions; it must not inherit broad IAM permissions blindly from examples.

## Benchmark conclusion

The target repository must combine strengths that public samples usually separate:

`business architecture + DDD + APIs + event governance + Cloud Run + Pub/Sub + data + IAM/WIF + Terraform + resilience evidence + recruiter-readable architecture package`.

No public sample is treated as the target architecture.
