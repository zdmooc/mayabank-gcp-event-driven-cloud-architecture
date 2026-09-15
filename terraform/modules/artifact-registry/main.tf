variable "project_id" { type = string }
variable "region" { type = string }
variable "repository_id" { type = string }

resource "google_artifact_registry_repository" "docker" {
  project       = var.project_id
  location      = var.region
  repository_id = var.repository_id
  format        = "DOCKER"
  description   = "Maya Automotive Financial Services POC images"
  labels = {
    managed_by = "terraform"
    purpose    = "gcp-event-driven-poc"
  }
}

output "repository" { value = google_artifact_registry_repository.docker.name }
