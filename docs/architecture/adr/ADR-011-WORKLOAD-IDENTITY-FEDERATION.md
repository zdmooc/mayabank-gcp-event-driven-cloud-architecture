# ADR-011 — GitHub Workload Identity Federation

Status: **Accepted / IaC implemented / not GCP validated**.

## Decision

GitHub Actions authenticates to Google Cloud using OIDC and Workload Identity Federation, impersonating a dedicated deployment service account.

Do not store a GCP service-account JSON key in GitHub.

## Controls

- workload identity pool/provider dedicated to CI;
- repository claim restriction;
- later branch/GitHub Environment restriction before real apply;
- separate deployer and runtime identities;
- short-lived credentials;
- infrastructure roles reviewed independently of application roles.

Implementation: `terraform/bootstrap/`.
