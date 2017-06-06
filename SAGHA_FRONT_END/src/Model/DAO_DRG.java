package Model;

import java.util.*;
import org.hibernate.*;

public class DAO_DRG
{
    private Session sessao;
    private Transaction tr;
    
    public DAO_DRG(Session session)
    {
        sessao = session;
    }
    
    /**
     * Metodo que cadastra uma DRG no sistema
     * @param d drg a ser cadastrada
     * @return status de cadastro
     */
    public boolean cadastrarDrg(Drg d)
    {
        sessao.save(d);
        tr = sessao.beginTransaction();
        tr.commit();
        
        //Se deu errado retorna falso
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que deleta uma drg do banco
     * @param codigo
     * @return 
     */
    public boolean removerDrg(int codigo)
    {
        Drg d;
        
        //Buscar a drg no banco
        Iterator it = sessao.createQuery("from Drg where id = "+codigo).list().iterator();
        if(it.hasNext())
        {
            d = (Drg) it.next();
            sessao.delete(d);
            tr = sessao.beginTransaction();
            tr.commit();
            
            return tr.wasCommitted();
        }
        
        //Drg nao encontrado pelo codigo
        return false;
    }
    
    /**
     * Metodo que busca uma drg no banco
     * @param codigo identificador da drg
     * @return drg buscado ou nulo caso nao encontrado
     */
    public Drg getDrg(int codigo)
    {
        Drg d;
        
        //Buscar a drg no banco
        Iterator it = sessao.createQuery("from Drg where id = "+codigo).list().iterator();
        if(it.hasNext())
        {
            d = (Drg) it.next();
            return d;
        }
        
        //Drg nao encontrada pelo codigo
        return null;
    }
    
    /**
     * Obtem todas as DRG's cadastrados
     * @return lista de drgs
     */
    public ArrayList<Drg> listarDRGs()
    {
        ArrayList<Drg> lista = new ArrayList<>();
        
        Iterator it = sessao.createQuery("from Drg").list().iterator();
        while(it.hasNext())
        {
            Drg d = (Drg) it.next();
            lista.add(d);
        }
        
        return lista;
    }
    
    /**
     * Metodo que atualiza os dados de uma Drg cadastrada no sistema
     * @param d Drg com os novos dados 
     * @return status da atualizacao
     */
    public boolean atualizarDadosDrg(Drg d)
    {
        sessao.update(d);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
}
