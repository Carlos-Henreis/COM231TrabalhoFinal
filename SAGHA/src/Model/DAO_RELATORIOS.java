package Model;

import java.util.LinkedList;
import java.util.List;
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

    public void felicidade(String est)
    {
        String consulta = "select d.definicao, avg(a.mediapagamentosmedicare) from hospital h,(select id from hospital where estado = :est) est, atendimento_drg a,drg d where h.id = est.id and d.codigo = a.codigodrg group by codigodrg";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        sql.setString("est", est);

        List<Relatorio1> lista = new LinkedList<>();

        for (String s : sql.getNamedParameters())
        {
            System.out.println(""+s);
        }
    }
}
