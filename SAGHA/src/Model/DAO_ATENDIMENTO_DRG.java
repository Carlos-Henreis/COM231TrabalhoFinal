package Model;

import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO_ATENDIMENTO_DRG
{
    private Session sessao;
    private Transaction tr;
    private DAO_HOSPITAL DAO_H;
    private DAO_DRG DAO_D;
    
    public DAO_ATENDIMENTO_DRG(Session session)
    {
        sessao = session;
        DAO_H = new DAO_HOSPITAL(sessao);
        DAO_D = new DAO_DRG(sessao);
    }
    
    /**
     * Metodo que cadastra o atendimento de uma DRG no sistema
     * @param att O atendimento que deve ser cadastrado
     * @return Status do cadastro
     */
    public boolean cadastrarAtendimentoDRG(AtendimentoDrg att)
    {
        sessao.save(att);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que atualiza os dados de um atendimento de DRG presente no sistema SAGHA
     * @param att atendimento de DRG com os dados atualizados
     * @return 
     */
    public boolean atualizarDadosAtendimentoDRG(AtendimentoDrg att)
    {
        sessao.update(att);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que retorna um registro de atendimento de DRG cadastrado no sistema
     * @param primary_key
     * @return 
     */
    public AtendimentoDrg getAtendimentoDrg(AtendimentoDrgId primary_key)
    {
        AtendimentoDrg att = new AtendimentoDrg();
        
        Iterator it = sessao.createQuery("from AtendimentoDrg where idhospital = "+primary_key.getIdhospital()+"and codigodrg = "+primary_key.getCodigodrg()).list().iterator();
        if(it.hasNext())
            return (AtendimentoDrg) it.next();
        else
            return null;
    }
    
    /**
     * Possibilita a obtencao de todos os atendimentos a DRG cadastrados no sistema
     * @return lista de atendimentos
     */
    public ArrayList<AtendimentoDrg> obterListaAtendimentoDRGs()
    {
        ArrayList<AtendimentoDrg> array = new ArrayList<>();
        
        Iterator it = sessao.createQuery("from AtendimentoDrg").list().iterator();
        while(it.hasNext())
        {
            AtendimentoDrg att = (AtendimentoDrg) it.next();
            array.add(att);
        }
        
        return array;
    }
    
    /**
     * Metodo para obter DAO de hospitais
     * @return DAO de hospital
     */
    public DAO_HOSPITAL getDAO_HOSPITAL() {
        return DAO_H;
    }
    
    /**
     * Metodo para obter DAO de DRG
     * @return DAO de DRG
     */
    public DAO_DRG getDAO_DRG() {
        return DAO_D;
    }
}
