# 1. Estágio de Build (Compilação)
# Iimagem do Maven com o Microsoft OpenJDK 17 para compilar
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia os arquivos de configuração e o código fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto gerando o .jar (pulando testes para velocidade)
RUN mvn clean package -DskipTests

# 2. Estágio de Execução (Runtime)
# Usamos a imagem oficial da Microsoft para o Java 17 (específica para o seu SDK)
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
WORKDIR /app

# Copia o arquivo .jar gerado no primeiro estágio
COPY --from=build /app/target/*.jar app.jar

# O Render exige que a aplicação escute em uma porta específica (padrão 10000)
# A aplicação DEVE bindar em 0.0.0.0 para receber tráfego externo [3, 4]
EXPOSE 10000

# Comando para iniciar a aplicação respeitando a porta definida pelo Render
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:10000}", "--server.address=0.0.0.0"]
