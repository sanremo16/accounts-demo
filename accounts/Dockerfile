FROM openjdk:17-jdk-alpine
COPY target/*.jar accounts.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar", "/accounts.jar"]