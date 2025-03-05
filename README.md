# Pharmacy Management Application

## Requisitos Funcionais

1. **Operações CRUD de Clientes (Clients)**
    - Criar, Ler, Atualizar e Deletar clientes.

2. **Operações CRUD de Medicamentos (Medications)**
    - Criar, Ler, Atualizar e Deletar medicamentos.

3. **Serviço de Listagem de Assinaturas (Subscriptions)**
    - Listar todos os medicamentos contínuos de um cliente fornecendo o email do cliente.

## Requisitos Não-Funcionais

1. **Mensageria**
    - Utiliza RabbitMQ para mensageria, pois a aplicação é baseada em arquitetura orientada a eventos (event-driven architecture), garantindo escalabilidade e confiabilidade no tratamento de eventos de domínio.

2. **Banco de Dados**
    - Utiliza MongoDB para armazenamento de dados, proporcionando alta escalabilidade e flexibilidade no manuseio de grandes volumes de dados.

## Arquitetura

A aplicação segue os princípios da **Clean Architecture**, garantindo separação de responsabilidades, manutenibilidade e testabilidade.

## Arquitetura Orientada a Eventos

A aplicação utiliza **RabbitMQ** para tratar eventos de domínio, garantindo que eventos como a associação de medicamentos sejam processados de forma assíncrona e eficiente.

## Fluxo Principal da Aplicação

1. **Cadastro do Cliente**
    - Um novo cliente é registrado no sistema.

2. **Cadastro do Medicamento**
    - Um novo medicamento é registrado no sistema.

3. **Assinatura de Medicamento Contínuo**
    - Quando o medicamento de um cliente é marcado como de uso contínuo, uma assinatura é criada. Esta assinatura inclui o dia de renovação mensal, indicando quando o medicamento deve ser comprado novamente.

4. **Serviço de Listagem de Assinaturas**
    - Ao fornecer o email do cliente, o serviço lista todos os medicamentos contínuos assinados pelo cliente.

## Como Subir a Aplicação

### Pré-requisitos

- Java 21
- Maven
- Docker

### Passo a Passo

1. Clone o repositório da aplicação:
   ```sh
   git clone https://github.com/sidartaoss/pharmacy-management.git
   cd pharmacy-management
   ```

2. Instale as dependências do projeto:
   ```sh
   mvn clean install
   ```

3. Certifique-se de que RabbitMQ e MongoDB estão em execução:
   ```sh
   docker compose up -d
   ```

4. Rode a aplicação localmente com o comando:
   ```sh
   mvn spring-boot:run
   ```

A aplicação estará disponível no endpoint padrão: `http://localhost:8080/api/actuator/health`

## URL do Swagger

Acesse a documentação do Swagger para explorar os endpoints da aplicação:

```
http://localhost:8080/api/swagger-ui/index.html
```