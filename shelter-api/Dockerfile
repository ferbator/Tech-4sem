# Первый этап: сборка приложения
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x gradlew
RUN ./gradlew build --no-daemon

# Второй этап: минимальный образ для запуска
FROM eclipse-temurin:17-jdk AS runner
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar /app/app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
