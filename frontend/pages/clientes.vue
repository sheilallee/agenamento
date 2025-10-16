<template>
  <div class="min-h-screen bg-gray-50 flex flex-col">
    <nav class="bg-blue-600 text-white shadow-lg">
      <div class="container mx-auto px-4">
        <div class="flex items-center justify-between h-16">
          <NuxtLink to="/" class="text-2xl font-bold">Sistema de Agendamento</NuxtLink>
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
      <div class="mb-6">
        <h1 class="text-3xl font-bold text-gray-800">Gerenciar Clientes</h1>
      </div>

      <div class="card mb-6">
        <h2 class="text-xl font-semibold mb-4">{{ editingId ? 'Editar Cliente' : 'Novo Cliente' }}</h2>
        <form @submit.prevent="saveCliente" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Nome</label>
              <input v-model="form.nome" type="text" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">CPF</label>
              <input v-model="form.cpf" type="text" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Email</label>
              <input v-model="form.email" type="email" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Telefone</label>
              <input v-model="form.telefone" type="tel" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
            <div class="md:col-span-2">
              <label class="block text-sm font-medium text-gray-700 mb-1">Endereço</label>
              <input v-model="form.endereco" type="text" class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
          </div>
          <div class="flex gap-2">
            <button type="submit" class="btn-primary">{{ editingId ? 'Atualizar' : 'Cadastrar' }}</button>
            <button v-if="editingId" type="button" @click="cancelEdit" class="btn-secondary">Cancelar</button>
          </div>
        </form>
      </div>

      <div class="card">
        <h2 class="text-xl font-semibold mb-4">Lista de Clientes</h2>
        <div v-if="loading" class="text-center py-4">Carregando...</div>
        <div v-else-if="clientes.length === 0" class="text-center py-4 text-gray-500">Nenhum cliente cadastrado</div>
        <div v-else class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nome</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">CPF</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Email</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Telefone</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Endereço</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="cliente in clientes" :key="cliente.id">
                <td class="px-6 py-4 whitespace-nowrap">{{ cliente.nome }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ cliente.cpf }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ cliente.email }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ cliente.telefone }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ cliente.endereco || '-' }}</td>
                <td class="px-6 py-4 whitespace-nowrap space-x-2">
                  <button @click="editCliente(cliente)" class="text-blue-600 hover:text-blue-800">Editar</button>
                  <button @click="deleteCliente(cliente.id!)" class="text-red-600 hover:text-red-800">Excluir</button>
                </td>
              </tr>
            </tbody>
          </table>
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
import type { Cliente } from '~/types'

const api = useApi()
const clientes = ref<Cliente[]>([])
const loading = ref(true)
const editingId = ref<number | null>(null)

const form = ref<Cliente>({
  nome: '',
  cpf: '',
  email: '',
  telefone: '',
  endereco: ''
})

const loadClientes = async () => {
  try {
    loading.value = true
    const response = await api.getClientes()
    clientes.value = response.data
  } catch (error) {
    console.error('Erro ao carregar clientes:', error)
  } finally {
    loading.value = false
  }
}

const saveCliente = async () => {
  try {
    if (editingId.value) {
      await api.updateCliente(editingId.value, form.value)
    } else {
      await api.createCliente(form.value)
    }
    resetForm()
    await loadClientes()
  } catch (error) {
    console.error('Erro ao salvar cliente:', error)
  }
}

const editCliente = (cliente: Cliente) => {
  editingId.value = cliente.id!
  form.value = { ...cliente }
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

const deleteCliente = async (id: number) => {
  if (confirm('Deseja realmente excluir este cliente?')) {
    try {
      await api.deleteCliente(id)
      await loadClientes()
    } catch (error) {
      console.error('Erro ao excluir cliente:', error)
    }
  }
}

const cancelEdit = () => {
  resetForm()
}

const resetForm = () => {
  editingId.value = null
  form.value = {
    nome: '',
    cpf: '',
    email: '',
    telefone: '',
    endereco: ''
  }
}

onMounted(() => {
  loadClientes()
})
</script>
