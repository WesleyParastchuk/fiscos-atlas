# Estagio 1: Build (Compilacao)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compila e gera o .jar (pula testes para ser mais rapido no deploy)
RUN mvn clean package -DskipTests

# Estagio 2: Runtime (Execução)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copia apenas o .jar gerado no estagio anterior
COPY --from=build /app/target/*.jar app.jar

# Expoe a porta 8080
EXPOSE 8080

# Comando para rodar a aplicacao
ENTRYPOINT ["java", "-jar", "app.jar"]