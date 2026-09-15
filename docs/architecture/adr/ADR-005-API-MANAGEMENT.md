# ADR-005 — API Gateway vs Apigee

Status: **Accepted for the POC**.

## Decision

Use Google **API Gateway** as the economical POC front door if a managed gateway is provisioned. Keep **Apigee X** as the enterprise target option when richer API product governance is required.

## Apigee drivers

- partner/API product lifecycle;
- sophisticated policy/governance needs;
- developer portal/consumer onboarding;
- advanced mediation/analytics/enterprise API management.

## POC drivers

The architecture must prove OpenAPI contracts, authentication, correlation and service boundaries without incurring enterprise gateway complexity/cost solely for demonstration.
