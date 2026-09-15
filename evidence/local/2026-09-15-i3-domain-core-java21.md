# I3 Domain Core — Local Evidence

Date: 2026-09-15.

Environment available to the execution agent:

```text
openjdk version "21.0.11" 2026-04-21
javac 21.0.11
Maven: not installed
```

Command:

```bash
javac --release 21 -d out $(find src/main/java src/test/java -name '*.java' | sort)
java -cp out com.maya.finance.domain.CoreModelSelfTest
```

Observed result:

```text
CORE_MODEL_SELF_TEST=PASS
```

Status: `LOCALLY_VALIDATED` for the dependency-free Customer/Vehicle/Contract domain model under Java 21 only.

Not proven by this evidence: Java 25 runtime, Spring Boot 4.1.1, PostgreSQL, Cloud Run, GCP deployment, API behavior or production readiness.
