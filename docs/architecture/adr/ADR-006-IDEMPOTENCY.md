# ADR-006 — Business Idempotency

Status: **Accepted principle**.

## Decision

Every state-changing event consumer must be idempotent at the **business effect** level.

## Minimum metadata

- `eventId`;
- aggregate/business identifier;
- aggregate version where relevant;
- correlation/causation IDs.

## Candidate implementation

Persist processed event/business-effect keys in the same transactional boundary as the domain state change where practical, or implement an equivalent inbox/outbox pattern.

## Required proof

Publish the same `ContractUpdated` event twice. The evidence must show one logical state transition and a duplicate-detected outcome for the repeated event. No implementation is marked validated until this runtime test exists.
