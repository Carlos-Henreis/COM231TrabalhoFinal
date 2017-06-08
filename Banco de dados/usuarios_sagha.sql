create table usuarios
(
	nome varchar(64),
	cpf char(11) unique,
	funcao varchar(16),
	login varchar(16),
	senha char(8),
	primary key (login,senha)
);

insert into usuarios values('Mateus Henrique Toledo','12332112321','AGENTE','root','root0'),('Carlos Henrique Reis','40028922123','AGENTE','root1','root2'),('Victor Rodrigues','08007777000','AGENTE','root','root2');