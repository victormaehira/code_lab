CREATE TABLE lancamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	-- descricao VARCHAR(50) NOT NULL,
	descricao VARCHAR(50),
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

INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 6500.00, null, 'GLICEMIA', 1, 1);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-02-10', 100.32, null, 'PRESSAO', 2, 2);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 120, null, 'PRESSAO', 3, 3);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-02-10', 110.44, null, 'PRESSAO', 3, 4);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 200.30, null, 'PRESSAO', 3, 5);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-03-10', 1010.32, null, 'PRESSAO', 4, 6);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 500, null, 'GLICEMIA', 1, 7);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-03-10', 400.32, null, 'PRESSAO', 4, 8);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 123.64, 'Multas', 'PRESSAO', 3, 9);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-04-10', 665.33, null, 'PRESSAO', 5, 10);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 8.32, null, 'PRESSAO', 1, 5);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-04-10', 2100.32, null, 'PRESSAO', 5, 4);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 1040.32, null, 'PRESSAO', 4, 3);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-04-10', 4.32, null, 'PRESSAO', 4, 2);
INSERT INTO lancamento (data_vencimento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) values ('2017-06-10', 10.20, null, 'PRESSAO', 4, 1);
