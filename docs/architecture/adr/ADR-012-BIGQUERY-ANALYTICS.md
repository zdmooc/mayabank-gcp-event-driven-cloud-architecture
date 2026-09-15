# ADR-012 — BigQuery Analytics

Status: **Accepted baseline**.

Use BigQuery for analytical/event-derived queries, not as the transactional source of truth.

Initial projections:

- customers;
- contracts;
- vehicles;
- events;
- recommendations;
- golden moments;
- residual values.

Target queries include contracts ending in <120 days, recommendation conversion, contracts by type and Golden Moments by category. Small POC volumes favor direct/light ingestion; Dataflow is target-only until throughput/transformation needs justify it.
