# Cadastro de Funcionários

Sistema completo em Java com Spring Boot para gestão de funcionários, tarefas e autenticação de usuários. O projeto combina API REST, interface web em Thymeleaf e segurança com JWT, oferecendo cadastro, listagem, edição, exclusão e login funcional.

## Visão geral

Este projeto foi desenvolvido para registrar funcionários e associá-los a tarefas, além de permitir acesso seguro à aplicação por meio de autenticação. A estrutura inclui:

- API REST para pessoas e tarefas
- Interface web em Thymeleaf para uso direto no navegador
- Autenticação de usuários com Spring Security + JWT
- Persistência com PostgreSQL e migrações via Flyway
- Documentação automática com Swagger/OpenAPI

## Funcionalidades

### Usuários e autenticação
- Cadastro de usuário
- Login com e-mail e senha
- Geração de token JWT
- Rotas públicas para login, cadastro e documentação

### Gestão de funcionários
- Cadastro de funcionários
- Listagem geral
- Busca por ID
- Atualização de dados
- Exclusão

### Gestão de tarefas
- Cadastro de tarefas
- Listagem geral
- Busca por ID
- Atualização
- Exclusão
- Associação com funcionários

### Interface web
- Página de login
- Página de cadastro
- Listagem de funcionários
- Detalhes de funcionário
- Formulário de edição
- Listagem de tarefas e páginas associadas

## Stack tecnológica

| Tecnologia | Uso |
|-----------|-----|
| Java 17 | Linguagem principal |
| Spring Boot 4.0.7 | Framework principal |
| Spring Web MVC | API e controllers |
| Spring Data JPA | Persistência e ORM |
| Spring Security | Autenticação e autorização |
| JWT (java-jwt) | Geração e validação de tokens |
| Thymeleaf | Views HTML |
| PostgreSQL | Banco de dados principal |
| Flyway | Migrações de banco |
| Lombok | Redução de boilerplate |
| OpenAPI/Swagger | Documentação da API |
| Maven | Build e gerenciamento de dependências |

## Estrutura do projeto

```text
src/
├── main/
│   ├── java/dev/dulciobernardo7/CadastroDeFuncionarios/
│   │   ├── Config/
│   │   │   ├── SecurityConfig.java
│   │   │   ├── SecurityFilter.java
│   │   │   ├── TokenService.java
│   │   │   └── SwaggerConfig.java
│   │   ├── Controller/
│   │   │   ├── AuthController.java
│   │   │   ├── AuthViewController.java
│   │   │   ├── PessoaController.java
│   │   │   ├── PessoaControllerUI.java
│   │   │   ├── TarefasController.java
│   │   │   └── TarefasControllerUI.java
│   │   ├── Controller/DTO/
│   │   │   ├── LoginDTO.java
│   │   │   ├── LoginToken.java
│   │   │   ├── PessoaDTO.java
│   │   │   ├── TarefasDTO.java
│   │   │   └── UserDTO.java
│   │   ├── Entity/
│   │   │   ├── PessoaModel.java
│   │   │   ├── TarefasModel.java
│   │   │   └── User.java
│   │   ├── Exception/
│   │   │   └── UsenameOrPasswordInvalidExceptions.java
│   │   ├── Repository/
│   │   │   ├── PessoasRepository.java
│   │   │   ├── TarefasRepository.java
│   │   │   └── UserRepository.java
│   │   ├── Service/
│   │   │   ├── AuthService.java
│   │   │   ├── PessoaService.java
│   │   │   ├── TarefasService.java
│   │   │   └── UserService.java
│   │   ├── mapper/
│   │   │   ├── PessoaMapper.java
│   │   │   ├── TarefasMapper.java
│   │   │   └── UserMapper.java
│   │   └── CadastroDeFuncionariosApplication.java
│   └── resources/
│       ├── application.yaml
│       ├── db/migration/
│       │   ├── V1__create_table_tarefas.sql
│       │   ├── V2__create_table_pessoas.sql
│       │   └── V3__create_table_users.sql
│       ├── static/
│       │   └── css/auth.css
│       └── templates/
│           ├── adicionarPessoas.html
│           ├── adicionarTarefas.html
│           ├── alterarPessoas.html
│           ├── detalhesPessoas.html
│           ├── detalhesTarefas.html
│           ├── listaPessoas.html
│           ├── listaTarefas.html
│           ├── login.html
│           └── register.html
```

## Requisitos

- Java 17+
- Maven 3.6+
- PostgreSQL 12+
- Git

## Configuração do ambiente

O projeto usa o arquivo `src/main/resources/application.yaml` para configurar a conexão com o banco e a segurança.

Exemplo de configuração:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/cadastrodepessoas
    username: postgres
    password: 1234
    driver-class-name: org.postgresql.Driver

  flyway:
    enabled: true

cadastrodefuncionario:
  security:
    secret: palava-secreta
```

Crie o banco `cadastrodepessoas` no PostgreSQL antes de iniciar a aplicação.

## Como executar

### 1) Clone o repositório

```bash
git clone https://github.com/dulciobernardo77/CadastroDeFuncionarios.git
cd CadastroDeFuncionarios
```

### 2) Compile e baixe dependências

```bash
mvn clean install
```

### 3) Inicie a aplicação

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em:

- Frontend web: `http://localhost:8080/login`
- Swagger UI: `http://localhost:8080/swagger/index.html`
- OpenAPI docs: `http://localhost:8080/api/api-docs`

## Endpoints principais

### Autenticação

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/cadastrodefuncionarios/auth/register` | Cadastro de usuário |
| POST | `/cadastrodefuncionarios/auth/login` | Login e geração de token |

### Funcionários

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/pessoas/boavindas` | Mensagem inicial |
| POST | `/pessoas/cadastrar` | Cadastrar pessoa/funcionário |
| GET | `/pessoas/lista` | Listar todos |
| GET | `/pessoas/lista/{id}` | Buscar por ID |
| PATCH | `/pessoas/alterar/{id}` | Atualizar |
| DELETE | `/pessoas/deletar/{id}` | Excluir |

### Tarefas

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/tarefas/cadastrar` | Cadastrar tarefa |
| GET | `/tarefas/lista` | Listar todas |
| GET | `/tarefas/lista/{id}` | Buscar por ID |
| PATCH | `/tarefas/altera/{id}` | Atualizar |
| DELETE | `/tarefas/Deletar/{id}` | Excluir |

### Interface web

| Rota | Descrição |
|------|-----------|
| `/` | Redireciona para `/login` |
| `/login` | Página de autenticação |
| `/register` | Página de cadastro de usuário |
| `/pessoas/ui/lista` | Lista de funcionários |
| `/pessoas/ui/adicionar` | Formulário de cadastro |
| `/pessoas/ui/lista/{id}` | Detalhes do funcionário |
| `/tarefas/ui/lista` | Lista de tarefas |
| `/tarefas/ui/adicionar` | Formulário de tarefa |

## Modelos principais

### User
- id
- nome
- email
- senha

### Pessoa
- id
- nome
- idade
- nacionalidade
- bi
- sexo
- email
- telefone
- nivel
- imagemUrl
- tarefa

### Tarefa
- id
- nomeDaTarefa
- dificuldade
- pessoas

## Segurança

A aplicação utiliza Spring Security com autenticação por usuário/senha e geração de token JWT para acesso às rotas protegidas. As rotas públicas incluem:

- `/`
- `/login`
- `/register`
- `/css/**`
- `/cadastrodefuncionarios/auth/**`
- `/api/api-docs/**`
- `/swagger/**`

## Migrações

As alterações de banco são gerenciadas automaticamente pelo Flyway. Os scripts ficam em:

```text
src/main/resources/db/migration/
```

## Roadmap

- [ ] Melhorar fluxo de autenticação com refresh token
- [ ] Implementar paginação nas listagens
- [ ] Adicionar filtros e buscas avançadas
- [ ] Melhorar validações e mensagens de erro
- [ ] Cobrir com testes unitários e de integração
- [ ] Adicionar containerização com Docker

## Contribuição

Contribuições são bem-vindas. Para colaborar:

1. Faça um fork do projeto
2. Crie uma branch para a sua funcionalidade
3. Commit suas alterações
4. Abra um pull request

## Licença

Este projeto está em desenvolvimento e pode ser usado para fins educacionais e pessoais.

## Autor

Dulcio Bernardo

- GitHub: [@dulciobernardo77](https://github.com/dulciobernardo77)

---

Última atualização: 2026-10-01
