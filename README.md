# Sistema de Agendamentos 

## 📋 Visão Geral

Sistema completo de gestão de advocacia desenvolvido com **Java 21 + Quarkus** no backend e **Vue 3 + Nuxt 3** no frontend, oferecendo APIs REST para gestão de advogados, clientes e agendamentos com validação Bean Validation e documentação OpenAPI/Swagger. A comunicação entre frontend e backend é realizada via Axios com composables centralizados, enquanto o gerenciamento de estado utiliza Pinia. A infraestrutura inclui containerização Docker com multi-stage builds, orquestração via docker-compose integrando PostgreSQL, Prometheus para métricas e Grafana para dashboards. O pipeline CI/CD no GitHub Actions automatiza build, testes e deploy, com observabilidade completa através de health checks, logs estruturados e monitoramento de performance em tempo real.

## �️ Stack Tecnológica

### Backend
- **Java 21 LTS** - Runtime moderno com performance otimizada
- **Quarkus 3.15.1** - Framework reativo supersônico
- **Hibernate ORM Panache** - Persistência simplificada
- **PostgreSQL** - Banco de dados relacional
- **Bean Validation** - Validação declarativa
- **SmallRye OpenAPI** - Documentação automática da API
- **Micrometer + Prometheus** - Métricas e observabilidade

### Frontend
- **Vue 3** - Framework progressivo com Composition API
- **Nuxt 3** - Framework full-stack para Vue
- **TypeScript** - Tipagem estática
- **Pinia** - Gerenciamento de estado
- **Tailwind CSS** - Framework CSS utilitário
- **Axios** - Cliente HTTP para APIs

### DevOps & Infraestrutura
- **Docker** - Containerização com multi-stage builds
- **Docker Compose** - Orquestração de serviços
- **GitHub Actions** - CI/CD automatizado
- **Prometheus** - Coleta de métricas
- **Grafana** - Dashboards e visualização
- **PostgreSQL** - Banco de dados em container

## 📁 Estrutura do Projeto

```
projeto3/
├── backend/          # API Java Quarkus
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/advocacia/
│   │       │       ├── entity/
│   │       │       └── resource/
│   │       └── resources/
│   └── pom.xml
└── frontend/         # Frontend Nuxt.js
    ├── app/
    ├── pages/
    ├── components/
    ├── composables/
    └── package.json
```

## 🔧 Pré-requisitos

- **Java 21** (JDK)
- **Maven 3.8+**
- **Node.js 18+** e npm
- **PostgreSQL 14+**

## 🎯 Configuração e Execução

### 1. Configurar o Banco de Dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE agendamento;
```

### 2. Executar o Backend

```bash
cd backend

# Compilar o projeto
mvn clean package

# Executar em modo desenvolvimento
mvn quarkus:dev
```

O backend estará disponível em: `http://localhost:8080`

### 3. Executar o Frontend

```bash
cd frontend

# Instalar dependências (primeira vez)
npm install

# Executar em modo desenvolvimento
npm run dev
```

O frontend estará disponível em: `http://localhost:3000`

## 📚 API Endpoints

### Advogados
- `GET /advogados` - Listar todos
- `GET /advogados/{id}` - Buscar por ID
- `POST /advogados` - Criar novo
- `PUT /advogados/{id}` - Atualizar
- `DELETE /advogados/{id}` - Deletar

### Clientes
- `GET /clientes` - Listar todos
- `GET /clientes/{id}` - Buscar por ID
- `POST /clientes` - Criar novo
- `PUT /clientes/{id}` - Atualizar
- `DELETE /clientes/{id}` - Deletar

### Agendamentos
- `GET /agendamentos` - Listar todos
- `GET /agendamentos/{id}` - Buscar por ID
- `POST /agendamentos` - Criar novo
- `PUT /agendamentos/{id}` - Atualizar
- `DELETE /agendamentos/{id}` - Deletar
- `GET /agendamentos/advogado/{id}` - Por advogado
- `GET /agendamentos/cliente/{id}` - Por cliente

## 🎨 Funcionalidades

- ✅ Gerenciamento completo de Advogados
- ✅ Gerenciamento completo de Clientes
- ✅ Gerenciamento completo de Agendamentos
- ✅ Interface responsiva e moderna
- ✅ Validação de dados no backend e frontend
- ✅ Status de agendamento (Pendente, Confirmado, Cancelado, Concluído)
- ✅ API REST documentada (Swagger UI em `/swagger-ui`)

## 🔍 Documentação da API

Com o backend rodando, acesse: `http://localhost:8080/swagger-ui`

## 🛠️ Build para Produção

### Backend
```bash
cd backend
mvn clean package -Dquarkus.package.type=uber-jar
java -jar target/agendamento-backend-1.0.0-SNAPSHOT-runner.jar
```

### Frontend
```bash
cd frontend
npm run build
npm run preview
```

## 📝 Configurações

### Backend
Edite `backend/src/main/resources/application.properties`:
```properties
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/agendamento
quarkus.datasource.username=postgres
quarkus.datasource.password=postgres
```

### Frontend
Crie um arquivo `.env` baseado em `.env.example`:
```bash
API_BASE_URL=http://localhost:8080
```

## 🚧 Status do Projeto

✅ Backend Java 21 (upgrade concluído)
✅ Frontend Vue.js/Nuxt.js criado
✅ CRUD completo de Advogados
✅ CRUD completo de Clientes
✅ CRUD completo de Agendamentos
✅ Interface responsiva

## 📄 Licença

Este projeto é de uso educacional.
