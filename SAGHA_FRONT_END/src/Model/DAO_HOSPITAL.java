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
        Iterator it = sessao.createQuery("from Hospital where id = "+id).list().iterator();
        if(it.hasNext())
        {
            h = (Hospital) it.next();
            return h;
        }
        
        //Hospital nao encontrado pelo codigo
        return null;
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
