package Model;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.*;

public class DAO_HOSPITAL
{
    private Session sessao;
    private Transaction tr;

    public DAO_HOSPITAL(Session sess)
    {
        sessao = sess;
    }
    
    /**
     * Metodo que cadastra um hospital no sistema
     * @param h hospital a ser cadastrado
     * @return status de cadastro
     */
    public boolean cadastrarHospital(Hospital h)
    {
        sessao.save(h);
        tr = sessao.beginTransaction();
        tr.commit();
        
        //Se deu errado retorna falso
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que deleta um hospital do banco
     * @param id
     * @return 
     */
    public boolean removerHospital(int id)
    {
        Hospital h;
        
        //Buscar o Hospital p no banco
        Iterator it = sessao.createQuery("from Hospital where id = "+id).list().iterator();
        if(it.hasNext())
        {
            h = (Hospital) it.next();
            sessao.delete(h);
            tr = sessao.beginTransaction();
            tr.commit();
            
            return tr.wasCommitted();
        }
        
        //Hospital nao encontrado pelo codigo
        return false;
    }
    
    /**
     * Metodo que busca um hospital no banco
     * @param id identificador do hospital
     * @return hospital buscado ou nulo caso nao encontrado
     */
    public Hospital getHospital(int id)
    {
        Hospital h;
        
        //Buscar o Hospital p no banco
        Iterator it = sessao.createQuery("from Hospital where login = '"+id).list().iterator();
        if(it.hasNext())
        {
            h = (Hospital) it.next();
            return h;
        }
        
        //Hospital nao encontrado pelo codigo
        return null;
    }
    
    public void felicidade(String est)
    {
        String consulta = "select concat(d.definicao,'#',avg(a.mediapagamentosmedicare)) from hospital h,(select id from hospital where estado = :est) est, atendimento_drg a,drg d where h.id = est.id and d.codigo = a.codigodrg group by codigodrg";
        SQLQuery sql = sessao.createSQLQuery(consulta);
        sql.setString("est", est);
        
        
        int i = 0;
        for(Object o : sql.list())
        {
            String st = (String) o;
            String data[] = st.split("#");
            System.out.println("Definicao: "+data[0]+"\nMedia: "+data[1]+"\n\n");
            i++;
        }
        
        System.out.println("Contagem: "+i);
    }
    
    /**
     * Obtem todos os hospitais cadastrados
     * @return lista de hospitais
     */
    public ArrayList<Hospital> listarHospitais()
    {
        ArrayList<Hospital> lista = new ArrayList<>();
        
        Iterator it = sessao.createQuery("from Hospital").list().iterator();
        while(it.hasNext())
        {
            Hospital h = (Hospital) it.next();
            lista.add(h);
        }
        
        return lista;
    }
    
    /**
     * Metodo que atualiza os dados de um hospital cadastrado no sistema
     * @param h hospital com os novos dados 
     * @return status da atualizacao
     */
    public boolean atualizarDadosHospital(Hospital h)
    {
        sessao.update(h);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
}
