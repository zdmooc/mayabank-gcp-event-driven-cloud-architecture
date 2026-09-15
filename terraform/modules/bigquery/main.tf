variable "project_id" { type = string }
variable "dataset_id" { type = string }
variable "location" { type = string }
variable "environment" { type = string }

resource "google_bigquery_dataset" "analytics" {
  project                    = var.project_id
  dataset_id                 = var.dataset_id
  location                   = var.location
  delete_contents_on_destroy = true
  labels = {
    environment = var.environment
    managed_by  = "terraform"
  }
}

output "dataset_id" { value = google_bigquery_dataset.analytics.dataset_id }
