variable "billing_account" { type = string }
variable "project_number" { type = string }
variable "amount_eur" {
  type    = number
  default = 25
}

resource "google_billing_budget" "lab" {
  billing_account = var.billing_account
  display_name    = "maya-gcp-eda-lab-budget"

  budget_filter {
    projects = ["projects/${var.project_number}"]
  }

  amount {
    specified_amount {
      currency_code = "EUR"
      units         = var.amount_eur
    }
  }

  threshold_rules { threshold_percent = 0.5 }
  threshold_rules { threshold_percent = 0.9 }
  threshold_rules { threshold_percent = 1.0 }
}
