# Solar Hub

API REST para gestão e monitoramento de instalações de energia solar. Em desenvolvimento.

> **Status:** em desenvolvimento. Fase 1 concluída (base do back-end, cadastro de usuários e autenticação). Próxima fase: CRUD de instalações e painéis.

🇬🇧 [Read in English](README.md)

## Tecnologias

- Java 25 e Spring Boot
- Spring Security com autenticação JWT
- Spring Data JPA / Hibernate
- PostgreSQL
- Flyway (migrações do banco)
- Docker Compose (banco de dados local)

## O que já está implementado

- Cadastro de usuários com validação dos dados
- Login com geração de token de acesso JWT
- Endpoint protegido que retorna o usuário autenticado
- Esquema do banco versionado com Flyway (o Hibernate apenas valida)

## Como rodar

**Pré-requisitos:** Java 25 e Docker.

1. Clone o repositório:

   ```bash
   git clone https://github.com/fgsilvadev/solar-hub.git
   cd solar-hub
   ```

2. Suba o banco de dados:

   ```bash
   docker compose up -d
   ```

3. Inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

   No Windows: `mvnw.cmd spring-boot:run`

A API ficará disponível em `http://localhost:8080`.

### Variáveis de ambiente (opcionais)

Os valores padrão servem apenas para desenvolvimento local. Em qualquer outro ambiente, defina os seus:

| Variável | Descrição | Padrão (dev) |
|---|---|---|
| `DB_HOST` | Host do PostgreSQL | `localhost` |
| `DB_PORT` | Porta do PostgreSQL | `5432` |
| `DB_NAME` | Nome do banco | `solarhub` |
| `DB_USER` | Usuário do banco | `solarhub` |
| `DB_PASSWORD` | Senha do banco | `solarhub` |
| `JWT_SECRET` | Chave de assinatura do JWT (mínimo de 32 caracteres) | valor de desenvolvimento |

## Endpoints da API

| Método | Caminho | Autenticação | Descrição |
|---|---|---|---|
| `POST` | `/api/auth/register` | Não | Cadastra um novo usuário |
| `POST` | `/api/auth/login` | Não | Faz login e retorna um token de acesso |
| `GET` | `/api/users/me` | Sim | Retorna o usuário autenticado |

## Exemplos de uso

**Cadastrar usuário**

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{ "name": "Nome Sobrenome", "email": "nome@exemplo.com", "password": "altereme1234" }' \
  -w "\n"
```

Exemplo de resposta:

```json
{
   "id":2,
   "name":"Nome Sobrenome",
   "email":"nome@exemplo.com",
   "role":"CUSTOMER",
   "createdAt":"2026-11-07T18:34:19.976243643Z"
}
```

**Fazer login**

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{ "email": "nome@exemplo.com", "password": "altereme1234" }' \
  -w "\n"
```

Exemplo de resposta:

```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```

**Consultar o usuário autenticado**

Use o `accessToken` retornado no login:

```bash
curl http://localhost:8080/api/users/me \
  -H "Authorization: Bearer SEU_ACCESS_TOKEN" \
  -w "\n"
```

Exemplo de resposta:

```json
{
   "id":2,
   "name":"Nome Sobrenome",
   "email":"nome@exemplo.com",
   "role":"CUSTOMER",
   "createdAt":"2026-11-07T18:34:19.976244Z"
}
```

## Roadmap

- [x] Fase 1: base do back-end, usuários e autenticação JWT
- [ ] Fase 2: CRUD de instalações e painéis, por usuário autenticado
- [ ] Registro de leituras de geração
- [ ] Comparação entre geração esperada e real, com alertas de baixo desempenho
- [ ] Testes automatizados, documentação da API (OpenAPI) e deploy

## Autor

Felipe Gomes da Silva · [LinkedIn](https://www.linkedin.com/in/fgsilvadev/)