FROM openjdk:17-jdk-slim
COPY build/libs/*SNAPSHOT.jar /sharefit_server.jar
ENTRYPOINT ["java", "-jar", "/sharefit_server.jar"]