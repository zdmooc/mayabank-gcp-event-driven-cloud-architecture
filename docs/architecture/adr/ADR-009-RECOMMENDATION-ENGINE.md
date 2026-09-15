# ADR-009 — Recommendation Engine

Status: **Accepted for MVP**.

Use deterministic business rules for the first Next Best Action engine. Recommendation output retains `type`, synthetic score and `reason`.

Examples:

- lease end approaching -> `VEHICLE_RENEWAL`;
- finance/loan end approaching -> `NEW_FINANCE_OFFER`;
- high synthetic mileage -> `LEASE_REVIEW`.

ML/GenAI is explicitly a future option only after data quality, explainability, evaluation and governance requirements exist. The POC does not add AI merely for portfolio breadth.
