// Script de inicialização do MongoDB
// Este arquivo é executado automaticamente quando o container MongoDB é criado

print('🚀 Iniciando configuração do banco highschool...');

// Conectar ao banco de dados da aplicação
db = db.getSiblingDB('highschool');

// Criar usuário de desenvolvimento
db.createUser({
  user: 'dev',
  pwd: 'dev123',
  roles: [
    {
      role: 'readWrite',
      db: 'highschool'
    },
    {
      role: 'dbAdmin',
      db: 'highschool'
    }
  ]
});

print('👤 Usuário dev criado com sucesso');

// Criar coleção de atividades com dados de exemplo
db.activities.insertMany([
  {
    _id: ObjectId(),
    name: "Futebol",
    description: "Atividade esportiva de futebol",
    maxParticipants: 22,
    participants: [],
    createdAt: new Date(),
    updatedAt: new Date()
  },
  {
    _id: ObjectId(),
    name: "Voleibol", 
    description: "Atividade esportiva de voleibol",
    maxParticipants: 12,
    participants: [],
    createdAt: new Date(),
    updatedAt: new Date()
  },
  {
    _id: ObjectId(),
    name: "Teatro",
    description: "Grupo de teatro escolar",
    maxParticipants: 15,
    participants: [],
    createdAt: new Date(),
    updatedAt: new Date()
  },
  {
    _id: ObjectId(),
    name: "Música",
    description: "Banda escolar",
    maxParticipants: 10,
    participants: [],
    createdAt: new Date(),
    updatedAt: new Date()
  }
]);

print('📚 Atividades de exemplo criadas');

// Criar índices para otimização
db.activities.createIndex({ "name": 1 }, { unique: true });
db.activities.createIndex({ "participants.email": 1 });

print('🔍 Índices criados');

print('✅ Configuração do banco highschool concluída!');
print('📍 Use: mongodb://dev:dev123@localhost:27017/highschool');
print('🔧 Admin: mongodb://admin:admin123@localhost:27017/highschool?authSource=admin');
