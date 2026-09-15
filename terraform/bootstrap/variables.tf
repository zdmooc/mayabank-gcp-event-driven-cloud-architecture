variable "project_id" { type = string }
variable "github_repository" {
  type    = string
  default = "zdmooc/mayabank-gcp-event-driven-cloud-architecture"
}
variable "pool_id" {
  type    = string
  default = "github-actions"
}
variable "provider_id" {
  type    = string
  default = "github"
}
variable "deployer_account_id" {
  type    = string
  default = "github-gcp-deployer"
}
variable "deployer_roles" {
  type = set(string)
  default = [
    "roles/artifactregistry.admin",
    "roles/bigquery.admin",
    "roles/cloudsql.admin",
    "roles/iam.serviceAccountAdmin",
    "roles/iam.serviceAccountUser",
    "roles/pubsub.admin",
    "roles/resourcemanager.projectIamAdmin",
    "roles/run.admin",
    "roles/secretmanager.admin",
    "roles/serviceusage.serviceUsageAdmin"
  ]
}
