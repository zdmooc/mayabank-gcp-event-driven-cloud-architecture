# Java / Spring Boot Stack Baseline

Checked: 2026-09-15.

Selected target:

- Java 25;
- Spring Boot 4.1.1;
- Maven multi-module build.

Official Spring sources checked on 2026-09-15 state that Spring Boot 4.1.1 is released and supports Java 17 through Java 26. Java 25 is therefore inside the supported range.

Sources:

- https://spring.io/blog/2026/08/20/spring-boot-4-1-1-available-now/
- https://docs.spring.io/spring-boot/system-requirements.html

The local execution environment used while authoring the repository exposes Java 21 and no Maven. Therefore the Java 25/Spring Boot build remains `IMPLEMENTED / NOT_YET_CI_VALIDATED` until the GitHub workflow completes successfully.

The first executable is intentionally a composition service around the bounded contexts. The target architecture may split deployables when independent scaling, ownership, lifecycle, security or failure-isolation needs justify it.
