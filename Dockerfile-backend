FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/student-manager-0.0.1-SNAPSHOT.jar /app/student-manager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "student-manager.jar"]