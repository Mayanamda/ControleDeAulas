# Projeto de Controle de Aulas

Este é um projeto de controle de faltas e participação de alunos em aulas, desenvolvido em Java Spring Boot com banco de dados Oracle para armazenamento dos dados dos alunos e das informações de presença/falta.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas e recursos instalados em seu ambiente de desenvolvimento:

- Java Development Kit (JDK) 8 ou superior
- Apache Maven
- Oracle Database (ou uma instância Oracle compatível)

## Configuração do Banco de Dados Oracle

1. Crie um esquema de banco de dados Oracle para o projeto.
2. Atualize as configurações de conexão com o banco de dados no arquivo `application.properties` no diretório `src/main/resources` do projeto Spring Boot.

   ```properties
   spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/servicename
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.datasource.driverClassName=oracle.jdbc.OracleDriver