variable "project_id" { type = string }
variable "region" { type = string }
variable "name" { type = string }
variable "database_version" {
  type    = string
  default = "POSTGRES_17"
}
variable "tier" {
  type    = string
  default = "db-f1-micro"
}
variable "deletion_protection" {
  type    = bool
  default = false
}

resource "google_sql_database_instance" "postgres" {
  project             = var.project_id
  name                = var.name
  region              = var.region
  database_version    = var.database_version
  deletion_protection = var.deletion_protection

  settings {
    tier              = var.tier
    edition           = "ENTERPRISE"
    availability_type = "ZONAL"
    disk_type         = "PD_SSD"
    disk_size         = 10
    disk_autoresize   = true
    backup_configuration { enabled = false }
    ip_configuration { ipv4_enabled = true }
  }
}

resource "google_sql_database" "app" {
  project  = var.project_id
  name     = "maya_finance"
  instance = google_sql_database_instance.postgres.name
}

output "connection_name" { value = google_sql_database_instance.postgres.connection_name }
