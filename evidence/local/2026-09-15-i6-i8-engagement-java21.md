# I6-I8 Domain Engagement — Local Evidence

Date: 2026-09-15. OpenJDK/Javac 21.0.11, dependency-free code.

Observed after full domain compilation:

```text
ENGAGEMENT_SELF_TEST=PASS residual=20224.00
```

The test exercised:

- Relationship aggregation;
- `LEASE_END_APPROACHING` detection;
- `HIGH_MILEAGE` detection;
- `VEHICLE_RENEWAL` and `LEASE_REVIEW` deterministic recommendations;
- positive `SYNTHETIC_V1` residual-value calculation.

Status: `LOCALLY_VALIDATED` for the dependency-free business rules under Java 21.

Not validated: Spring controllers/persistence, scheduled evaluation, Pub/Sub consumers, BigQuery, mobile BFF runtime or GCP deployment.
