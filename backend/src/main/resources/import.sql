-- Script de inicialização do banco de dados
-- Executado automaticamente após a criação das tabelas

-- Inserir clientes de exemplo
INSERT INTO clientes (id, nome, email, telefone, cpf) VALUES (1, 'João Silva', 'joao.silva@email.com', '(11) 98765-4321', '123.456.789-00');
INSERT INTO clientes (id, nome, email, telefone, cpf) VALUES (2, 'Maria Santos', 'maria.santos@email.com', '(11) 98765-4322', '987.654.321-00');
INSERT INTO clientes (id, nome, email, telefone, cpf) VALUES (3, 'Pedro Oliveira', 'pedro.oliveira@email.com', '(11) 98765-4323', '456.789.123-00');

-- Inserir advogados de exemplo
INSERT INTO advogados (id, nome, oab, especialidade, email) VALUES (1, 'Dr. Carlos Mendes', 'SP 123456', 'Direito Civil', 'carlos.mendes@advocacia.com');
INSERT INTO advogados (id, nome, oab, especialidade, email) VALUES (2, 'Dra. Ana Paula', 'SP 234567', 'Direito Trabalhista', 'ana.paula@advocacia.com');
INSERT INTO advogados (id, nome, oab, especialidade, email) VALUES (3, 'Dr. Ricardo Alves', 'SP 345678', 'Direito Criminal', 'ricardo.alves@advocacia.com');

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
