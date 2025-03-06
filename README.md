# Pharmacy Management

## Requisitos Funcionais

1. **CRUD de Clientes (Clients)**
   - Cadastro de novos clientes.
   - Atualização de informações dos clientes.
   - Listagem de clientes.
   - Remoção de clientes.

2. **CRUD de Medicamentos (Medications)**
   - Cadastro de novos medicamentos.
   - Atualização de informações dos medicamentos.
   - Listagem de medicamentos.
   - Remoção de medicamentos.

3. **Serviço de Listagem de Assinaturas (Subscriptions)**
   - Listagem de assinaturas de medicamentos contínuos de um cliente, informando o email do cliente.

## Requisitos Não-Funcionais

1. **Mensageria**
   - Utilização do RabbitMQ para tratar eventos de domínio da aplicação, garantindo escalabilidade e desacoplamento entre os serviços.

2. **Banco de Dados**
   - Utilização do MongoDB, que oferece alta escalabilidade e flexibilidade no armazenamento de dados.

## Arquitetura da Aplicação

A arquitetura da aplicação é baseada em **Clean Architecture**, que promove a separação de responsabilidades e facilita a manutenção e evolução do sistema.

## Event Driven Architecture

A aplicação utiliza **Event Driven Architecture** com RabbitMQ para tratar os eventos de domínio, garantindo que as ações sejam processadas de forma assíncrona e escalável.

## Fluxo Principal da Aplicação

1. **Cadastro do Cliente**
   - O cliente é cadastrado no sistema.

2. **Cadastro do Medicamento**
   - O medicamento é cadastrado no sistema.

3. **Criação de Assinatura**
   - Sempre que o medicamento do cliente for de uso contínuo, é criada uma assinatura do medicamento no sistema, informando o dia do mês de renovação da assinatura.

4. **Listagem de Assinaturas**
   - A partir do serviço de listagem de assinaturas, ao informar o email do cliente, é possível listar todos os medicamentos contínuos do cliente.

## Como Subir a Aplicação

### Pré-requisitos

- Java 21
- Maven
- Docker

### Passo a Passo

1. Clone o repositório da aplicação:
   ```bash
   git clone https://github.com/sidartaoss/pharmacy-management.git
   cd pharmacy-management
   ```

2. Instale as dependências do projeto:
   ```bash
   mvn clean install
   ```

3. Rode o comando do Docker para subir os serviços necessários:
   ```bash
   docker compose up -d
   ```

4. Rode a aplicação localmente com o comando:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível no endpoint padrão: [http://localhost:8080/api/actuator/health](http://localhost:8080/api/actuator/health)

## Testes

A aplicação conta com testes unitários para os casos de uso e testes de integração para a camada de API REST.

## Documentação da API

Acesse a documentação dos endpoints da aplicação através do Swagger: [http://localhost:8080/api/swagger-ui/index.html](http://localhost:8080/api/swagger-ui/index.html)

## Tecnologias Utilizadas

- **Java 21**: Linguagem de alto desempenho.
- **Spring Boot**: Framework para APIs e microserviços.
- **Maven**: Gerenciamento de dependências e automação de builds.
- **Docker**: Plataforma para criar e gerenciar contêineres.
- **RabbitMQ**: Sistema de mensageria para comunicação assíncrona e eventos.
- **MongoDB**: Banco de dados NoSQL, escalável e de alta performance.
- **Swagger**: Ferramenta para documentação e testes de APIs REST.
