# 🐾 VetAgenda API

> REST API for veterinary clinic scheduling built with Java 21, Spring Boot 3.5 and PostgreSQL.

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-4169E1?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)
![Status](https://img.shields.io/badge/Status-Em_desenvolvimento-yellow?style=flat-square)

---

> 🚧 **Projeto em desenvolvimento ativo.** A documentação completa será publicada ao final da construção.

---

## 📋 Sobre o Projeto

O **VetAgenda** é uma API REST para gerenciamento de agendamentos de clínicas veterinárias. O sistema permite controlar tutores (donos de pets), animais, veterinários e consultas — com validação de conflito de horário e histórico completo por animal.

Desenvolvido como projeto de portfólio pessoal, com foco em boas práticas de backend: arquitetura em camadas, validações de negócio reais e tratamento centralizado de erros.

---

## ⚙️ Stack

| Camada | Tecnologia |
|---|---|
| Linguagem | Java 21 |
| Framework | Spring Boot 3.5.x |
| Persistência | Spring Data JPA + Hibernate |
| Banco de dados | PostgreSQL 15 |
| Validação | Bean Validation (Jakarta) |
| Documentação | Springdoc OpenAPI (Swagger UI) |
| Boilerplate | Lombok |
| Testes | JUnit 5 + Mockito |
| Containerização | Docker + Docker Compose |
| Build | Maven |

---

## 📌 Progresso

- [x] Entidades e relacionamentos JPA
- [x] Enums (`StatusAgendamento`, `Especialidade`)
- [x] DTOs de Request e Response
- [x] Repositórios com queries customizadas
- [x] Services (Agendamento pendente)
- [x] Controllers REST (Agendamento pendente)
- [ ] Tratamento global de exceções
- [ ] Documentação Swagger
- [ ] Testes unitários
- [ ] Docker Compose

---

## 🗃️ Modelo de Dados

```
Tutor (1) ──────────────────── (N) Animal
                                      │
                                     (N)
                                      │
Veterinario (1) ──────────── (N) Agendamento
```

**StatusAgendamento:** `AGENDADO` | `CONFIRMADO` | `CANCELADO` | `CONCLUIDO`

**Especialidade:** `CLINICO_GERAL` | `DERMATOLOGIA` | `ORTOPEDIA` | `CARDIOLOGIA` | `OFTALMOLOGIA`

---

## 👨‍💻 Autor

**Gabriel Mendes**

[![GitHub](https://img.shields.io/badge/GitHub-mendesx5-181717?style=flat-square&logo=github)](https://github.com/mendesx5)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-gabrielmendes06-0A66C2?style=flat-square&logo=linkedin)](https://linkedin.com/in/gabrielmendes06)