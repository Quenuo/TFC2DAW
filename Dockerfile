FROM openjdk:21-jdk-slim
ARG JAR_FILE=target/BackendJurasick-1.0-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]