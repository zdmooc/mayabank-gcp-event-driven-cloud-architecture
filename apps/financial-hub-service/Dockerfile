FROM eclipse-temurin:25-jre
WORKDIR /app
COPY target/financial-hub-service-*.jar app.jar
USER 65532:65532
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
