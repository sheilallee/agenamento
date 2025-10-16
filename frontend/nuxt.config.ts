// https://nuxt.com/docs/api/configuration/nuxt-config
export default defineNuxtConfig({
  devtools: { enabled: true },

  // Ordem: primeiro pinia depois tailwind (não crítico, apenas organizado)
  modules: ['@pinia/nuxt', '@nuxtjs/tailwindcss'],

  // Configuração explícita do módulo tailwind para garantir caminho do arquivo custom
  tailwindcss: {
    cssPath: '@/assets/css/main.css'
  },

  // PostCSS inline evita warning sobre postcss.config.cjs
  postcss: {
    plugins: {
      tailwindcss: {},
      autoprefixer: {}
    }
  },

  runtimeConfig: {
    public: {
      apiBase: process.env.NUXT_PUBLIC_API_BASE || 'http://localhost:8080'
    }
  },

  app: {
    head: {
      title: 'Sistema de Agendamento - Advocacia',
      meta: [
        { charset: 'utf-8' },
        { name: 'viewport', content: 'width=device-width, initial-scale=1' },
        { name: 'description', content: 'Sistema de agendamento para escritório de advocacia' }
      ]
    }
  },

  // CSS global usando alias @ (mais consistente cross-plataforma)
  css: ['@/assets/css/main.css'],

  compatibilityDate: '2025-10-16'
})