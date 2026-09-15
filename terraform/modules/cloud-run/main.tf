variable "project_id" { type = string }
variable "region" { type = string }
variable "service_name" { type = string }
variable "image" { type = string }
variable "service_account_email" { type = string }
variable "environment" { type = string }
variable "max_instances" {
  type    = number
  default = 3
}

resource "google_cloud_run_v2_service" "service" {
  project             = var.project_id
  name                = var.service_name
  location            = var.region
  deletion_protection = false
  ingress             = "INGRESS_TRAFFIC_ALL"

  template {
    service_account = var.service_account_email
    scaling {
      min_instance_count = 0
      max_instance_count = var.max_instances
    }
    containers {
      image = var.image
      ports { container_port = 8080 }
      resources {
        limits = {
          cpu    = "1"
          memory = "512Mi"
        }
      }
      env {
        name  = "MAYA_ENVIRONMENT"
        value = var.environment
      }
    }
  }

  labels = {
    environment = var.environment
    managed_by  = "terraform"
  }
}

output "uri" { value = google_cloud_run_v2_service.service.uri }
output "name" { value = google_cloud_run_v2_service.service.name }
