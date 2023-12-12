FROM openjdk:17-jdk-alpine
COPY target/*.jar accounts.jar
ENTRYPOINT ["java", "-jar", "/accounts.jar"]