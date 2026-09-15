# ADR-008 — Customer Matching

Status: **Accepted for MVP**.

## Decision

Use explainable deterministic matching before introducing ML:

1. exact source-qualified identifiers/contact values;
2. normalized deterministic composite attributes;
3. transparent weighted score with reason codes;
4. outcomes `MATCHED`, `REVIEW_REQUIRED`, `NO_MATCH`.

## Why

The target mission explicitly raises customer matching/data quality. A deterministic baseline is testable, auditable and suitable as a control if ML is explored later.

## Consequences

Thresholds are synthetic configuration and must not be presented as real-client rules. False-positive/false-negative evaluation requires a synthetic test corpus before production-like claims.
