CREATE TABLE categoria (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO categoria (nome) values ('SSL');
INSERT INTO categoria (nome) values ('Code Signing');
INSERT INTO categoria (nome) values ('Eletronic Invoice');
INSERT INTO categoria (nome) values ('eCPF');
INSERT INTO categoria (nome) values ('eCNPJ');