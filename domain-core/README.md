# Domain Core

Dependency-free Java domain model used to validate business rules before coupling the POC to Spring Boot/GCP adapters.

Target application stack: Java 25 + Spring Boot 4.1.x (version pinned separately). Current local evidence in this repository was produced with OpenJDK 21 because that is the available local compiler. The code intentionally uses Java 21-compatible language features; this is not proof of the final Java 25/Spring runtime.

Compile the dependency-free core locally:

```bash
mkdir -p out
javac --release 21 -d out $(find src/main/java src/test/java -name '*.java' | sort)
java -cp out com.maya.finance.domain.CoreModelSelfTest
```
