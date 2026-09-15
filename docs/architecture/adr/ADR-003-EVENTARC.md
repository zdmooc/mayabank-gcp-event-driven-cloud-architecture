# ADR-003 — Eventarc role

Status: **Accepted**.

## Decision

Use Pub/Sub as the explicit domain messaging backbone. Use **Eventarc selectively** for event routing from Google Cloud providers to supported targets when it removes custom integration work.

## Why

Eventarc is a managed event-routing capability. It is valuable for Google Cloud state-change routing, but placing every domain event behind Eventarc would obscure ownership and unnecessarily complicate a POC whose core requirement is explicit domain-event governance.

## Consequence

No Eventarc resource is created merely to increase the service count. Each trigger/bus/pipeline must have a documented source, target and reason.
