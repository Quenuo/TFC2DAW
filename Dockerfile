FROM maven:3.9.4-eclipse-temurin-17 AS build

COPY . /app
WORKDIR /app

RUN mvn clean package

FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/BackendJurasick-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]