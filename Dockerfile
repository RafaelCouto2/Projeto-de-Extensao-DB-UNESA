# Use a imagem base do Amazon Corretto JDK 21
FROM amazoncorretto:21.0.3
LABEL name="minha-escolinha"
# Mantainer information
LABEL maintainer="Projeto de extensão, UNESA"

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e as configurações de dependências
COPY pom.xml /app

# Copiar o Maven Wrapper
COPY mvnw /app/mvnw
COPY .mvn /app/.mvn

# Copiar o código fonte
COPY src /app/src

# Copiar outros arquivos necessários (como configuração, recursos, etc.)
COPY . /app

COPY out/artifacts/MinhaEscolinha /app/MinhaEscolinha


# Expor a porta em que a aplicação rodará (se necessário)
EXPOSE 8080

# Definir o ponto de entrada para executar a aplicação
CMD ["java", "-jar", "MinhaEscolinha/MinhaEscolinha.jar"]
