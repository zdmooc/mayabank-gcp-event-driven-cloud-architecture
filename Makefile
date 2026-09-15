TF_DIR ?= terraform/environments/dev

.PHONY: test terraform-fmt terraform-validate plan apply destroy

test:
	mvn -B verify

terraform-fmt:
	terraform fmt -recursive terraform

terraform-validate:
	terraform fmt -check -recursive terraform
	terraform -chdir=terraform/bootstrap init -backend=false
	terraform -chdir=terraform/bootstrap validate
	terraform -chdir=$(TF_DIR) init -backend=false
	terraform -chdir=$(TF_DIR) validate

plan:
	terraform -chdir=$(TF_DIR) plan

apply:
	terraform -chdir=$(TF_DIR) apply

destroy:
	terraform -chdir=$(TF_DIR) destroy
