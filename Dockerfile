FROM eclipse-temurin:11-alpine
COPY target/app.jar /app.jar

EXPOSE 7000
ENTRYPOINT ["java", "-jar", "/app.jar"]