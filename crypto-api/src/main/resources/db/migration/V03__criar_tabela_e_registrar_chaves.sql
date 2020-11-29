CREATE TABLE chave (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Assinatura Prontuário', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RSA', 1, 1);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Acesso e-cac', '2017-02-10', '2017-02-10', 100.32, null, 'ELLIPTICAL_CURVE', 2, 2);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Nota Fiscal Eletrônica', '2017-06-10', null, 120, null, 'RSA', 3, 3);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Assinatura imóvel', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RSA', 3, 4);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Assinatura imóvel', '2017-06-10', null, 200.30, null, 'ELLIPTICAL_CURVE', 3, 5);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Assinatura imóvel', '2017-03-10', '2017-03-10', 1010.32, null, 'RSA', 4, 6);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Acesso e-cac', '2017-06-10', null, 500, null, 'RSA', 1, 7);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Acesso e-cac', '2017-03-10', '2017-03-10', 400.32, null, 'ELLIPTICAL_CURVE', 4, 8);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Assinatura imóvel', '2017-06-10', null, 123.64, 'Multas', 'ELLIPTICAL_CURVE', 3, 9);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Nota Fiscal Eletrônica', '2017-04-10', '2017-04-10', 665.33, null, 'RSA', 5, 10);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Acesso e-cac', '2017-06-10', null, 8.32, null, 'ELLIPTICAL_CURVE', 1, 5);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Acesso e-cac', '2017-04-10', '2017-04-10', 2100.32, null, 'ELLIPTICAL_CURVE', 5, 4);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('Nota Fiscal Eletrônica', '2017-06-10', null, 1040.32, null, 'ELLIPTICAL_CURVE', 4, 3);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('CRM Digital', '2017-04-10', '2017-04-10', 4.32, null, 'ELLIPTICAL_CURVE', 4, 2);
INSERT INTO chave (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('CRM Digital', '2017-06-10', null, 10.20, null, 'ELLIPTICAL_CURVE', 4, 1);