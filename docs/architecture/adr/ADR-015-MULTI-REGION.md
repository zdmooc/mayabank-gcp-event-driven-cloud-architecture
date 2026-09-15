# ADR-015 — Multi-region Strategy

Status: **Target architecture only**.

## MVP

Deploy one European region for cost-controlled validation. Do not claim regional DR from a single-region POC.

## Enterprise target

When business RTO/RPO requires regional resilience:

- deploy Cloud Run services to at least two regions;
- use global load balancing/serverless NEGs and service-health/failover capabilities;
- design Pub/Sub delivery/routing deliberately for multi-region consumers;
- use Cloud SQL regional HA for zonal failure plus a cross-region replica/DR procedure when required;
- test failover and failback rather than relying on diagrams.

Google Cloud documents that Cloud Run is region-scoped and multi-region service requires deployment/routing across regions. Cloud SQL cross-region replicas are asynchronous, so database DR can have non-zero RPO.

A real target must derive topology from business RTO/RPO, consistency, data residency, latency and cost—not from a generic two-region pattern.
