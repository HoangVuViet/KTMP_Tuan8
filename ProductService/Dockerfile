# Dockerfile cho ProductService
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/product-service.jar /app/product-service.jar

ENTRYPOINT ["java", "-jar", "product-service.jar"]
