<template>
  <div class="min-h-screen bg-gray-50 flex flex-col">
    <nav class="bg-blue-600 text-white shadow-lg">
      <div class="container mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <div class="flex items-center">
            <h1 class="text-2xl font-bold">Sistema de Agendamento</h1>
          </div>
          <div class="flex space-x-4">
            <NuxtLink to="/" class="hover:bg-blue-700 px-3 py-2 rounded">Home</NuxtLink>
            <NuxtLink to="/advogados" class="hover:bg-blue-700 px-3 py-2 rounded">Advogados</NuxtLink>
            <NuxtLink to="/clientes" class="hover:bg-blue-700 px-3 py-2 rounded">Clientes</NuxtLink>
            <NuxtLink to="/agendamentos" class="hover:bg-blue-700 px-3 py-2 rounded">Agendamentos</NuxtLink>
          </div>
        </div>
      </div>
    </nav>

    <main class="flex-grow container mx-auto px-4 py-8">
      <div class="text-center">
        <h2 class="text-4xl font-bold text-gray-800 mb-4">Bem-vindo ao Sistema de Agendamento</h2>
        <p class="text-xl text-gray-600 mb-8">Gerencie advogados, clientes e agendamentos de forma eficiente</p>
        
        <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mt-12">
          <NuxtLink to="/advogados" class="card hover:shadow-xl transition duration-200">
            <div class="text-blue-600 mb-4">
              <svg class="w-16 h-16 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z"></path>
              </svg>
            </div>
            <h3 class="text-2xl font-semibold mb-2">Advogados</h3>
            <p class="text-gray-600">Gerencie o cadastro de advogados</p>
          </NuxtLink>

          <NuxtLink to="/clientes" class="card hover:shadow-xl transition duration-200">
            <div class="text-green-600 mb-4">
              <svg class="w-16 h-16 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z"></path>
              </svg>
            </div>
            <h3 class="text-2xl font-semibold mb-2">Clientes</h3>
            <p class="text-gray-600">Gerencie o cadastro de clientes</p>
          </NuxtLink>

          <NuxtLink to="/agendamentos" class="card hover:shadow-xl transition duration-200">
            <div class="text-purple-600 mb-4">
              <svg class="w-16 h-16 mx-auto" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z"></path>
              </svg>
            </div>
            <h3 class="text-2xl font-semibold mb-2">Agendamentos</h3>
            <p class="text-gray-600">Gerencie os agendamentos</p>
          </NuxtLink>
        </div>
      </div>
    </main>

    <footer class="bg-gray-800 text-white mt-auto">
      <div class="container mx-auto px-4 py-6 text-center">
        <p>&copy; 2025 Sistema de Agendamento - Advocacia. Todos os direitos reservados.</p>
      </div>
    </footer>
  </div>
</template>

<script setup lang="ts">
</script>
