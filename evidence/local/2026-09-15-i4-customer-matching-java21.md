# I4 Customer Matching — Local Evidence

Date: 2026-09-15. Runtime: OpenJDK/Javac 21.0.11, dependency-free domain core.

Observed command/result after compiling all domain sources with `javac --release 21`:

```text
MATCHING_SELF_TEST=PASS
```

The self-test asserts three decision branches:

- exact/composite identity -> `MATCHED`;
- email-only signal at configured threshold -> `REVIEW_REQUIRED`;
- unrelated identity -> `NO_MATCH`.

The scoring rules are deterministic and returned as reason codes.

Status: `LOCALLY_VALIDATED` for the pure Java matching logic on Java 21.

Not validated here: Spring API, persisted customer search, large candidate sets, production thresholds, PII handling or GCP runtime.
