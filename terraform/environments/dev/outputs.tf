output "cloud_run_uri" { value = module.cloud_run.uri }
output "pubsub_topics" { value = module.pubsub.topic_ids }
output "bigquery_dataset" { value = module.analytics.dataset_id }
output "cloud_sql_connection" { value = var.enable_cloud_sql ? module.cloud_sql[0].connection_name : null }
