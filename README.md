# 🐾 VetAgenda API

> API REST para gerenciamento de clínicas veterinárias — construída com Java 21, Spring Boot 3.5, Spring Security + JWT e PostgreSQL.

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?style=flat-square&logo=springsecurity)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)](https://www.docker.com/)
[![Status](https://img.shields.io/badge/Status-v1.1-brightgreen?style=flat-square)]()

---

## 📋 Sobre o Projeto

O **VetAgenda** é uma API REST completa para gestão de clínicas veterinárias. O sistema gerencia tutores (donos de pets), animais, veterinários, agendamentos e usuários do sistema — com autenticação JWT, controle de acesso por roles e regras de negócio reais.

Desenvolvido como projeto de portfólio pessoal, com foco em boas práticas de backend: arquitetura em camadas, segurança stateless, validações de domínio e tratamento centralizado de erros.

**Frontend:** [github.com/mendesx5/VetAgenda-Frontend](https://github.com/mendesx5/VetAgenda-Frontend)

---

## ⚙️ Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.5.14 |
| Segurança | Spring Security + JWT (auth0 java-jwt 4.4.0) |
| Persistência | Spring Data JPA + Hibernate |
| Banco de dados | PostgreSQL 15 |
| Banco para testes | H2 (in-memory, `@ActiveProfiles("test")`) |
| Validação | Bean Validation (Jakarta) |
| Documentação | Springdoc OpenAPI 2.8 (Swagger UI) |
| Boilerplate | Lombok |
| Configuração | spring-dotenv 4.0.0 |
| Containerização | Docker + Docker Compose |
| Build | Maven |

---

## 🔐 Segurança e Autenticação

A API utiliza autenticação **stateless com JWT**. Todos os endpoints — exceto `/auth/login` e `/auth/register` — exigem um token Bearer válido no header `Authorization`.

### Roles disponíveis

| Role | Acesso |
|---|---|
| `ADMIN` | Acesso total — gerencia usuários, cadastros e agendamentos |
| `VETERINARIO` | Acesso à agenda, animais, tutores e agendamentos |
| `RECEPCIONISTA` | Acesso aos agendamentos e cadastros básicos |

### Fluxo de autenticação

```
POST /auth/login  →  { token, role }  →  Authorization: Bearer <token>
```

O token expira em **2 horas** (fuso `America/Sao_Paulo`). Em caso de token inválido ou expirado, a API retorna `401 Unauthorized`.

---

## 🗃️ Modelo de Dados

```
UsuarioEntity
├── login (unique)
├── password (BCrypt)
└── role (ADMIN | VETERINARIO | RECEPCIONISTA)

TutorEntity (1) ──────── (N) AnimalEntity
                                   │
                                  (N)
                                   │
VeterinarioEntity (1) ──── (N) AgendamentoEntity
```

**StatusAgendamento:** `AGENDADO` | `CONFIRMADO` | `CONCLUIDO` | `CANCELADO`

**Especialidade:** `CLINICO_GERAL` | `DERMATOLOGIA` | `ORTOPEDIA` | `CARDIOLOGIA` | `OFTAMOLOGIA`

---

## 🔌 Endpoints

### Auth — `/auth`

| Método | Rota | Acesso | Descrição |
|---|---|---|---|
| POST | `/auth/login` | Público | Autenticar → retorna `{ token, role }` |
| POST | `/auth/register` | Público | Cadastrar novo usuário do sistema |

### Tutores — `/tutores`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/tutores` | Cadastrar novo tutor |
| GET | `/tutores` | Listar todos os tutores |
| GET | `/tutores/{id}` | Buscar tutor por ID |
| PUT | `/tutores/{id}` | Atualizar dados do tutor |
| DELETE | `/tutores/{id}` | Remover tutor |

### Animais — `/animais`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/animais` | Cadastrar novo animal |
| GET | `/animais` | Listar todos os animais |
| GET | `/animais/{id}` | Buscar animal por ID |
| PUT | `/animais/{id}` | Atualizar dados do animal |
| DELETE | `/animais/{id}` | Remover animal |
| GET | `/animais/{id}/historico` | Histórico de consultas do animal |

### Veterinários — `/veterinarios`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/veterinarios` | Cadastrar novo veterinário |
| GET | `/veterinarios` | Listar todos os veterinários |
| GET | `/veterinarios/{id}` | Buscar veterinário por ID |
| PUT | `/veterinarios/{id}` | Atualizar dados do veterinário |
| DELETE | `/veterinarios/{id}` | Remover veterinário |
| GET | `/veterinarios/{id}/agenda` | Listar agenda do veterinário |

### Agendamentos — `/agendamentos`

| Método | Rota | Descrição |
|---|---|---|
| POST | `/agendamentos` | Criar agendamento (valida conflito de horário) |
| GET | `/agendamentos` | Listar todos (aceita `?data=dd/MM/yyyy`) |
| GET | `/agendamentos/{id}` | Buscar por ID |
| DELETE | `/agendamentos/{id}` | Remover agendamento |
| PATCH | `/agendamentos/{id}/agendar` | Mudar status → `AGENDADO` |
| PATCH | `/agendamentos/{id}/confirmar` | Mudar status → `CONFIRMADO` |
| PATCH | `/agendamentos/{id}/concluir` | Mudar status → `CONCLUIDO` |
| PATCH | `/agendamentos/{id}/cancelar` | Mudar status → `CANCELADO` |

> **Regra de negócio:** ao criar um agendamento, o sistema verifica se o veterinário já possui uma consulta no mesmo `dataHora` exato. Em caso positivo, retorna `409 Conflict`.

---

## ⚠️ Tratamento de Erros

Todos os erros retornam um body padronizado via `GlobalExceptionHandler`:

| Código | Situação |
|---|---|
| `400` | Campos inválidos ou ausentes (`@Valid`) — retorna lista de campos com erro |
| `401` | Token ausente, inválido ou expirado |
| `404` | Recurso não encontrado |
| `409` | Conflito de horário no agendamento |

```json
{
  "status": 409,
  "erro": "Conflito de horário",
  "mensagem": "Este horário de atendimento já está preenchido!",
  "timestamp": "2026-06-09T10:30:00"
}
```

---

## 🚀 Como Rodar

### Opção 1 — Docker (recomendado)

> Necessário: [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)

```bash
# 1. Clone o repositório
git clone https://github.com/mendesx5/VetAgenda-api.git
cd VetAgenda-api

# 2. Configure as variáveis de ambiente
cp .env.example .env
# Edite o .env com suas configurações

# 3. Suba os containers (API + PostgreSQL)
docker compose up -d
```

A API estará disponível em `http://localhost:8080`

### Opção 2 — Local (IDE ou Maven)

> Necessário: Java 21, Maven e PostgreSQL rodando localmente

```bash
git clone https://github.com/mendesx5/VetAgenda-api.git
cd VetAgenda-api
cp .env.example .env
# Configure DB_HOST=localhost no .env
./mvnw spring-boot:run
```

---

## 🔧 Variáveis de Ambiente

Copie `.env.example` para `.env` e preencha:

```env
# "localhost" para rodar localmente, "db" para rodar via Docker Compose
DB_HOST=localhost
DB_PORT=5432
DB_NAME=vetagenda-db
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
JWT_SECRET=sua-chave-secreta-aqui
```

| `DB_HOST` | Quando usar |
|---|---|
| `localhost` | Rodando pela IDE ou `mvnw spring-boot:run` |
| `db` | Rodando via `docker compose up` |

---

## 📖 Documentação Interativa

Com a aplicação rodando, acesse o Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

Todos os endpoints estão documentados com descrições, parâmetros e exemplos de resposta.

---

## 🏗️ Estrutura do Projeto

```
src/
└── main/java/com/vetagenda/vetagenda_api/
    ├── controller/          # Controllers REST (Auth, Agendamento, Animal, Tutor, Veterinario)
    ├── service/             # Regras de negócio e lógica de domínio
    ├── repository/          # Repositórios JPA com queries customizadas
    ├── domain/
    │   ├── dto/
    │   │   ├── request/     # DTOs de entrada (com validações @NotBlank, @NotNull)
    │   │   └── response/    # DTOs de saída
    │   ├── entity/          # Entidades JPA (Agendamento, Animal, Tutor, Veterinario, Usuario)
    │   └── enums/           # StatusAgendamento, Especialidade, UserRole
    ├── exception/           # GlobalExceptionHandler + exceções customizadas
    └── infra/security/      # SecurityConfigurations, SecurityFilter, TokenService
```

---

## 👨‍💻 Autor

**Gabriel Mendes**

[![GitHub](https://img.shields.io/badge/GitHub-mendesx5-181717?style=flat-square&logo=github)](https://github.com/mendesx5)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-gabrielmendes06-0A66C2?style=flat-square&logo=linkedin)](https://linkedin.com/in/gabrielmendes06)