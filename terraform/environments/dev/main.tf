locals {
  required_services = toset([
    "artifactregistry.googleapis.com",
    "bigquery.googleapis.com",
    "cloudresourcemanager.googleapis.com",
    "iam.googleapis.com",
    "iamcredentials.googleapis.com",
    "pubsub.googleapis.com",
    "run.googleapis.com",
    "secretmanager.googleapis.com",
    "sqladmin.googleapis.com"
  ])

  topics = toset([
    "customer-events-v1",
    "vehicle-events-v1",
    "contract-events-v1",
    "engagement-events-v1",
    "audit-events-v1"
  ])
}

module "project_services" {
  source     = "../../modules/project-services"
  project_id = var.project_id
  services   = local.required_services
}

module "iam" {
  source     = "../../modules/iam"
  project_id = var.project_id
  service_accounts = {
    "maya-finance-runtime" = {
      display_name = "Maya Finance Cloud Run runtime"
      roles = toset([
        "roles/pubsub.publisher",
        "roles/pubsub.subscriber",
        "roles/secretmanager.secretAccessor",
        "roles/cloudsql.client"
      ])
    }
    "maya-audit-runtime" = {
      display_name = "Maya Finance analytics ingestion"
      roles = toset([
        "roles/pubsub.subscriber",
        "roles/bigquery.dataEditor"
      ])
    }
  }
  depends_on = [module.project_services]
}

module "artifact_registry" {
  source        = "../../modules/artifact-registry"
  project_id    = var.project_id
  region        = var.region
  repository_id = "maya-finance"
  depends_on    = [module.project_services]
}

module "pubsub" {
  source      = "../../modules/pubsub"
  project_id  = var.project_id
  topics      = local.topics
  environment = var.environment
  depends_on  = [module.project_services]
}

module "secrets" {
  source     = "../../modules/secret-manager"
  project_id = var.project_id
  secret_ids = toset(["maya-finance-db-password"])
  depends_on = [module.project_services]
}

module "analytics" {
  source      = "../../modules/bigquery"
  project_id  = var.project_id
  dataset_id  = "maya_finance_analytics"
  location    = "EU"
  environment = var.environment
  depends_on  = [module.project_services]
}

module "cloud_run" {
  source                = "../../modules/cloud-run"
  project_id            = var.project_id
  region                = var.region
  service_name          = "maya-financial-hub"
  image                 = var.application_image
  service_account_email = module.iam.emails["maya-finance-runtime"]
  environment           = var.environment
  max_instances         = 3
  depends_on            = [module.artifact_registry, module.pubsub]
}

module "cloud_sql" {
  count      = var.enable_cloud_sql ? 1 : 0
  source     = "../../modules/cloud-sql"
  project_id = var.project_id
  region     = var.region
  name       = "maya-finance-${var.environment}"
  depends_on = [module.project_services]
}

module "budget" {
  count           = var.billing_account != "" && var.project_number != "" ? 1 : 0
  source          = "../../modules/budget"
  billing_account = var.billing_account
  project_number  = var.project_number
  amount_eur      = var.budget_eur
}
