import axios from 'axios'

export const useApi = () => {
  const config = useRuntimeConfig()
  
  const api = axios.create({
    baseURL: config.public.apiBase,
    headers: {
      'Content-Type': 'application/json',
    },
  })

  return {
    // Advogados
    getAdvogados: () => api.get('/api/advogados'),
    getAdvogado: (id: number) => api.get(`/api/advogados/${id}`),
    createAdvogado: (data: any) => api.post('/api/advogados', data),
    updateAdvogado: (id: number, data: any) => api.put(`/api/advogados/${id}`, data),
    deleteAdvogado: (id: number) => api.delete(`/api/advogados/${id}`),

    // Clientes
    getClientes: () => api.get('/api/clientes'),
    getCliente: (id: number) => api.get(`/api/clientes/${id}`),
    createCliente: (data: any) => api.post('/api/clientes', data),
    updateCliente: (id: number, data: any) => api.put(`/api/clientes/${id}`, data),
    deleteCliente: (id: number) => api.delete(`/api/clientes/${id}`),

    // Agendamentos
    getAgendamentos: () => api.get('/api/agendamentos'),
    getAgendamento: (id: number) => api.get(`/api/agendamentos/${id}`),
    createAgendamento: (data: any) => api.post('/api/agendamentos', data),
    updateAgendamento: (id: number, data: any) => api.put(`/api/agendamentos/${id}`, data),
    deleteAgendamento: (id: number) => api.delete(`/api/agendamentos/${id}`),
    getAgendamentosByAdvogado: (id: number) => api.get(`/api/agendamentos/advogado/${id}`),
    getAgendamentosByCliente: (id: number) => api.get(`/api/agendamentos/cliente/${id}`),
  }
}
