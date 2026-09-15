# FinOps Cost Model

Status: `DESIGNED / CURRENT_PUBLIC_PRICES_CHECKED_2026-09-15 / NOT_MEASURED_ON_ACCOUNT`.

All figures below are public list-price references checked 2026-09-15 and are not a bill forecast. Real costs depend on region, billing account currency/SKUs, free-tier consumption shared by the account, traffic, storage, retention and discounts.

## Current public anchors

| Service | Public pricing anchor checked 2026-09-15 | POC implication |
|---|---|---|
| Cloud Run request-based services | free tier includes 180,000 vCPU-s, 360,000 GiB-s and 2M requests/month; Tier-1 active list price shown by Google includes $0.000024/vCPU-s, $0.0000025/GiB-s and $0.40/M requests | keep min instances = 0; small demo traffic may fit free tier |
| Pub/Sub | first 10 GiB/month of Message Delivery Basic throughput free per billing account, then $40/TiB | use small synthetic events and short retention |
| BigQuery on-demand | first 1 TiB query data/month free, then $6.25/TiB for listed standard regions including EU | partition/select only needed columns; demo should remain tiny |
| Cloud SQL shared-core | public table lists `db-f1-micro` at $0.0105/hour plus storage/network and other applicable charges | unlike scale-to-zero Cloud Run, running DB time accumulates cost; disabled by default |

Sources:

- https://cloud.google.com/run/pricing
- https://cloud.google.com/pubsub/pricing
- https://cloud.google.com/bigquery/pricing
- https://cloud.google.com/sql/pricing

## Lab strategy

Default Terraform policy:

```text
Cloud Run min instances       0
Cloud SQL                     disabled
GKE                           not provisioned
Apigee                        not provisioned
Dataflow                      not provisioned
Budget resource               optional, target 25 EUR
Destroy after evidence        mandatory
```

Cloud SQL is enabled only for a bounded test window. At the displayed $0.0105/hour compute anchor, four hours of shared-core runtime is roughly $0.042 before storage/network/other charges; leaving it on for ~730 hours would be roughly $7.67 compute before those extras. This is why `enable_cloud_sql=false` is the safe repository default.

## Cost equation to capture during runtime

```text
Total lab cost =
  Cloud Run billable CPU + memory + requests
+ Pub/Sub publish/delivery/retention/transfer
+ Cloud SQL compute + storage + backup/network
+ BigQuery scan + storage/streaming if applicable
+ Artifact Registry storage/egress
+ Logging/Monitoring volume beyond allowances
+ Secret Manager operations/storage
+ Eventarc events if enabled
+ network transfer
```

## Evidence requirements

A measured run must record:

1. project and region;
2. start/end timestamps;
3. resources created;
4. request/event volume;
5. BigQuery bytes processed;
6. Cloud Run billable instance time;
7. Cloud SQL enabled duration;
8. billing report/cost export after charges settle;
9. destroy evidence.

Never label an estimate as `MEASURED`.
