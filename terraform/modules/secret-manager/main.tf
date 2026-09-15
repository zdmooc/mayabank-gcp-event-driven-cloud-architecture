variable "project_id" { type = string }
variable "secret_ids" { type = set(string) }

resource "google_secret_manager_secret" "secret" {
  for_each  = var.secret_ids
  project   = var.project_id
  secret_id = each.value
  replication { auto {} }
  labels = {
    managed_by = "terraform"
    purpose    = "gcp-event-driven-poc"
  }
}

output "secret_ids" { value = keys(google_secret_manager_secret.secret) }
