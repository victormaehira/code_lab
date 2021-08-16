CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('Glicemia');
INSERT INTO categoria (nome) values ('Temperatura');
INSERT INTO categoria (nome) values ('Pressao Sanguinea');
INSERT INTO categoria (nome) values ('Batimento Cardiaco');
INSERT INTO categoria (nome) values ('Outros');