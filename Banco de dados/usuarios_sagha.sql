create table usuarios
(
	nome varchar(64),
	cpf char(11) primary key,
	funcao varchar(16),
	senha char(8)
);

insert into usuarios values('Jean Carlos','00000000001','GERENTE','JEAN'),('Victor Rodrigues','00000000002','AGENTE','VICTOR'),('Mario mandzukic','00000000003','GERENTE','MARIO'),('Carlos Henrique','00000000004','GERENTE','CARLOS');