--Valores dos pagamentos médios de DRG por estado -- USUARIO INFORMA O ESTADO
--Filtar por estado
--Ordenar por nome da DRG
select d.definicao, avg(a.mediapagamentosmedicare) from hospital h,(select id from hospital where estado = 'AL') est, atendimento_drg a,drg d
where h.id = est.id and d.codigo = a.codigodrg group by codigodrg;


--Media de pagamentos por DRG
--Filtar por definicao de DRG
--Ordenar por media de valor
select d.definicao,t.mediavalor
from drg d, (SELECT codigodrg,avg(mediapagamentosmedicare) as mediavalor from atendimento_drg group by codigodrg) t where d.codigo = t.codigodrg;

--Valor dos pagamentos médios de DRG por estado do provedor.

--Grafico de barras com numero de DRG's atendidas por estado (um com os que mais atendem e um com os que menos atendem)
select distinct att.estado as ESTADO,count(distinct att.codigo) as CONTAGEM
from (select h.estado,d.codigo from hospital h,drg d, atendimento_drg att where att.idhospital = h.id and att.codigodrg = d.codigo) att group by att.estado order by CONTAGEM desc;

--Pagamentos medios totais por hospital
--Ordenacao por valor
--Filtragem por hospital ou por estado
select h.id,h.nome,h.estado,val.valor_medio_pagamentos 
from hospital h join (select idhospital, avg(pagamentosmediostotais) as valor_medio_pagamentos from atendimento_drg group by idhospital)  val 
on h.id = val.idhospital order by val.valor_medio_pagamentos asc;

--Definicao da DRG, numero de altas totais dessa DRG, numero de hospitais que atendem essa DRG,media de altas por hospital
select d.definicao, att.numero_total_altas,att.numero_hospitais_capacitados 
from(select codigodrg,sum(numeroaltas) as numero_total_altas,count(idhospital) as numero_hospitais_capacitados from atendimento_drg group by codigodrg) att, drg d
where d.codigo = att.codigodrg order by att.numero_total_altas;

--Numero de DRG's atendidas por regiao de referencia
select attr.regiao_referencia, count(distinct attr.codigodrg) 
from (select hr.regiao_referencia, att.codigodrg from hospitais_regiao hr, (select idhospital,codigodrg from atendimento_drg) att where hr.idhospital = att.idhospital ) attr
group by regiao_referencia;