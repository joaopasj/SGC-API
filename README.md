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
- Git

---

### Clonando o Repositório

Clone o projeto para sua máquina:

```bash
git clone https://github.com/joaopasj/SGC-API.git
```

Acesse a pasta do projeto:

```bash
cd SGC-API
```

---

### Executando o Backend

Acesse a pasta do backend:

```bash
cd backend
```

Execute a aplicação:

```bash
mvn spring-boot:run
```

Ou execute diretamente a classe principal:

```java
SgcApiApplication
```

Após iniciar, a documentação Swagger poderá ser acessada em:

```text
http://localhost:8080/swagger-ui.html
```

---

### Executando o Frontend

Abra outro terminal e acesse a pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute a aplicação:

```bash
npm run dev
```

A aplicação estará disponível em:

```text
http://localhost:5173
```

---

### Utilização

Com o backend e frontend em execução, o sistema permitirá:

- Cadastrar usuários;
- Editar usuários;
- Excluir usuários;
- Cadastrar chamados;
- Editar chamados;
- Excluir chamados;
- Filtrar chamados por título;
- Filtrar chamados por status;
- Filtrar chamados por prioridade;
- Visualizar estatísticas no Dashboard.

---

### Observação

O projeto utiliza o banco de dados H2 em memória. Dessa forma, os dados cadastrados serão perdidos sempre que a aplicação backend for encerrada.
