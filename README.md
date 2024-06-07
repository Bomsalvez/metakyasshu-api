# Projeto Metakyasshu

Este é um projeto desenvolvido em Java utilizando o framework Spring Boot e gerenciado pelo Maven.

## Tecnologias e Ferramentas

- Java
- Spring Boot
- Maven
- Spring Data JPA
- Spring Security
- Thymeleaf
- Quartz Scheduler
- Java Mail Sender
- Docker Compose

## Configuração e Execução

Para executar este projeto, você precisa ter o Java e o Maven instalados em seu ambiente. Além disso, você precisa ter o Docker instalado para executar o banco de dados MySQL através do Docker Compose.

1. Clone o repositório para o seu ambiente local.
2. Navegue até o diretório do projeto.
3. Execute o comando `mvn clean install` para construir o projeto.
4. Execute o comando `docker-compose up` para iniciar o banco de dados MySQL.
5. Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

A aplicação estará disponível em `http://localhost:8080`.
O banco de dados estará disponível em `http://localhost:3306`.
O swagger estará disponível em `http://localhost:8080/api/swagger-ui/index.html`.

## Testes

Os testes são escritos usando o framework de testes JUnit. Para executar os testes, use o comando `mvn test`.

## Documentação

Para mais informações sobre as tecnologias e ferramentas utilizadas, consulte a documentação oficial:

- [Documentação oficial do Apache Maven](https://maven.apache.org/guides/index.html)
- [Guia de referência do plugin Spring Boot Maven](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
- [Suporte ao Docker Compose](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#features.docker-compose)
- [Java Mail Sender](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#io.email)
- [Quartz Scheduler](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#io.quartz)
- [Spring Security](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web.security)
- [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)
- [Validation](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#io.validation)
- [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)

## Contribuição

Se você deseja contribuir para este projeto, por favor, faça um fork do repositório e submeta um pull request.