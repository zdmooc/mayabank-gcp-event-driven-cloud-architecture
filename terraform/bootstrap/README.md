# Bootstrap — GitHub OIDC / Workload Identity Federation

Purpose: create a GitHub OIDC trust and a dedicated Terraform deployer without storing a service-account key in GitHub.

The bootstrap roles are intentionally separated from runtime service accounts. They are infrastructure-management permissions for this dedicated lab project and should be reduced further if organization policy or a narrower deployment pipeline allows it.

The provider is restricted to the repository claim `zdmooc/mayabank-gcp-event-driven-cloud-architecture`. Branch/environment restrictions should be tightened before a real apply, for example by adding a `ref`/GitHub Environment condition after the deployment branch strategy is finalized.

This directory itself still needs initial operator credentials to create the trust. Once WIF is established, GitHub Actions can authenticate with short-lived federated credentials.
