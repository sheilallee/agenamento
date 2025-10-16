# Dockerfile para Nuxt (frontend)
FROM node:20-alpine AS build
WORKDIR /app

# Instalar dependências primeiro (melhor cache)
COPY package*.json ./
RUN npm ci --only=production=false

# Copiar código e gerar arquivos TypeScript
COPY . .
RUN npm run prepare

# Build da aplicação
RUN npm run build

FROM node:20-alpine
WORKDIR /app
COPY --from=build /app/.output ./
EXPOSE 3000
CMD ["node", "server/index.mjs"]
