#!/bin/bash

# Script para inicializar MongoDB no Dev Container
# Este script é executado automaticamente quando o container é criado

set -e

echo "🚀 Iniciando configuração do MongoDB..."

# Verificar se o Docker está disponível
if ! command -v docker &> /dev/null; then
    echo "❌ Docker não está disponível"
    exit 1
fi

# Verificar se a imagem do MongoDB já existe
if docker images | grep -q "mongo.*6.0"; then
    echo "✅ Imagem MongoDB 6.0 já existe"
else
    echo "📥 Baixando imagem MongoDB 6.0..."
    docker pull mongo:6.0
fi

# Verificar se já existe um container MongoDB rodando
if docker ps | grep -q "mongodb-highschool"; then
    echo "✅ MongoDB já está rodando"
    exit 0
fi

# Verificar se existe um container parado
if docker ps -a | grep -q "mongodb-highschool"; then
    echo "🔄 Iniciando container MongoDB existente..."
    docker start mongodb-highschool
else
    echo "🐳 Criando e iniciando container MongoDB..."
    docker run -d \
        --name mongodb-highschool \
        -p 27017:27017 \
        -e MONGO_INITDB_ROOT_USERNAME=admin \
        -e MONGO_INITDB_ROOT_PASSWORD=admin123 \
        -e MONGO_INITDB_DATABASE=highschool \
        --restart unless-stopped \
        mongo:6.0
fi

# Aguardar MongoDB estar pronto
echo "⏳ Aguardando MongoDB inicializar..."
timeout=30
counter=0

while ! docker exec mongodb-highschool mongosh --eval "db.runCommand('ping')" &>/dev/null; do
    sleep 1
    counter=$((counter + 1))
    if [ $counter -ge $timeout ]; then
        echo "❌ Timeout aguardando MongoDB"
        exit 1
    fi
done

echo "✅ MongoDB está pronto!"

# Criar usuário de desenvolvimento (opcional)
echo "👤 Configurando usuário de desenvolvimento..."
docker exec mongodb-highschool mongosh --eval "
use admin;
db.createUser({
  user: 'dev',
  pwd: 'dev123',
  roles: [
    { role: 'readWrite', db: 'highschool' },
    { role: 'dbAdmin', db: 'highschool' }
  ]
});
" 2>/dev/null || echo "ℹ️ Usuário dev já existe ou falha na criação"

echo "🎉 MongoDB configurado com sucesso!"
echo "📍 Conexão: mongodb://admin:admin123@localhost:27017/highschool?authSource=admin"
echo "🔧 Dev User: mongodb://dev:dev123@localhost:27017/highschool?authSource=admin"
