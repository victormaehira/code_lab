CREATE TABLE pessoa (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(50),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(50),
	cidade VARCHAR(50),
	estado VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Victor', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('John', true, 'Rua do ze', '33', 'apto 4', 'Vila Prudente', '03221-040', 'floripa', 'sc');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Mary', true, 'Rua de baixo', '1234', '', 'vila zelina', '03121-042', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Luiz', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Jair', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Pedro', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Zulu', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Wagon', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Kaka', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');
insert into pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) 
values ('Kiko', true, 'Rua teste', '123', 'apto 3', 'mooca', '03121-040', 'sao paulo', 'sp');