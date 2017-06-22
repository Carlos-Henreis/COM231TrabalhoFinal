package Model;

import java.math.BigInteger;
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

        String consulta = "select concat(sel.definicao,'#',sel.media) from (select d.definicao, avg(a.mediapagamentosmedicare) as media from (select id from hospital where estado = :est) est, atendimento_drg a,drg d " +
                          "where a.idhospital = est.id and d.codigo = a.codigodrg "
                        + "group by codigodrg order by media asc) sel";
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

        String consulta = "select concat(d.definicao,'#',t.mediavalor) from drg d, (SELECT codigodrg,avg(mediapagamentosmedicare) as mediavalor from atendimento_drg group by codigodrg) t where d.codigo = t.codigodrg order by t.mediavalor asc;";
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
     * @param ordenar 1 = definicao DRG, 2 = numerototalAltas, 3 = numerohospitais capacitados
     * @return lista de dados do relatorio
     */
    public ArrayList<RelatorioDRGGeral> relatorioDRG(int ordenar) {
        ArrayList<RelatorioDRGGeral> lista = new ArrayList<>();
        String consulta = "";

        if(ordenar == 1)
            consulta = "select concat (d.definicao,'#',att.numero_total_altas,'#',att.numero_hospitais_capacitados) \n" +
                            "from(select codigodrg,sum(numeroaltas) as numero_total_altas,count(idhospital) as numero_hospitais_capacitados from atendimento_drg group by codigodrg) att, drg d\n" +
                            "where d.codigo = att.codigodrg order by d.definicao asc;";
            else
            {
                if(ordenar == 2)        
                    consulta = "select concat (d.definicao,'#',att.numero_total_altas,'#',att.numero_hospitais_capacitados) \n" +
                            "from(select codigodrg,sum(numeroaltas) as numero_total_altas,count(idhospital) as numero_hospitais_capacitados from atendimento_drg group by codigodrg) att, drg d\n" +
                            "where d.codigo = att.codigodrg order by att.numero_total_altas desc;";
                else
                    consulta = "select concat (d.definicao,'#',att.numero_total_altas,'#',att.numero_hospitais_capacitados) \n" +
                                    "from(select codigodrg,sum(numeroaltas) as numero_total_altas,count(idhospital) as numero_hospitais_capacitados from atendimento_drg group by codigodrg) att, drg d\n" +
                                    "where d.codigo = att.codigodrg order by att.numero_hospitais_capacitados desc;";
            }
        SQLQuery sql = sessao.createSQLQuery(consulta);
        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioDRGGeral(data[0] , Integer.parseInt(data[1]), Integer.parseInt(data[2])));
        }
        return lista;
    }
    
    /**
     * Busca no banco os dados necessarios para o relatorio de Numero de DRG's atendidas
     * por regiao de referencia
     * @return lista de dados
     */
    public ArrayList<RelatorioNumDRGPorRef> relatorioNumDRGPorRef() {
        ArrayList<RelatorioNumDRGPorRef> lista = new ArrayList<>();

        String consulta = "select concat(attr.regiao_referencia,'#', count(distinct attr.codigodrg)) " +
                                "from (select hr.regiao_referencia, att.codigodrg from hospitais_regiao hr, (select idhospital,codigodrg from atendimento_drg) att where hr.idhospital = att.idhospital ) attr " +
                                "group by regiao_referencia";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        for (Object o : sql.list()) {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new RelatorioNumDRGPorRef(data[0] , Integer.parseInt(data[1])));
        }
        return lista;
    }
    
    /**
     * Metodo para obter a lista com todos os estados cadastrados no sistema
     * @return lista de estados (String)
     */
    public String[] obterListaDeEstados()
    {
        String lista[];
        BigInteger dim;
        int dimensao;
        
        //Contar numero de estados distintos do banco
        String contador = "select count(distinct estado) from hospital";
        SQLQuery sql = sessao.createSQLQuery(contador);
        dim = (BigInteger) sql.list().get(0);
        
        //Definir dimensao da lista
        lista = new String[dim.intValue()];
        
        //Obter todos os estados disponiveis no SAGHA
        String consulta = "select distinct estado from hospital";
        sql = sessao.createSQLQuery(consulta);
        dimensao = 0;
        for(Object est : sql.list())
        {
            lista[dimensao] = (String) est;
            dimensao++;
        }
        
        return lista;
    }
}
