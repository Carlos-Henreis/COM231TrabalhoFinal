package Model;

import java.util.Iterator;
import org.hibernate.*;

/**
 *  Classe que "trabalha" na tabela hospiais_regiao no banco de dados
 * @author Desenvolvedores SAGHA
 */
public class DAO_HOSPITAIS_REGIAO
{
    private Session sessao;
    private Transaction tr;
    private DAO_HOSPITAL DAO_HOSP;

    public DAO_HOSPITAIS_REGIAO(Session psessao)
    {
        sessao = psessao;
        DAO_HOSP = new DAO_HOSPITAL(sessao);
    }
    
    /**
     * Obter DAO de hospital
     * @return DAO de hospital
     */
    public DAO_HOSPITAL getDAO_HOSPITAL() {
        return DAO_HOSP;
    }
    /**
     * Metodo que cadastra a regiao de um hospital
     * @param hr hospital_Regiao a ser cadastrado
     * @return status do cadastro
     */
    public boolean cadastrarHospitalRegiao(HospitaisRegiao hr)
    {
        sessao.save(hr);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que atualiza a regiao de um hospital
     * @param hr hospital_Regiao a ser atualizado
     * @return status da atualizacao
     */
    public boolean atualizarHospitalRegiao(HospitaisRegiao hr)
    {
        sessao.update(hr);
        tr = sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que busca a regiao de um hospital no banco
     * @param id identificador do hospital
     * @return hospital e sua regiao
     */
    public HospitaisRegiao getHospital(int id)
    {
        Iterator it = sessao.createQuery("from HospitaisRegiao where idhospital = "+id).list().iterator();
        if(it.hasNext())
        {
            HospitaisRegiao hr = (HospitaisRegiao) it.next();
            return hr;
        }
        
        //Hospital nao encontrado pelo codigo
        return null;
    }
}
