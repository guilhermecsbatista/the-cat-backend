[![Build Status](https://travis-ci.org/guilhermecsbatista/the-cat-backend.svg)](https://travis-ci.org/github/guilhermecsbatista/the-cat-backend)
[![codecov](https://codecov.io/gh/guilhermecsbatista/the-cat-backend/branch/develop/graph/badge.svg)](https://codecov.io/gh/guilhermecsbatista/the-cat-backend)

# the-cat-backend


## the-cat-backend é uma aplicação responsável por coletar informações da [API The Cat](https://thecatapi.com/), armazenando em um banco de dados Postgres e expõe uma API com endpoints de busca.

# Sumário

- [Pré-requisitos](#Pré-requisitos)
- [Configuração](#Configuração)
- [Testes](#Testes)
- [Executar](#Executar)
- [Arquitetura](#Arquitetura)
- [Aplicação](#Aplicação)

# Pré-requisitos

- [Java-11](https://sdkman.io/install)
- [Plugin Lombok](https://projectlombok.org)
- [Docker compose](https://docs.docker.com/compose/install/)

# Configuração

**Faça um clone do projeto:**

https<br/>
https://github.com/guilhermecsbatista/the-cat-backend.git

**Instale as dependências do projeto:**<br/>

```
Java 11 - SDKMAN:
https://sdkman.io/install
sdk i java 11.0.2-open
```

```
Lombok plugin

Intellij: https://projectlombok.org/setup/intellij
Eclipse : https://projectlombok.org/setup/eclipse
```

**Configure as variáveis de ambiente de acordo com o ambiente:**<br/> `src/main/resources/application.yml`:

| Nome | Descrição | Valor Padrão | Obrigatório |
| -- | -- | -- | -- |
| ENV | Ambiente | development | :white_check_mark: |
| APPLICATION_CLIENT_DEFAULT_TIMEOUT | Timeout de requisição de API's externas | 10000 | :white_check_mark: |
| APPLICATION_SCHEDULE_CRON | [CRON](http://www.cronmaker.com/) de agendamento da coleta de dados  | 0 0/2 * 1/1 * ? | :white_check_mark: |
| APPLICATION_SECURITY_JWT_SECRET_KEY | Secret key JWT | xxxxx | :white_check_mark: |
| APPLICATION_THREAD_CORE_POOL_SIZE | Quantidade de threads em execução | 5 | :white_check_mark: |
| APPLICATION_THREAD_MAX_POOL_SIZE | Quantidade maxima de threads | 10 | :white_check_mark: |
| APPLICATION_THREAD_QUEUE_CAPACITY | Quantidade total da fila de Threads | 5 | :white_check_mark: |
| THECATAPI_TOKEN | Token de autenticação a API thecatapi | xxxxxx | :white_check_mark: |
| THECATAPI_URL_BASE | URL base | https://api.thecatapi.com | :white_check_mark: |
| THECATAPI_ENDPOINT_V1_BREED_PATH | Endpoint de busca raças | /v1/breeds | :white_check_mark: |
| THECATAPI_ENDPOINT_V1_BREED_LIMIT | Limite de retorno de registros | 50 | :white_check_mark: |
| THECATAPI_ENDPOINT_V1_IMAGE_PATH | Endpoint de busca de images | /v1/images/search | :white_check_mark: |
| THECATAPI_ENDPOINT_V1_IMAGE_LIMIT | Limite de retorno de registros | 3 | :white_check_mark: |
| SPRING_DATASOURCE_URL | URL JDBC para conectar ao banco de dados | jdbc:postgresql:// | :white_check_mark: |
| SPRING_DATASOURCE_HOST | HOST | localhost | :white_check_mark: |
| SPRING_DATASOURCE_PORT | PORTA | 5432 | :white_check_mark: |
| SPRING_DATASOURCE_DBNAME | Nome do banco de dados | cat | :white_check_mark: |
| SPRING_DATASOURCE_USERNAME | Usuário para conectar ao banco de dados | cat | :white_check_mark: |
| SPRING_DATASOURCE_PASSWORD | Senha para conectar ao banco de dados | cat | :white_check_mark: |
| SPRING_DATASOURCE_HIKARI_MAXIMUM_POOL_SIZE | uantidade máxinma de conexões no pool | 10 | :white_check_mark: |
| SPRING_FLYWAY_ENABLED | Habilitar execução do Flyway | true | :white_check_mark: |

# Testes

Para executar os testes do projeto, execute o comando abaixo

```
./mvnw clean install jacoco:report
```

# Executar

**Executar manualmente**

```
./mvnw clean spring-boot:run
```

**Executar via docker-compose**

```
docker-compose up -d
```

**Acessando a documentação da API**
```
http://localhost:8080/docs
```

# Arquitetura

**Arquitetura da solução**

A solução proposta foi desenvolver uma aplicação que possa ser escalável e simples. Podemos observar que a API e o Worker estão contidos na mesma aplicação the-cat-backend, no primeiro momento foi utilizado esta estratégia por estarem no mesmo contexto.

<img src="./docs/images/Architecture.png">

**BPMN Usecases**

<img src="./docs/images/BPMN Usecases.png">

**Arquitetura limpa**

A arquitetura do projeto foi desenvolvido seguindo os conceitos da [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) com os princípios do [SOLID](https://pt.wikipedia.org/wiki/SOLID).

<img src="./docs/images/Clean Architecture.jpeg">

**Arquitetura do projeto**

Acredito que o excesso de complexidade possa impactar no time de desenvolvedores. Então baseado na arquitetura limpa, foi criado uma estrutura de pacotes neste formato.

<img src="./docs/images/Clean Architecture project.png">


```
the-cat-backend/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── dev
│   │   │           └── thecat
│   │   │               ├── app
│   │   │               │   ├── config
│   │   │               │   ├── entrypoint
│   │   │               │   │   ├── api
│   │   │               │   │   │   ├── config
│   │   │               │   │   │   │   ├── documentation
│   │   │               │   │   │   │   ├── log
│   │   │               │   │   │   │   │   └── interceptor
│   │   │               │   │   │   │   └── security
│   │   │               │   │   │   └── v1
│   │   │               │   │   │       ├── endpoint
│   │   │               │   │   │       └── response
│   │   │               │   │   └── worker
│   │   │               │   └── provider
│   │   │               │       ├── api
│   │   │               │       │   ├── client
│   │   │               │       │   ├── config
│   │   │               │       │   ├── constant
│   │   │               │       │   └── response
│   │   │               │       └── database
│   │   │               │           └── postgres
│   │   │               │               ├── repository
│   │   │               │               └── table
│   │   │               └── domain
│   │   │                   ├── breed
│   │   │                   │   ├── entity
│   │   │                   │   ├── provider
│   │   │                   │   └── usecase
│   │   │                   │       ├── exception
│   │   │                   │       └── impl
│   │   │                   ├── image
│   │   │                   │   ├── entity
│   │   │                   │   └── provider
│   │   │                   ├── origin
│   │   │                   │   ├── entity
│   │   │                   │   └── provider
│   │   │                   └── temperament
│   │   │                       ├── entity
│   │   │                       └── provider
│   │   └── resources
│   │       ├── db
│   │       │   └── migration
│   │       │       └── commons
│   │       ├── static
│   │       └── templates

```

**Diagrama de classe**

Neste diagrama podemos observar a simplicidade de comunicação entre classes e módulos. Sendo o modulo APP o concentrador da infraestrutura e o DOMAIN o core da aplicação, contendo suas funcionalidades por domínio.

<img src="./docs/images/Diagram class.png">

**Modelagem do banco de dados**

<img src="./docs/images/Database Model.png">


# Aplicação

**Documentação da API**

Para acessar a documentação da API [clique aqui](https://the-cat-backend.herokuapp.com/docs)
```
token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.AlBG7wq8By4otICCo7n0-3H1QNCMswEGEMBp1Cg2-dM
```

**Postman collections**

```
 Ambiente Local
 the-cat-backend/docs/the-cat-backend-local.postman_collection.json
 
 Ambiente de produção
 the-cat-backend/docs/the-cat-backend-prod.postman_collection.json
```

***Docker hub -***
[Clique aqui](https://hub.docker.com/r/guilhermecsbatista/the-cat-backend)

***Travis CI -***
[Clique aqui](https://travis-ci.org/github/guilhermecsbatista/the-cat-backend)

***Codecov -***
[Clique aqui](https://codecov.io/gh/guilhermecsbatista/the-cat-backend)