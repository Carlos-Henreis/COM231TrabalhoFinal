--CRIAR BANCO COM TODAS AS COLUNAS DO .CSV
create database tudao;

use tudao;

create table tabelao(
	drg varchar(256),
	idprovedor int,
	nomeprovedor varchar(256),
	enderecoprovedor varchar(256),
	cidadeprovedor varchar(64),
	estadoprovedor varchar(2),
	descricao_regiao_ref_hospitalar varchar(64),
	altastotais smallint,
	taxasmediascobertas float(2),
	pagamentosmedicostotais float(2),
	mediapagamentosmedicare float(2),
	primary key (drg,idprovedor)
);

--POPULAR BANCO COM DADOS DO CVS
LOAD DATA LOCAL INFILE 'banco_americano.csv'
INTO TABLE tabelao
FIELDS TERMINATED BY ',';


/*BANCO FINAL*/
--SAGHA: sistema de apoio a gestao de hospitais americanos
create database sagha;

--Tabela DRG: Armazena dados do grupo relacionado de doenças
--Tabela foi populada via aplicaçao java
create table drg
(
	codigo smallint primary key,			--O maior ID é inferior a 1000
	definicao varchar(128) not null	--A maior definicao tem menos de 70 caracteres
);

--Tabela hospital: Armazena os dados que identificam um hospital
create table hospital
(
	id int primary key,		--O maior ID tem valor de 670000
	nome varchar(64) not null		--O maior nome tem menos de 50 caracteres
);
--Populando essa tabela:
insert into sagha.hospital select distinct t.idprovedor,t.nomeprovedor from tudao.tabelao t;

--Tabela hospitais_regiao: Armazena chave estrangeira do hospital e descricao de sua regiao de referencia
create table hospitais_regiao
(
	idhospital int,
	regiao_referencia varchar(64) not null,
	foreign key (idhospital) references hospital(id) on delete cascade,
	primary key(idhospital,regiao_referencia)
);
--Populando essa tabela:
insert into sagha.hospitais_regiao select distinct t.idprovedor,t.descricao_regiao_ref_hospitalar from tudao.tabelao t;


--Tabela endereço: Armazena o endereço de um hospital
create table endereco
(
	idhospital int primary key,
	rua varchar(52) not null,
	cidade varchar(64) not null,				--O maior campo cidade tem menos de 60 caracteres
	estado char(2) not null,
	foreign key (idhospital) references hospital(id) on delete cascade
);
--Populando essa tabela:
insert into sagha.endereco select distinct t.idprovedor,enderecoprovedor,t.cidadeprovedor,t.estadoprovedor from tudao.tabelao t;

--Tabela Dados Tratamento: Dados relativos ao tratamento de determinado drg em determinado hospital
create table atendimento_drg
(
	codigodrg smallint,
	idhospital int,
	numeroaltas smallint not null,
	taxasmediascobertas float(2) not null,
	pagamentosmediostotais float(2) not null,
	mediapagamentosmedicare float(2) not null,
	foreign key(idhospital) references hospital(id) on update cascade,
	foreign key(codigodrg) references drg(codigo) on update cascade,
	primary key(idhospital,codigodrg)
);
--Populando essa tabela: