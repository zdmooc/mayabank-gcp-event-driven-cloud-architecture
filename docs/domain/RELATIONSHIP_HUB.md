# Customer Relationship Hub

The Relationship Hub is a read-oriented projection, not the canonical owner of customer or contract state.

Target response:

```json
{
  "customerId": "CUS-10045",
  "vehicles": [],
  "contracts": [],
  "recommendations": [],
  "renewalOpportunities": [],
  "interactions": []
}
```

Primary API: `GET /customers/{id}/relationship`.

The Mobile BFF exposes channel-oriented forms such as `/me`, `/me/contracts`, `/me/vehicles`, `/me/recommendations`, `/me/renewal-opportunities` without duplicating domain ownership.

Projection updates are expected to be event-driven and idempotent. Consistency is therefore explicit: transactional command state and relationship read state can be temporarily different, and the final UI/runtime must expose/handle this deliberately rather than pretending distributed atomicity.
