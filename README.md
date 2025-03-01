# API Rest - Backend CRUD Processos do desafio
Este projeto foi criado utilizando Spring e o banco de dados MySQL. A biblioteca Lombok também foi utilizada para gerar as classes básicas do projeto.

## Iniciando o banco de dados MySQL

Para configurar o banco de dados MySQL, é necessário executar o script docker-compose.yml localizado na pasta raiz do projeto. O arquivo docker-compose.yml contém as informações necessárias para conectar ao banco de dados.

```bash
docker-compose up
```

## Iniciando o servidor

Instale o Maven. Acesse o site oficial do Apache Maven:
https://maven.apache.org/download.cgi

Execute o comando abaixo para iniciar o servidor. URL base: http://localhost:8080/api/v1

```bash
mvn spring-boot:run
```

## Swagger API

http://localhost:8080/swagger-ui/index.html
