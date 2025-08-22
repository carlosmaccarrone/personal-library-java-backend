# build stage
FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests clean package

# runtime stage
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Spring environment variables
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://library_db:5432/personal_library
ENV SPRING_DATASOURCE_USERNAME=admin
ENV SPRING_DATASOURCE_PASSWORD=admin123

COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]