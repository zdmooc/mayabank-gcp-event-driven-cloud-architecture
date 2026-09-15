# Internal Reuse Analysis — zdmooc Portfolio

Purpose: transfer proven **methods and patterns**, not copy technology-specific implementation into GCP.

## `zdmooc/gcparchitect`

Observed role: large GCP learning/reference repository containing material around Apigee, GKE, infrastructure, reliability and Terraform, while its root README is minimal.

Reuse:

- GCP knowledge/reference material;
- terminology and service familiarity;
- Terraform/GKE/Apigee study material where still current.

Do not reuse as the main portfolio structure: the new repository must demonstrate a business-to-runtime architecture narrative.

## `zdmooc/mayabank-kafka-ddd-openshift`

Strong reusable method:

`Business need -> DDD/Bounded Contexts -> Event Storming -> functional architecture -> sync/async choice -> EDA -> security/sizing/HA/PRA/observability -> GitOps -> Architecture Board`.

Transfer to GCP:

- bounded contexts and domain-event reasoning;
- event governance and explicit technology arbitration;
- synthetic-data/no-client-confidentiality rule;
- architecture before broker/platform choice.

Do not mechanically transplant Kafka topology into Pub/Sub.

## `zdmooc/mayabank-ibm-mq-native-ha-openshift-eda-platform`

Strong reusable method:

- retry/backout/DLQ/replay/idempotency as demonstrable reliability concerns;
- separation of local lab vs HA target;
- evidence folders and no `tested` claim without execution output;
- HLD/LLD/ADR/runbooks plus observability.

Transfer to GCP:

- evidence-first reliability scenarios;
- explicit duplicate-message/idempotency tests;
- controlled DLQ replay runbook;
- truthful status matrix.

Do not transpose MQ-specific Native HA/JMS mechanisms into Pub/Sub semantics.

## `zdmooc/mayabank-azure-cloud-ai-platform`

Strong reusable method:

- end-to-end architecture chain from business/NFR to cloud/security/integration/data/observability/resilience/cost;
- clear separation of design, static validation and cloud runtime validation;
- ADR catalog, labs, Terraform validation and FinOps/GreenOps;
- workload identity instead of static credentials.

Transfer to GCP:

- repository information architecture;
- architecture-review discipline;
- Terraform CI gates;
- low-cost/disposable lab strategy;
- evidence and status vocabulary.

Do not cloud-translate Azure services one-for-one; make GCP-native decisions.

## `zdmooc/wero-organisme-poc`

Strong reusable method:

- synthetic financial actors;
- end-to-end flow plus timeout/retry/duplicate/lost-event failure cases;
- API/event/security/ledger/reconciliation thinking;
- OpenTelemetry/metrics/tracing and progressive resilience iterations.

Transfer: scenario-driven final demo and failure injection discipline.

## `zdmooc/maya-ai-agentic-architecture-reference`

Strong reusable method:

- architecture/evidence hub with specialist implementation repositories;
- `DESIGNED` explicitly does not imply implementation/runtime proof;
- FR/NFR, constraints, ADR, risk, RACI and architecture-review artifacts.

Transfer: Design Authority discipline and evidence-first vocabulary. The GCP POC itself stays autonomous and does not depend on an AI repository.

## `zdmooc/cadrage_202682030`

Role: master portfolio/strategy repository. Its current rule is to build a coherent demonstrable portfolio and avoid unnecessary generic POCs.

This GCP repository is justified because it closes a concrete **current mission gap**: GCP + Event Driven + HLD/LLD + customer matching + financial relationship hub. It should become the dedicated evidence source for that target, not another generic cloud course repository.

## Reuse decision

Reuse intellectually:

- DDD/Event Storming;
- event governance;
- retry/DLQ/replay/idempotence;
- OpenTelemetry and evidence packs;
- ADR/NFR/HLD/LLD discipline;
- Terraform CI/least privilege/workload identity;
- FinOps/GreenOps and teardown-first labs.

Keep this repository autonomous, GCP-native and business-specific.
