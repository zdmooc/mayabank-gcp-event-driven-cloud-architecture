# RTO / RPO Position

No contractual RTO or RPO can be derived from the public job advertisements.

## POC

The single-region dev POC does not claim disaster recovery. Recovery objectives remain `TBD_BY_BUSINESS`.

## Target method

For each business capability determine:

- maximum tolerated outage;
- maximum tolerated data loss;
- consistency requirement;
- manual versus automatic failover tolerance;
- recovery dependency order;
- acceptable cost for stronger recovery.

Then map the results to GCP architecture.

Current Google Cloud documentation supports multi-region Cloud Run deployments with global routing/service-health patterns. Cloud SQL regional HA addresses zonal availability; cross-region database DR uses asynchronous replication and can therefore have non-zero RPO.

The repository will not invent numerical RTO/RPO values simply to complete a diagram.
