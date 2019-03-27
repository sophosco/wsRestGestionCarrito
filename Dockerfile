FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]