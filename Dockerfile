FROM maven:3.9.9-eclipse-temurin-21 AS build

COPY . /app
WORKDIR /app

RUN mvn clean package

FROM openjdk:21-jdk-slim
COPY --from=build /app/target/BackendJurasick-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]