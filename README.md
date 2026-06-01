# 🐾 VetAgenda API

> REST API para gerenciamento de agendamentos de clínicas veterinárias, construída com Java 21, Spring Boot 3.5 e PostgreSQL.

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat-square&logo=springboot)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)](https://www.docker.com/)
[![Status](https://img.shields.io/badge/Status-Concluído_(Backend)-brightgreen?style=flat-square)]()

---

## 📋 Sobre o Projeto

O **VetAgenda** é uma API REST para gerenciamento de agendamentos de clínicas veterinárias. O sistema permite controlar tutores (donos de pets), animais, veterinários e consultas (agendamentos) — com **validação de conflito de horário** e histórico completo por animal.

Desenvolvido como projeto de portfólio pessoal, com foco em boas práticas de backend: arquitetura em camadas, validações de negócio reais e tratamento centralizado de erros.

**Diferencial técnico:** a lógica de detecção de conflito de agendamento verifica sobreposição de horários por veterinário, retornando `HTTP 409 Conflict` com mensagem descritiva quando um horário já está ocupado.

---

## ⚙️ Stack

| Camada          | Tecnologia                       |
|-----------------|----------------------------------|
| Linguagem       | Java 21                          |
| Framework       | Spring Boot 3.5                  |
| Persistência    | Spring Data JPA + Hibernate      |
| Banco de dados  | PostgreSQL 15                    |
| Validação       | Bean Validation (Jakarta)        |
| Documentação    | Springdoc OpenAPI (Swagger UI)   |
| Boilerplate     | Lombok                           |
| Containerização | Docker + Docker Compose          |
| Build           | Maven                            |

---

## 🗃️ Modelo de Dados

```
Tutor (1) ──────────── (N) Animal
                              │
                             (N)
                              │
Veterinario (1) ──────── (N) Agendamento
```

**StatusAgendamento:** `AGENDADO` | `CONFIRMADO` | `CANCELADO` | `CONCLUIDO`

**Especialidade:** `CLINICO_GERAL` | `DERMATOLOGIA` | `ORTOPEDIA` | `CARDIOLOGIA` | `OFTALMOLOGIA`

--- 

## 🔌 Endpoints

### Tutores — `/tutores`

| Método | Rota          | Descrição                        |
|--------|---------------|----------------------------------|
| POST   | `/`           | Cadastrar novo tutor             |
| GET    | `/`           | Listar todos os tutores          |
| GET    | `/{id}`       | Buscar tutor por ID              |
| PUT    | `/{id}`       | Atualizar dados do tutor         |
| DELETE | `/{id}`       | Remover tutor                    |

### Animais — `/animais`

| Método | Rota              | Descrição                   |
|--------|-------------------|-----------------------------|
| POST   | `/`               | Cadastrar novo animal       |
| GET    | `/`               | Listar todos os animais     |
| GET    | `/{id}`           | Buscar animal por ID        |
| PUT    | `/{id}`           | Atualizar dados do animal   |
| DELETE | `/{id}`           | Remover animal              |
| GET    | `/{id}/historico` | Listar histórico do animal  |

### Veterinários — `/veterinarios`

| Método | Rota           | Descrição                      |
|--------|----------------|--------------------------------|
| POST   | `/`            | Cadastrar novo veterinário     |
| GET    | `/`            | Listar todos os veterinários   |
| GET    | `/{id}`        | Buscar veterinário por ID      |
| PUT    | `/{id}`        | Atualizar dados do veterinário |
| DELETE | `/{id}`        | Remover veterinário            |
| GET    | `/{id}/agenda` | Listar agenda do veterinário   |

### Agendamentos — `/agendamentos`

| Método | Rota               | Descrição                                      |
|--------|--------------------|------------------------------------------------|
| POST   | `/`                | Criar agendamento (valida conflito de horário) |
| GET    | `/`                | Listar todos os agendamentos                   |
| GET    | `/{id}`            | Buscar agendamento por ID                      |
| GET    | `?data=dd/MM/yyyy` | Filtrar agendamentos por dia                   |
| PATCH  | `/{id}/agendar`    | Atualizar status do agendamento para AGENDADO  |
| PATCH  | `/{id}/concluir`   | Atualizar status do agendamento para CONCLUIDO |
| PATCH  | `/{id}/cancelar`   | Atualizar status do agendamento para CANCELAR  |
| DELETE | `/{id}`            | Remover agendamento                            |

> **Regra de negócio:** ao criar ou atualizar um agendamento, o sistema verifica se o veterinário já possui uma consulta no mesmo horário. Em caso positivo, retorna `409 Conflict`.

---

## 🚀 Como Rodar

### Pré-requisitos

- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/)
- Java 21+ (para rodar sem Docker)
- Maven 3.9+ (para rodar sem Docker)

### Com Docker (recomendado)

```bash
# 1. Clone o repositório
git clone https://github.com/mendesx5/VetAgenda-api.git
cd VetAgenda-api

# 2. Configure as variáveis de ambiente
cp .env.example .env
# Edite o .env com suas configurações

# 3. Suba os containers
docker compose up -d

# A API estará disponível em: http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
```

### Sem Docker (local)

```bash
# 1. Clone o repositório
git clone https://github.com/mendesx5/VetAgenda-api.git
cd VetAgenda-api

# 2. Configure as variáveis de ambiente
cp .env.example .env
# Preencha com os dados do seu PostgreSQL local

# 3. Execute a aplicação
./mvnw spring-boot:run
```

---

## 🔧 Variáveis de Ambiente

Copie `.env.example` para `.env` e preencha:

```env
DB_HOST=localhost
DB_PORT=5432
DB_NAME=vetagenda
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
```

---

## 📖 Documentação Interativa

Com a aplicação rodando, acesse o Swagger UI:

```
http://localhost:8080/swagger-ui.html
```

Todos os endpoints estão documentados com exemplos de request/response e códigos de retorno.

---

## 🏗️ Estrutura do Projeto

```
src/
└── main/
    └── java/com/vetagenda/
        ├── controller/            # Controllers REST
        ├── service/               # Regras de negócio
        ├── repository/            # Repositórios JPA
        ├── domain/
        │   ├── dto/
        │   │   ├── request/       # DTOs de entrada
        │   │   ├── response/      # DTOs de saída
        │   ├── entity/            # Entidades JPA
        │   ├── enums/             # StatusAgendamento, Especialidade
        └── exception/             # Tratamento global de erros
```

---

## ⚠️ Tratamento de Erros

A API retorna respostas de erro padronizadas:

| Código | Situação                                          |
|--------|---------------------------------------------------|
| 400    | Dados inválidos na requisição                     |
| 404    | Recurso não encontrado                            |
| 409    | Conflito de horário no agendamento                |
| 500    | Erro interno do servidor                          |

Exemplo de resposta de erro `409`:
```json
{
  "status": 409,
  "error": "Conflict",
  "message": "O veterinário já possui um agendamento neste horário.",
  "timestamp": "2025-06-01T10:30:00"
}
```

---

## 👨‍💻 Autor

**Gabriel Mendes**

[![GitHub](https://img.shields.io/badge/GitHub-mendesx5-181717?style=flat-square&logo=github)](https://github.com/mendesx5)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-gabrielmendes06-0A66C2?style=flat-square&logo=linkedin)](https://linkedin.com/in/gabrielmendes06)