# Event Governance

Status: `DESIGNED`; envelope/publisher core locally validated on Java 21. Pub/Sub runtime remains unvalidated.

## Naming

Integration event type: `maya.finance.<domain>.<fact>.v<major>`.

Examples:

- `maya.finance.contract.created.v1`
- `maya.finance.customer.matched.v1`
- `maya.finance.recommendation.generated.v1`

Pub/Sub topic families are broader domain channels (`contract-events-v1`, etc.); event type remains in the envelope.

## Ownership

The bounded context that owns the fact owns its event contract. Consumers cannot redefine producer semantics.

## Envelope

CloudEvents-compatible baseline:

- `specversion=1.0`
- globally unique `id`
- semantic `type`
- `source`
- event `time`
- `subject`
- `datacontenttype`
- `correlationId`
- optional `causationId`
- `aggregateVersion`
- `data`

## Versioning / evolution

- additive optional fields preferred inside v1;
- incompatible changes create a new major event type;
- consumers tolerate unknown optional fields;
- deprecation windows are explicit;
- AsyncAPI/schema validation is a CI gate.

## Delivery semantics

The application assumes redelivery is possible. Business idempotency is mandatory even when a Pub/Sub feature can reduce duplicate delivery.

## Ordering

Do not assume global ordering. Use aggregate/version metadata and ordering keys only when the use case requires them. Consumers must detect stale state and choose reject/defer/quarantine/replay deliberately.

## Retry / DLQ

- transient failures: bounded retry/redelivery;
- permanent validation/poison failures: DLQ;
- DLQ inspection retains original event ID and failure reason;
- replay is controlled, observable and idempotent.

## Retention / replay

Retention values are an infrastructure/runtime decision driven by recovery and cost requirements. The POC must not claim arbitrary long-term replay semantics without configuration evidence.

## PII classification

The POC uses synthetic data only. Even synthetic event contracts are designed to minimize PII and avoid putting unnecessary identity/contact fields in broad integration events.

## Producer/consumer registry baseline

| Event | Producer | Consumers |
|---|---|---|
| ContractCreated | Contract | Customer Matching, Relationship, Audit |
| CustomerMatched | Customer Matching | Relationship, Audit |
| ContractEndApproaching | Contract/Renewal | Renewal, Audit |
| GoldenMomentDetected | Renewal | Recommendation, Audit |
| RecommendationGenerated | Recommendation | Relationship/Mobile, Audit |
| ResidualValueCalculated | Residual Value | Recommendation, Audit |
