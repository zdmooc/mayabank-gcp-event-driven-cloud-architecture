# Terraform — GCP lab

Status: `IMPLEMENTED / NOT_APPLIED`.

The Terraform code is designed for a disposable `dev` lab. No resource in this directory is evidence that GCP has been provisioned.

Current provider baseline checked 2026-09-15: `hashicorp/google 8.2.0`.

Cost guardrails:

- Cloud Run min instances = 0;
- Cloud SQL is disabled by default and must be explicitly enabled for a timed lab;
- no GKE/Apigee/Dataflow is created by the MVP root;
- resource labels include environment and project purpose;
- `make destroy` is the mandatory teardown path once a real project is connected.

Structure:

```text
terraform/
├── bootstrap/              # WIF/bootstrap separated from workload state
├── modules/
│   ├── project-services/
│   ├── iam/
│   ├── artifact-registry/
│   ├── cloud-run/
│   ├── pubsub/
│   ├── secret-manager/
│   ├── cloud-sql/
│   ├── bigquery/
│   ├── budget/
│   ├── networking/         # target design placeholder; not needed by MVP root yet
│   ├── eventarc/           # selective target design placeholder
│   └── observability/      # target design placeholder
└── environments/
    ├── dev/                # first deployable root
    ├── preprod/            # architecture placeholder
    └── prod/               # architecture placeholder
```

Run only after setting a dedicated GCP project and reviewing cost/quota:

```bash
cd terraform/environments/dev
terraform init
terraform plan -var='project_id=...' -var='region=europe-west1'
terraform apply
# capture evidence, then
terraform destroy
```
