variable "project_id" { type = string }
variable "topics" { type = set(string) }
variable "environment" { type = string }

resource "google_pubsub_topic" "topic" {
  for_each = var.topics
  project  = var.project_id
  name     = each.value
  labels = {
    environment = var.environment
    managed_by  = "terraform"
  }
}

output "topic_ids" { value = { for k, v in google_pubsub_topic.topic : k => v.id } }
