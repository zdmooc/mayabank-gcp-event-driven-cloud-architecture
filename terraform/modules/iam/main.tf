variable "project_id" { type = string }
variable "service_accounts" {
  type = map(object({
    display_name = string
    roles        = set(string)
  }))
}

resource "google_service_account" "runtime" {
  for_each     = var.service_accounts
  project      = var.project_id
  account_id   = each.key
  display_name = each.value.display_name
}

resource "google_project_iam_member" "runtime_roles" {
  for_each = merge([
    for sa_name, cfg in var.service_accounts : {
      for role in cfg.roles : "${sa_name}:${role}" => {
        sa_name = sa_name
        role    = role
      }
    }
  ]...)
  project = var.project_id
  role    = each.value.role
  member  = "serviceAccount:${google_service_account.runtime[each.value.sa_name].email}"
}

output "emails" { value = { for k, v in google_service_account.runtime : k => v.email } }
