FROM openjdk:17-jdk-alpine
COPY target/*.jar persons.jar
ENTRYPOINT ["java", "-jar", "/persons.jar"]