# GreenOps

Status: `DESIGNED`; no carbon measurement is claimed.

## Architecture choices

- Cloud Run baseline with `min_instance_count = 0` to avoid always-on compute for the POC.
- No GKE cluster solely for demonstration.
- Cloud SQL disabled by default because it is an always-running managed database once provisioned.
- BigQuery on-demand for small analytical workloads rather than reserved capacity.
- Dataflow stays target-only unless volume justifies a running pipeline.
- bounded log retention and event retention will be configured from actual recovery/audit needs rather than infinite retention.
- resource limits are explicit and load tests are used before increasing them.
- `make destroy` is part of the Definition of Done for cloud evidence runs.

## Region selection

The lab default is European, but no claim is made that one region is universally the lowest-carbon choice. Final region selection must jointly consider:

- business/data-residency requirements;
- user latency;
- service availability/features;
- HA/DR geography;
- cost;
- current carbon-intensity/sustainability information from authoritative sources.

## Measurements to add

- Cloud Run billable instance time per demo scenario;
- idle/alive resource inventory after demo;
- event/log retention volume;
- BigQuery bytes scanned per dashboard query;
- Cloud SQL hours actually enabled;
- estimated carbon/energy indicators only when an accepted measurement source/method is available.

GreenOps in this repository means reducing unnecessary resources first, not inventing a precise kgCO2e value without a defensible calculator and workload data.
