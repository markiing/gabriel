create table if not exists aluno (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	nome varchar(256 )NOT NULL,
	cpf VARCHAR(11) NOT NULL,
	email varchar(100),
	endereco varchar(256),
	telefone varchar(24),
	data_cadastro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	categoria char(4)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=4;