FROM maven:3.8.7-openjdk-18-slim AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:go-offline

COPY src/ ./src/

RUN mvn package -DskipTests

FROM openjdk:18

WORKDIR /app

COPY --from=builder /app/target/*.jar Group12-WebShop.jar

ENTRYPOINT ["java", "-jar", "Group12-WebShop.jar" ]