# Dev Container - Java 21 Spring Boot Development

Este projeto inclui uma configuração **OTIMIZADA** do Dev Container com MongoDB pré-configurado e inicialização automática.

## 🚀 Recursos Incluídos

### **☕ Ambiente de Desenvolvimento**

- **Java 21** (OpenJDK)
- **Maven** para gerenciamento de dependências
- **MongoDB 6.0** pré-configurado com Docker Compose
- **Mongo Express** (interface web admin na porta 8081)

### **🔌 Extensões VS Code Pré-instaladas**

#### **Java & Spring Boot**

- Extension Pack for Java (Microsoft)
- Spring Boot Tools (VMware)
- Spring Boot Dashboard
- Java Test Runner

#### **Database & MongoDB**

- MongoDB for VS Code

#### **Arquitetura & Documentação**

- PlantUML
- C4 DSL Extension

#### **DevOps & Containers**

- Docker Extension
- Dev Containers

#### **Qualidade & Testes**

- SonarLint

#### **Git & Controle de Versão**

- GitLens
- GitHub Pull Requests

#### **Produtividade**

- Material Icon Theme
- REST Client
- Thunder Client

#### **Configuração & Formato**

- YAML Support
- XML Support

### **⚙️ Otimizações Implementadas**

- **MongoDB Pré-carregado**: Imagem baixada automaticamente
- **Docker Compose**: Orquestração de serviços otimizada
- **Inicialização Automática**: MongoDB + dados de exemplo
- **Persistência de Dados**: Volume MongoDB preservado
- **Usuários Pré-configurados**: Admin e dev já criados
- **Format on Save**: Ativado
- **Auto Import Organization**: Configurado
- **Java Code Style**: Google Java Style Guide
- **PlantUML**: Configurado com servidor online
- **Git**: Auto-fetch habilitado

## 📋 Como Usar

### **1. Pré-requisitos**

- VS Code com extensão "Dev Containers" instalada
- Docker Desktop rodando

### **2. Abrir no Dev Container**

1. Clone o repositório
2. Abra no VS Code
3. Quando solicitado, clique em "Reopen in Container"
4. Ou use o Command Palette: `Dev Containers: Reopen in Container`
5. **MongoDB será iniciado automaticamente** com dados de exemplo

### **3. Portas Expostas**

- **8080**: Aplicação Spring Boot
- **27017**: MongoDB
- **8081**: Mongo Express (interface web admin)

### **4. Acesso aos Serviços**

- **Aplicação**: http://localhost:8080
- **Mongo Express**: http://localhost:8081 (admin/admin123)
- **MongoDB**: mongodb://dev:dev123@localhost:27017/highschool

### **5. Dados Pré-configurados**

O MongoDB já vem com atividades de exemplo:

- Futebol (22 vagas)
- Voleibol (12 vagas)
- Teatro (15 vagas)
- Música (10 vagas)

## 🐳 MongoDB Otimizado

### **Configuração Automática**

✅ **Imagem pré-carregada** durante build do container
✅ **Usuários criados automaticamente**:

- `admin:admin123` (root access)
- `dev:dev123` (aplicação)
  ✅ **Dados de exemplo** já inseridos
  ✅ **Índices otimizados** criados
  ✅ **Persistência** em volume Docker

### **Strings de Conexão**

```bash
# Para desenvolvimento (usuário dev)
mongodb://dev:dev123@localhost:27017/highschool

# Para admin (acesso completo)
mongodb://admin:admin123@localhost:27017/highschool?authSource=admin
```

## 🔧 Personalização

### **Adicionar Extensões**

Edite `.devcontainer/devcontainer.json` na seção `extensions`:

```json
"extensions": [
  // Suas extensões existentes...
  "nova.extensao.id"
]
```

### **Modificar Configurações**

Edite a seção `settings` no mesmo arquivo.

### **Adicionar Features**

Adicione na seção `features`:

```json
"features": {
  "ghcr.io/devcontainers/features/docker-in-docker:2": {},
  "ghcr.io/devcontainers/features/node:1": {}
}
```

## 📚 Comandos Úteis

```bash
# Compilar o projeto
mvn clean compile

# Executar testes
mvn test

# Iniciar aplicação
mvn spring-boot:run

# Gerar relatório de cobertura
mvn jacoco:report
```

## 🎯 Benefícios

- **Setup Zero**: Ambiente completo em segundos
- **Consistência**: Mesmo ambiente para toda a equipe
- **Isolamento**: Não afeta o sistema host
- **Portabilidade**: Funciona em qualquer máquina com Docker

## 🔗 Links Úteis

- [VS Code Dev Containers](https://code.visualstudio.com/docs/devcontainers/containers)
- [Java Dev Container Features](https://github.com/devcontainers/features/tree/main/src/java)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
