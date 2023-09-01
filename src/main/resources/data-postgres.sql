-- Inserindo um registro na tabela 'endereco'
INSERT INTO endereco (id, cep, rua, bairro, cidade, estado, numero)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '12345-678', 'Rua das Flores', 'Bairro Jardim', 'Cidade Maravilha', 'Estado Exemplo', '123');

-- Inserindo um registro na tabela 'usuario'
INSERT INTO usuario (id, nome, email, aniversario, criado_em, atualizado_em, id_endereco)
VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Nome Exemplo', 'email.exemplo@email.com', '2023-01-01', '2023-01-01', '2023-01-01', '550e8400-e29b-41d4-a716-446655440000');

-- -- Inserindo um registro na tabela 'cartao'
INSERT INTO cartao (id, numero_cartao, nome_titular, vencimento_cartao, codigo_seguranca, tipo_cartao, id_conta_banco, id_usuario)
VALUES ('6ba7b811-9dad-11d1-80b4-00c04fd430c8', '1234567891011121', 'Nome Titular', '2023-01-01', '1234', 'PRATA', 'ID_CONTA_BANCO', '6ba7b810-9dad-11d1-80b4-00c04fd430c8');


-- Inserindo um registro na tabela 'compra'
INSERT INTO compra (id, valor, status_compra, data_compra, loja, id_cartao)
VALUES ('6ba7b812-9dad-11d1-80b4-00c04fd430c8', 100.50, 'PENDENTE', '2023-9-1', 'Loja Exemplo', '6ba7b811-9dad-11d1-80b4-00c04fd430c8');

INSERT INTO compra (id, valor, status_compra, data_compra, loja, id_cartao)
VALUES ('758c0777-6411-49c6-a1ca-bd47256955ea', 50.50, 'PENDENTE', '2023-9-1', 'Loja Exemplo', '6ba7b811-9dad-11d1-80b4-00c04fd430c8');
