create table agente
(
	nome varchar(64),
	cpf char(11) primary key,
	senha char(8)
);

insert into agente values('Jean Carlos','00000000001','JEAN'),('Victor Rodrigues','00000000002','VICTOR'),
	('Mario mandzukic','00000000003','MARIO'),('Carlos Henrique','00000000004','CARLOS');

create table gerente
(
	nome varchar(64),
	cpf char(11) primary key,
	senha char(8),
	idhospital int,
	foreign key (idhospital) references hospital(id) on delete cascade
);

insert into gerente values('Mateus Toledo','12712712712','rootroot',510031);