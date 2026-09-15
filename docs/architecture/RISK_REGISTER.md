# Risk Register

Scale: likelihood/impact `L/M/H`.

| ID | Risk | L | I | Treatment |
|---|---|---:|---:|---|
| R01 | Incorrect customer matching decision | M | H | explainable scoring, review state, synthetic quality test corpus |
| R02 | Duplicate event creates repeated business effect | H | H | transactional idempotency strategy and duplicate test |
| R03 | Out-of-order event replaces newer state | M | H | aggregate version and stale-event policy |
| R04 | Permanently invalid event retries forever | M | M | bounded retry, DLQ, replay runbook and alert |
| R05 | Relationship view is temporarily stale | M | M | explicit eventual consistency and lag metric |
| R06 | Too many deployables slow delivery | H | M | bounded contexts do not automatically become separate services |
| R07 | Cloud SQL increases lab cost | M | M | disabled by default, bounded test window, teardown |
| R08 | CI/runtime identity receives excessive permissions | M | H | separate identities, WIF conditions, reduce permissions after bootstrap |
| R09 | Logs/events expose unnecessary identity data | M | H | minimization, structured fields, no payload dumping |
| R10 | Public-offer correlation is presented as client fact | M | H | synthetic naming and explicit disclaimer |
| R11 | Multi-region diagram is mistaken for validated HA | M | H | target-only ADR and real failover test before validation |
| R12 | Load-test result lacks environment context | M | M | capture commit, config, volume, metrics and cost with each result |
| R13 | CI succeeds but GCP-specific behavior differs | M | H | staged dev deployment and GCP negative/failure tests |
| R14 | Event schema evolution breaks consumers | M | H | version policy and contract validation |
