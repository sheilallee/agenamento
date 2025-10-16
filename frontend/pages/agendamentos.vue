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
        <h1 class="text-3xl font-bold text-gray-800">Gerenciar Agendamentos</h1>
      </div>

      <div class="card mb-6">
        <h2 class="text-xl font-semibold mb-4">{{ editingId ? 'Editar Agendamento' : 'Novo Agendamento' }}</h2>
        <form @submit.prevent="saveAgendamento" class="space-y-4">
          <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Advogado</label>
              <select v-model="form.advogadoId" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                <option value="">Selecione um advogado</option>
                <option v-for="advogado in advogados" :key="advogado.id" :value="advogado.id">
                  {{ advogado.nome }}
                </option>
              </select>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Cliente</label>
              <select v-model="form.clienteId" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                <option value="">Selecione um cliente</option>
                <option v-for="cliente in clientes" :key="cliente.id" :value="cliente.id">
                  {{ cliente.nome }}
                </option>
              </select>
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Data e Hora</label>
              <input v-model="form.dataHora" type="datetime-local" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent" />
            </div>
            <div>
              <label class="block text-sm font-medium text-gray-700 mb-1">Status</label>
              <select v-model="form.status" required class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent">
                <option value="PENDENTE">Pendente</option>
                <option value="CONFIRMADO">Confirmado</option>
                <option value="CANCELADO">Cancelado</option>
                <option value="CONCLUIDO">Concluído</option>
              </select>
            </div>
            <div class="md:col-span-2">
              <label class="block text-sm font-medium text-gray-700 mb-1">Descrição</label>
              <textarea v-model="form.descricao" required rows="3" class="w-full px-3 py-2 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent"></textarea>
            </div>
          </div>
          <div class="flex gap-2">
            <button type="submit" class="btn-primary">{{ editingId ? 'Atualizar' : 'Cadastrar' }}</button>
            <button v-if="editingId" type="button" @click="cancelEdit" class="btn-secondary">Cancelar</button>
          </div>
        </form>
      </div>

      <div class="card">
        <h2 class="text-xl font-semibold mb-4">Lista de Agendamentos</h2>
        <div v-if="loading" class="text-center py-4">Carregando...</div>
        <div v-else-if="agendamentos.length === 0" class="text-center py-4 text-gray-500">Nenhum agendamento cadastrado</div>
        <div v-else class="overflow-x-auto">
          <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-50">
              <tr>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Advogado</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Cliente</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Data/Hora</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Descrição</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Status</th>
                <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Ações</th>
              </tr>
            </thead>
            <tbody class="bg-white divide-y divide-gray-200">
              <tr v-for="agendamento in agendamentos" :key="agendamento.id">
                <td class="px-6 py-4 whitespace-nowrap">{{ agendamento.advogado?.nome }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ agendamento.cliente?.nome }}</td>
                <td class="px-6 py-4 whitespace-nowrap">{{ formatDateTime(agendamento.dataHora) }}</td>
                <td class="px-6 py-4">{{ agendamento.descricao }}</td>
                <td class="px-6 py-4 whitespace-nowrap">
                  <span :class="getStatusClass(agendamento.status)" class="px-2 py-1 rounded-full text-xs font-semibold">
                    {{ agendamento.status }}
                  </span>
                </td>
                <td class="px-6 py-4 whitespace-nowrap space-x-2">
                  <button @click="editAgendamento(agendamento)" class="text-blue-600 hover:text-blue-800">Editar</button>
                  <button @click="deleteAgendamento(agendamento.id!)" class="text-red-600 hover:text-red-800">Excluir</button>
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
import type { Agendamento, Advogado, Cliente } from '~/types'

const api = useApi()
const agendamentos = ref<Agendamento[]>([])
const advogados = ref<Advogado[]>([])
const clientes = ref<Cliente[]>([])
const loading = ref(true)
const editingId = ref<number | null>(null)

const form = ref({
  advogadoId: '',
  clienteId: '',
  dataHora: '',
  descricao: '',
  status: 'PENDENTE'
})

const loadData = async () => {
  try {
    loading.value = true
    const [agendamentosRes, advogadosRes, clientesRes] = await Promise.all([
      api.getAgendamentos(),
      api.getAdvogados(),
      api.getClientes()
    ])
    agendamentos.value = agendamentosRes.data
    advogados.value = advogadosRes.data
    clientes.value = clientesRes.data
  } catch (error) {
    console.error('Erro ao carregar dados:', error)
  } finally {
    loading.value = false
  }
}

const saveAgendamento = async () => {
  try {
    const data = {
      advogado: { id: parseInt(form.value.advogadoId) },
      cliente: { id: parseInt(form.value.clienteId) },
      dataHora: form.value.dataHora,
      descricao: form.value.descricao,
      status: form.value.status
    }
    
    if (editingId.value) {
      await api.updateAgendamento(editingId.value, data)
    } else {
      await api.createAgendamento(data)
    }
    resetForm()
    await loadData()
  } catch (error) {
    console.error('Erro ao salvar agendamento:', error)
  }
}

const editAgendamento = (agendamento: Agendamento) => {
  editingId.value = agendamento.id!
  form.value = {
    advogadoId: agendamento.advogado?.id?.toString() || '',
    clienteId: agendamento.cliente?.id?.toString() || '',
    dataHora: agendamento.dataHora,
    descricao: agendamento.descricao,
    status: agendamento.status
  }
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

const deleteAgendamento = async (id: number) => {
  if (confirm('Deseja realmente excluir este agendamento?')) {
    try {
      await api.deleteAgendamento(id)
      await loadData()
    } catch (error) {
      console.error('Erro ao excluir agendamento:', error)
    }
  }
}

const cancelEdit = () => {
  resetForm()
}

const resetForm = () => {
  editingId.value = null
  form.value = {
    advogadoId: '',
    clienteId: '',
    dataHora: '',
    descricao: '',
    status: 'PENDENTE'
  }
}

const formatDateTime = (dateTime: string) => {
  if (!dateTime) return '-'
  const date = new Date(dateTime)
  return date.toLocaleString('pt-BR')
}

const getStatusClass = (status: string) => {
  const classes: Record<string, string> = {
    'PENDENTE': 'bg-yellow-100 text-yellow-800',
    'CONFIRMADO': 'bg-blue-100 text-blue-800',
    'CANCELADO': 'bg-red-100 text-red-800',
    'CONCLUIDO': 'bg-green-100 text-green-800'
  }
  return classes[status] || 'bg-gray-100 text-gray-800'
}

onMounted(() => {
  loadData()
})
</script>
