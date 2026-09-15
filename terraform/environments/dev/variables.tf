variable "project_id" { type = string }
variable "project_number" {
  type    = string
  default = ""
}
variable "region" {
  type    = string
  default = "europe-west1"
}
variable "environment" {
  type    = string
  default = "dev"
}
variable "application_image" {
  type        = string
  description = "Immutable Artifact Registry image reference for the financial hub service"
}
variable "enable_cloud_sql" {
  type    = bool
  default = false
}
variable "billing_account" {
  type        = string
  default     = ""
  description = "Optional billing account ID. Budget resource is skipped when empty."
}
variable "budget_eur" {
  type    = number
  default = 25
}
