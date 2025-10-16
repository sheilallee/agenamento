-- Script de inicialização do banco de dados
-- Executado automaticamente após a criação das tabelas

INSERT INTO clientes (id, nome, email, telefone, cpf, endereco) VALUES (1, 'João Silva', 'joao.silva@email.com', '(11) 98765-4321', '123.456.789-00', 'Rua das Flores, 100 - São Paulo/SP');
INSERT INTO clientes (id, nome, email, telefone, cpf, endereco) VALUES (2, 'Maria Santos', 'maria.santos@email.com', '(11) 98765-4322', '987.654.321-00', 'Av. Paulista, 1000 - São Paulo/SP');
INSERT INTO clientes (id, nome, email, telefone, cpf, endereco) VALUES (3, 'Pedro Oliveira', 'pedro.oliveira@email.com', '(11) 98765-4323', '456.789.123-00', 'Rua Augusta, 500 - São Paulo/SP');

-- Inserir advogados de exemplo
INSERT INTO advogados (id, nome, oab, especialidade, email, telefone) VALUES (1, 'Dr. Carlos Mendes', 'SP 123456', 'Direito Civil', 'carlos.mendes@advocacia.com', '(11) 91234-5678');
INSERT INTO advogados (id, nome, oab, especialidade, email, telefone) VALUES (2, 'Dra. Ana Paula', 'SP 234567', 'Direito Trabalhista', 'ana.paula@advocacia.com', '(11) 91234-5679');
INSERT INTO advogados (id, nome, oab, especialidade, email, telefone) VALUES (3, 'Dr. Ricardo Alves', 'SP 345678', 'Direito Criminal', 'ricardo.alves@advocacia.com', '(11) 91234-5680');

-- Inserir agendamentos de exemplo
INSERT INTO agendamentos (id, cliente_id, advogado_id, datahora, descricao, status) 
VALUES (1, 1, 1, '2025-10-20 10:00:00', 'Consulta sobre processo de divórcio', 'PENDENTE');

INSERT INTO agendamentos (id, cliente_id, advogado_id, datahora, descricao, status) 
VALUES (2, 2, 2, '2025-10-21 14:30:00', 'Rescisão de contrato trabalhista', 'CONFIRMADO');

INSERT INTO agendamentos (id, cliente_id, advogado_id, datahora, descricao, status) 
VALUES (3, 3, 3, '2025-10-22 09:00:00', 'Defesa em processo criminal', 'PENDENTE');

-- Ajustar sequências para próximos IDs
ALTER SEQUENCE clientes_seq RESTART WITH 4;
ALTER SEQUENCE advogados_seq RESTART WITH 4;
ALTER SEQUENCE agendamentos_seq RESTART WITH 4;
