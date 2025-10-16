export interface Advogado {
  id?: number;
  nome: string;
  oab: string;
  email: string;
  telefone: string;
  especialidade: string;
}

export interface Cliente {
  id?: number;
  nome: string;
  cpf: string;
  email: string;
  telefone: string;
  endereco?: string;
}

export interface Agendamento {
  id?: number;
  advogado?: Advogado;
  cliente?: Cliente;
  dataHora: string;
  descricao: string;
  status: 'PENDENTE' | 'CONFIRMADO' | 'CANCELADO' | 'CONCLUIDO';
}

export interface ApiResponse<T> {
  data: T;
  message?: string;
  error?: string;
}
