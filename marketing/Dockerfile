# ===== Stufe 1: Build =====
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build

# Erst nur die pom.xml kopieren, um Abhängigkeiten zu cachen
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Dann den Quellcode kopieren und bauen
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stufe 2: Runtime =====
FROM eclipse-temurin:21-jre-alpine

# Non-Root User anlegen (Sicherheitsanforderung!)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app
# Nur die fertige .jar aus Stufe 1 kopieren
COPY --from=builder /build/target/*.jar app.jar

# Besitzer wechseln und als non-root starten
RUN chown appuser:appgroup app.jar
USER appuser

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]