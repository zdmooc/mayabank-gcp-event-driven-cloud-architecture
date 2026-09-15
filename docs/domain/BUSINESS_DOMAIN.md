# Business Domain — Maya Automotive Financial Services

## 1. Purpose

Build a synthetic **Digital Financial Relationship Hub** that gives a customer a coherent view of the financial relationship around one or more vehicles, while letting architecture reviewers inspect how business needs map to APIs, events, data and GCP services.

This is not a real automotive-finance implementation. Names, identities, contracts, vehicle data, financial values and rules are synthetic.

## 2. Business outcomes

1. Consolidate customer, vehicle and contract information coming from multiple systems.
2. Reconcile customer identities transparently and explainably.
3. Expose a unified relationship view to two brand-neutral mobile channels.
4. Detect contextual renewal moments and generate deterministic next-best actions.
5. Propagate contract/customer changes asynchronously without tight coupling.
6. Provide auditable events and analytical views without turning analytics into the transactional source of truth.

## 3. Actors

| Actor | Goal |
|---|---|
| Customer | See vehicles, contracts, payments, offers and renewal opportunities |
| Mobile channel | Consume a stable customer-oriented BFF |
| Dealer / partner | Supply synthetic customer/vehicle/contract references |
| CRM / legacy system | Provide customer identity/contact updates |
| Contract system | Provide finance/lease/service/insurance contract changes |
| Finance system | Provide schedules and synthetic financial attributes |
| Recommendation function | Turn eligible business signals into explainable recommendations |
| Operations/SRE | Observe, troubleshoot, replay and audit flows |
| Data/analytics user | Analyze contracts, Golden Moments and recommendation outcomes |

## 4. Core aggregates

### Customer

- `Customer`
- `CustomerIdentity`
- `CustomerContact`
- `CustomerPreferences`
- `CustomerExternalReference`

Invariant examples:

- an internal `customerId` is stable and immutable;
- external references are source-qualified;
- matching decisions retain reason codes and source references;
- contact attributes are normalized before deterministic matching.

### Vehicle

- `Vehicle`
- `VehicleModel`
- `VehicleBrand`
- `VehicleConfiguration`
- `VehicleLifecycle`

All brands/configurations are fictional or generic.

### Contract

- `FinanceContract`
- `LeaseContract`
- `LoanContract`
- `ServiceContract`
- `InsuranceContract`

A customer may own several contracts across several vehicles.

### Financing

- `FinancingOffer`
- `Loan`
- `Lease`
- `MonthlyPayment`
- `Deposit`
- `Term`
- `InterestRate`

No formula or value in this repository is financial advice or a real regulated pricing model.

### Residual Value

- `ResidualValue`
- `VehicleValue`
- `Mileage`
- `ContractEndValue`

The MVP calculation is deliberately synthetic and exists to demonstrate ingestion -> calculation -> event -> recommendation.

### Recommendation

- `Recommendation`
- `NextBestAction`
- `RenewalOpportunity`
- `CrossSellOpportunity`

Recommendations are explainable deterministic rules in the MVP.

### Interaction

- `CustomerInteraction`
- `MobileInteraction`
- `ContractConsultation`
- `RecommendationViewed`

## 5. Primary business use cases

### UC01 — Ingest a new contract

A contract arrives from an external contract system. The platform validates it, stores transactional state, publishes `ContractCreated`, reconciles the customer and updates the relationship view.

### UC02 — Match a customer

The Customer Matching context evaluates exact identifiers, normalized deterministic attributes and a transparent weighted score. Output is `MATCHED`, `REVIEW_REQUIRED` or `NO_MATCH`, with reason codes.

### UC03 — View financial relationship

A mobile channel requests `/me` or `/customers/{id}/relationship` and receives vehicles, contracts, recommendations, renewal opportunities and recent interactions from the relationship projection.

### UC04 — Detect a Golden Moment

A domain change such as a lease end approaching, high synthetic mileage or contract anniversary emits/derives a `GoldenMomentDetected` event.

### UC05 — Generate a recommendation

A deterministic rule maps context to actions such as `VEHICLE_RENEWAL`, `NEW_FINANCE_OFFER`, `LEASE_REVIEW` or `SERVICE_CONTRACT`, preserving a reason code.

### UC06 — Analyze outcomes

Operational events are ingested to BigQuery for aggregate questions such as contracts ending within 120 days, recommendation conversion and Golden Moments by category.

## 6. Customer matching MVP

Three levels:

1. **Exact** — `customerId`, source-qualified external ID, normalized email, normalized phone.
2. **Deterministic composite** — normalized name/first name + synthetic birth date + synthetic postal code.
3. **Explainable score** — weighted rule score with reason codes; no opaque ML dependency.

Example result:

```json
{
  "matchingRequestId": "M-298",
  "candidateCustomerId": "CUS-10045",
  "score": 0.94,
  "decision": "MATCHED",
  "rules": ["EMAIL_EXACT", "BIRTHDATE_EXACT", "NAME_NORMALIZED"]
}
```

Future ML-assisted matching can be studied later, but the deterministic path remains the reference control and test oracle.

## 7. Golden Moments

Initial vocabulary:

- `CONTRACT_ANNIVERSARY`
- `LEASE_END_APPROACHING`
- `LOAN_END_APPROACHING`
- `HIGH_MILEAGE`
- `NEW_VEHICLE_ELIGIBILITY`
- `SERVICE_RENEWAL`

Thresholds are synthetic configuration, not business facts from a real company.

## 8. Business boundaries

Out of scope for the MVP:

- real credit underwriting;
- real APR/regulatory pricing;
- real insurance pricing;
- automated credit decisions;
- production PII;
- real manufacturer/dealer integrations;
- any claim that the model represents a real client platform.
