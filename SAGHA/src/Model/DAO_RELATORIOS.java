package Model;

import java.util.ArrayList;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO_RELATORIOS {

    private Session sessao;
    private Transaction tr;

    public DAO_RELATORIOS(Session ss) {
        sessao = ss;
    }

    /**
     * Gera o seguinte relatorio: Relatorio de pagamentos medios de DRG de
     * acordo com estado informado pelo usuario
     *
     * @param est estado desejado
     * @return lista de resultados <3
     */
    public ArrayList<RelatorioPagMedioDrgEstado> relatorioPagMedioDrgEstado(String est) {
        ArrayList<RelatorioPagMedioDrgEstado> lista = new ArrayList<>();

        String consulta = "select concat(d.definicao,'#',avg(a.mediapagamentosmedicare)) from hospital h,(select id from hospital where estado = :est) est, atendimento_drg a,drg d where h.id = est.id and d.codigo = a.codigodrg group by codigodrg";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        sql.setString("est", est);

        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioPagMedioDrgEstado(data[0], Double.parseDouble(data[1])));
        }

        return lista;
    }

    /**
     * Gera o seguinte relatorio: Relatorio de pagamentos medios por DRG
     *
     * @return lista de dados do relatorio
     */
    public ArrayList<RelatorioPagMedioDrgEstado> relatorioPagMedioPorDrg() {
        ArrayList<RelatorioPagMedioDrgEstado> lista = new ArrayList<>();

        String consulta = "select concat(d.definicao,'#',t.mediavalor) from drg d, (SELECT codigodrg,avg(mediapagamentosmedicare) as mediavalor from atendimento_drg group by codigodrg) t where d.codigo = t.codigodrg;";
        SQLQuery sql = sessao.createSQLQuery(consulta);

        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioPagMedioDrgEstado(data[0], Double.parseDouble(data[1])));
        }

        return lista;
    }
    
    /**
     * Gera o seguinte relatório: Relatório de contagem de número de DRGs por estado
     * @return lista de dados do relatorio
     */
    public ArrayList<RelatorioContagemDRGEstado> relatorioContNumeroDRGsEstado() {
        ArrayList<RelatorioContagemDRGEstado> lista = new ArrayList<>();

        String consulta = "select concat(teste.ESTADO,'#',teste.CONTAGEM) from (select distinct att.estado as ESTADO,count(distinct att.codigo) as CONTAGEM from (select h.estado,d.codigo from hospital h,drg d, atendimento_drg att where att.idhospital = h.id and att.codigodrg = d.codigo) att group by att.estado order by CONTAGEM desc) teste;";
        SQLQuery sql = sessao.createSQLQuery(consulta);

        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioContagemDRGEstado(data[0], Integer.parseInt(data[1])));
        }
        return lista;
    }
    
    /**
     * Gera o seguinte relatório: Relatório de pagamento medio total por hospital
     * @return lista de dados do relatorio
     */
    public ArrayList<RelatorioPagMedioTotalHospital> relatorioPagMedioTotalHospital() {
        ArrayList<RelatorioPagMedioTotalHospital> lista = new ArrayList<>();

        String consulta = "select concat (h.id,'#',h.nome,'#',h.estado,'#',val.valor_medio_pagamentos) \n" +
                          "from hospital h join (select idhospital, avg(pagamentosmediostotais) as valor_medio_pagamentos from atendimento_drg group by idhospital)  val \n" +
                          "on h.id = val.idhospital where h.estado = 'AL' order by val.valor_medio_pagamentos asc;";
        SQLQuery sql = sessao.createSQLQuery(consulta);

        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioPagMedioTotalHospital(Integer.parseInt(data[0]), data[1], data[2], Double.parseDouble(data[3])));
        }
        return lista;
    }


}
