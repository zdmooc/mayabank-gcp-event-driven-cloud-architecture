# ADR-002 — Cloud Run vs GKE

Status: **Accepted for the POC**.

## Decision

Use **Cloud Run** as the default MVP compute platform.

## Rationale

The POC needs containerized Java services, simple deployment, low administration and cost-sensitive runtime. Cloud Run is a managed/serverless container platform and is suitable for proving application/event architecture without first operating a Kubernetes platform.

## GKE becomes preferable if requirements demand

- strong cluster/platform control;
- service mesh as a central platform capability;
- complex networking/policy/scheduling;
- long-running or specialized workload behavior unsuitable for Cloud Run;
- sidecars/daemon-style platform components at scale;
- standardization on Kubernetes as an enterprise runtime.

## Consequences

Services should remain portable containers where practical. This POC does not use Cloud Run-specific behavior as business logic. A future GKE deployment is a transition option, not a current validation claim.
