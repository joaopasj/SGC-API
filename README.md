# Sistema de Gerenciamento de Chamados (SGC)

Este projeto consiste em um sistema de gerenciamento de chamados desenvolvido com Java, Spring Boot, React e TypeScript, permitindo o controle de usuários e chamados de suporte através de uma interface web.

O sistema permite que o usuário:

- Cadastrar um novo usuário;
- Editar usuários cadastrados;
- Remover usuários;
- Listar todos os usuários cadastrados;
- Cadastrar novos chamados;
- Editar chamados;
- Remover chamados;
- Listar todos os chamados cadastrados;
- Buscar chamados pelo título;
- Filtrar chamados por status;
- Filtrar chamados por prioridade;
- Visualizar estatísticas dos chamados através de um dashboard.

## Tecnologias

### Backend

- Java 26
- Spring Boot
- Spring Web
- Spring Data JPA
- Bean Validation
- OpenAPI / Swagger
- Banco de Dados H2
- Maven

### Frontend

- React
- TypeScript
- React Router DOM
- Axios
- Vite

### Principais Classes e Componentes

#### Backend

- UserController
- TicketController
- UserService
- TicketService
- UserRepository
- TicketRepository
- GlobalExceptionHandler

#### Frontend

- Dashboard
- Users
- CreateUser
- EditUser
- Tickets
- CreateTicket
- EditTicket

## Como Executar o Projeto

### Pré-requisitos

Antes de iniciar, é necessário ter instalado:

- Java 26
- Maven
- Node.js
- NPM

---

### Executando o Backend

1. Abra o projeto backend.

2. Execute a aplicação utilizando o Maven:

```bash
mvn spring-boot:run
```

Ou execute diretamente a classe principal:

```java
SgcApiApplication
```

3. A documentação Swagger poderá ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

### Executando o Frontend

1. Abra o terminal na pasta do frontend.

2. Instale as dependências:

```bash
npm install
```

3. Execute a aplicação:

```bash
npm run dev
```

4. Acesse a aplicação pelo navegador:

```text
http://localhost:5173
```

---

### Utilização

Após iniciar o backend e o frontend:

- Cadastre usuários;
- Crie chamados vinculados aos usuários cadastrados;
- Edite ou remova usuários e chamados;
- Utilize os filtros de busca por título, status e prioridade;
- Visualize as estatísticas na página Dashboard.

> O banco de dados H2 é executado em memória, portanto os dados serão reiniciados sempre que a aplicação backend for encerrada.
