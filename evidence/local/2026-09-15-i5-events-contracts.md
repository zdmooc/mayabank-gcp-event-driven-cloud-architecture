# I5 Events and Contracts — Local Evidence

Date: 2026-09-15.

## Java envelope/publisher

Compiled with OpenJDK/Javac 21.0.11 and executed:

```text
EVENT_ENVELOPE_SELF_TEST=PASS
```

This validates the dependency-free event-envelope invariants and in-memory publisher abstraction only.

## YAML syntax

`openapi/openapi.yaml` and `asyncapi/asyncapi.yaml` were parsed using PyYAML 6.0.3.

Observed:

```text
openapi/openapi.yaml YAML_PARSE=PASS version=3.1.0
asyncapi/asyncapi.yaml YAML_PARSE=PASS version=3.0.0
```

This is **YAML syntax parsing**, not full OpenAPI/AsyncAPI semantic validation. CI validators remain to be added.

Not validated: Google Pub/Sub topics/subscriptions, schema registry behavior, Eventarc, Cloud Run delivery, retries, DLQ, ordering or GCP IAM.
