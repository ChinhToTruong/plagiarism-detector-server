FROM openjdk:17-jdk-alpine

WORKDIR /app

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} springboot-application.jar

ENTRYPOINT ["java","-jar","springboot-application.jar"]

EXPOSE 8080