# ADR-004 — Data stores

Status: **Accepted baseline**.

## Decision

- Cloud SQL PostgreSQL for transactional relational domain state.
- BigQuery for analytics/event-derived reporting.
- Firestore only if a later access pattern creates a clear document/key-value requirement.
- Cloud Storage only for justified bulk/raw/object use cases.

## Rationale

Customer/vehicle/contract relationships and transactional consistency fit a relational MVP. Analytical queries must be isolated from transactional ownership. Avoid introducing several databases merely to demonstrate product knowledge.

## Consequences

Each bounded context owns its data model even if one Cloud SQL instance is shared by the economical dev POC. Production isolation/sizing is a later architecture decision.
