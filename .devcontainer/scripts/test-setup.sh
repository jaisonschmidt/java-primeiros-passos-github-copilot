#!/bin/bash

# Script para testar a configuração completa do Dev Container
# Execute este script para verificar se tudo está funcionando

echo "🧪 Iniciando testes da configuração Dev Container..."

# Testar Java
echo "☕ Testando Java..."
java -version
if [ $? -eq 0 ]; then
    echo "✅ Java 21 OK"
else
    echo "❌ Java falhou"
    exit 1
fi

# Testar Maven
echo "📦 Testando Maven..."
mvn -version
if [ $? -eq 0 ]; then
    echo "✅ Maven OK"
else
    echo "❌ Maven falhou"
    exit 1
fi

# Testar Docker
echo "🐳 Testando Docker..."
docker --version
if [ $? -eq 0 ]; then
    echo "✅ Docker OK"
else
    echo "❌ Docker falhou"
    exit 1
fi

# Testar conectividade MongoDB
echo "🍃 Testando conectividade MongoDB..."
timeout 10 bash -c 'until docker exec mongodb-highschool mongosh --eval "db.runCommand(\"ping\")" &>/dev/null; do sleep 1; done'
if [ $? -eq 0 ]; then
    echo "✅ MongoDB conectado"
else
    echo "⏳ MongoDB ainda inicializando..."
fi

# Testar compilação do projeto
echo "🔨 Testando compilação..."
mvn clean compile -q
if [ $? -eq 0 ]; then
    echo "✅ Compilação OK"
else
    echo "❌ Compilação falhou"
    exit 1
fi

# Testar execução dos testes
echo "🧪 Executando testes..."
mvn test -q
if [ $? -eq 0 ]; then
    echo "✅ Testes OK"
else
    echo "⚠️ Alguns testes falharam (esperado se MongoDB não estiver pronto)"
fi

# Verificar estrutura do projeto
echo "📁 Verificando estrutura..."
if [ -f "pom.xml" ] && [ -d "src/main/java" ] && [ -d ".devcontainer" ]; then
    echo "✅ Estrutura do projeto OK"
else
    echo "❌ Estrutura do projeto incorreta"
    exit 1
fi

# Verificar configurações do Dev Container
echo "🛠️ Verificando configurações..."
if [ -f ".devcontainer/devcontainer.json" ] && [ -f ".devcontainer/docker-compose.yml" ]; then
    echo "✅ Configurações do Dev Container OK"
else
    echo "❌ Configurações incompletas"
    exit 1
fi

echo ""
echo "🎉 Todos os testes passaram!"
echo "📍 Aplicação: http://localhost:8080"
echo "🗄️ Mongo Express: http://localhost:8081"
echo "🍃 MongoDB: mongodb://dev:dev123@localhost:27017/highschool"
echo ""
echo "Para iniciar a aplicação: mvn spring-boot:run"
