# High School Management System - Java Spring Boot Migration

Sistema de gerenciamento de atividades extracurriculares da Escola Mergington High migrado para Java com Spring Boot 3, seguindo os princípios da Clean Architecture.

## 🎯 Tecnologias Utilizadas

- **Java 21** com recursos modernos e melhorias de performance
- **Spring Boot 3.2.1**
- **MongoDB** para persistência de dados
- **Clean Architecture** com separação clara de responsabilidades
- **Testes unitários** com JUnit 5 Jupiter e Mockito
- **JaCoCo** para cobertura de testes
- **Documentação C4Model** para arquitetura
- **Diagramas PlantUML** para visualização

## 🏗️ Arquitetura Clean Architecture

O projeto segue os princípios da Clean Architecture com as seguintes camadas:

### Domain Layer (Camada de Domínio)
- **Entities**: `Activity` - Entidade de negócio com regras e validações
- **Repository Interfaces**: Contratos para acesso a dados
- **Use Cases**: Lógica de negócio pura
  - `GetActivitiesUseCase` - Buscar atividades
  - `SignupForActivityUseCase` - Inscrever estudante em atividade

### Application Layer (Camada de Aplicação)
- **Services**: `ActivityService` - Orquestração de casos de uso
- **DTOs**: Objetos de transferência de dados para API

### Infrastructure Layer (Camada de Infraestrutura)
- **Web Controllers**: `ActivityController` - Endpoints REST
- **Repository Implementations**: Implementações concretas para MongoDB
- **Configuration**: Configurações do Spring Boot e injeção de dependências

## 📁 Estrutura do Projeto

```
src/
├── main/java/com/mergington/highschool/
│   ├── domain/
│   │   ├── entity/          # Entidades de domínio
│   │   ├── repository/      # Interfaces de repositório
│   │   └── usecase/         # Casos de uso
│   ├── application/
│   │   ├── dto/             # Data Transfer Objects
│   │   └── service/         # Serviços de aplicação
│   └── infrastructure/
│       ├── config/          # Configurações
│       ├── persistence/     # Implementações de repositório
│       └── web/             # Controllers REST
├── test/java/               # Testes unitários espelhando a estrutura
└── resources/
    ├── static/              # Arquivos estáticos (HTML, CSS, JS)
    └── application.properties
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- Maven 3.6+
- MongoDB (local ou Docker)

### Executar MongoDB com Docker
```bash
docker run -d -p 27017:27017 --name mongodb mongo:6.0
```

### Compilar e Executar
```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Executar a aplicação
mvn spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

## 🧪 Testes e Cobertura

### Executar Testes
```bash
mvn test
```

### Relatório de Cobertura JaCoCo
```bash
mvn jacoco:report
```

O relatório será gerado em: `target/site/jacoco/index.html`

### Estrutura de Testes
- **Testes de Unidade**: Para todas as camadas (Domain, Application, Infrastructure)
- **Testes de Integração**: Para repositórios MongoDB com Testcontainers
- **Testes de Controller**: Com MockMvc para endpoints REST

## 📡 API Endpoints

### GET /api/activities
Retorna todas as atividades disponíveis.

**Resposta:**
```json
[
  {
    "id": "...",
    "name": "Clube de Xadrez",
    "description": "Aprenda estratégias e participe de torneios de xadrez",
    "schedule": "Sextas, 15h30 - 17h",
    "maxParticipants": 12,
    "participants": ["michael@mergington.edu", "daniel@mergington.edu"],
    "availableSpots": 10
  }
]
```

### POST /api/activities/{activityName}/signup
Inscreve um estudante em uma atividade.

**Request Body:**
```json
{
  "email": "student@mergington.edu"
}
```

**Resposta:**
```json
{
  "message": "student@mergington.edu inscrito(a) em Clube de Xadrez com sucesso"
}
```

## 📊 Funcionalidades

- ✅ Listar todas as atividades extracurriculares
- ✅ Visualizar detalhes das atividades (descrição, horário, vagas disponíveis)
- ✅ Inscrever estudantes em atividades
- ✅ Validação de email
- ✅ Verificação de capacidade máxima
- ✅ Prevenção de inscrições duplicadas
- ✅ Interface web responsiva

## 🎨 Frontend

O frontend utiliza HTML, CSS e JavaScript vanilla, mantendo a mesma interface da versão Python original, mas adaptada para consumir a nova API REST.

## 📚 Documentação

- **C4 Model**: Documentação arquitetural em `docs/c4model/`
- **Diagramas PlantUML**: Diagramas de arquitetura em `docs/diagrams/`
  - Diagrama de Contexto do Sistema
  - Diagrama de Container
  - Diagrama de Componentes
  - Diagrama de Classes

## 🏆 Qualidade do Código

- **Clean Architecture**: Separação clara de responsabilidades
- **SOLID Principles**: Aplicados em todo o código
- **Dependency Inversion**: Dependências apontam para abstrações
- **Testabilidade**: Cada camada pode ser testada isoladamente
- **Cobertura de Testes**: Objetivo de 80%+ de cobertura

## 🔄 Migração da Versão Python

Esta aplicação Java substitui completamente a versão Python FastAPI original, mantendo:
- Mesma funcionalidade
- Mesma interface de usuário
- Mesmos endpoints (com prefixo /api)
- Mesma estrutura de dados
- Inicialização automática com dados de exemplo

## 🤝 Contribuição

1. Faça fork do projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para mais detalhes.