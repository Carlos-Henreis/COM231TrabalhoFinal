package Model;

import java.util.ArrayList;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO_RELATORIOS {

    private Session sessao;
    private Transaction tr;

    public DAO_RELATORIOS(Session ss)
    {
        sessao = ss;
    }
    
    /**
     * Gera o seguinte relatorio: Relatorio de pagamentos medios de DRG de acordo com estado informado pelo usuario
     * @param est estado desejado
     * @return lista de resultados <3
     */
    public ArrayList<Relatorio1> relatorioPagMedioDrgEstado(String est)
    {
        ArrayList<Relatorio1> lista = new ArrayList<>();
        
        String consulta = "select concat(d.definicao,'#',avg(a.mediapagamentosmedicare)) from hospital h,(select id from hospital where estado = :est) est, atendimento_drg a,drg d where h.id = est.id and d.codigo = a.codigodrg group by codigodrg";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        sql.setString("est", est);
        
        for(Object o : sql.list())
        {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new Relatorio1(data[0],Double.parseDouble(data[1])));
        }
        
        return lista;
    }
    
    /**
     * Gera o seguinte relatorio: Relatorio de pagamentos medios por DRG
     * @return lista de dados do relatorio
     */
    public ArrayList<Relatorio1> relatorioPagMedioPorDrg()
    {
        ArrayList<Relatorio1> lista = new ArrayList<>();
        
        String consulta = "select concat(d.definicao,'#',t.mediavalor) from drg d, (SELECT codigodrg,avg(mediapagamentosmedicare) as mediavalor from atendimento_drg group by codigodrg) t where d.codigo = t.codigodrg;";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        
        for(Object o : sql.list())
        {
            String st = (String) o;
            String data[] = st.split("#");
            lista.add(new Relatorio1(data[0],Double.parseDouble(data[1])));
        }
        
        return lista;
    }
    
    
}
