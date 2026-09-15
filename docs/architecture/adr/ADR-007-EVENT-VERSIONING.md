# ADR-007 — Event Versioning and Evolution

Status: **Accepted principle**.

## Decision

Use stable semantic event types with an explicit major contract version, for example:

`maya.finance.contract.updated.v1`

Compatibility rules:

1. additive optional fields are preferred within a major version;
2. consumers ignore unknown optional fields;
3. incompatible semantic/schema changes create a new major event type;
4. schema validation is automated in CI;
5. producers and consumers have explicit ownership documented in AsyncAPI/event governance;
6. old versions have an intentional migration/deprecation window.

## Ordering/state evolution

`aggregateVersion` allows consumers to detect stale/out-of-order updates. The business policy for reject/defer/quarantine/replay is consumer-specific and must be tested.
