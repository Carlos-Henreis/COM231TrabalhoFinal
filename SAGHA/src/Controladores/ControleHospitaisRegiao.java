package Controladores;

import Limites.HospitalRegiao.*;
import Model.DAO_HOSPITAIS_REGIAO;
import Model.HospitaisRegiao;
import Model.HospitaisRegiaoId;
import Model.Hospital;
import org.hibernate.Session;
/**
 *
 * @author Desenvolvedores SAGHA
 */
public class ControleHospitaisRegiao
{
    private LimiteCadastroHospitalRegiao limCad;
    private LimiteAtualizacaoHospitalRegiao limAtt;
    private DAO_HOSPITAIS_REGIAO DAO;
    
    public ControleHospitaisRegiao(Session sessao)
    {
        DAO = new DAO_HOSPITAIS_REGIAO(sessao);
    }
    
    public void interfaceCadastroHospitalRegiao()
    {
        limCad = new LimiteCadastroHospitalRegiao(this);
    }
    
    public void interfaceAtualizacaoHospitalRegiao()
    {
        limAtt = new LimiteAtualizacaoHospitalRegiao(this);
    }
    
    /**
     * Cadastra a regiao de referencia de um hospital no banco de dados
     */
    public void cadastrarRegiaoHospital()
    {
        try {
            String form[] = limCad.getDados();
            
            Hospital h = DAO.getDAO_HOSPITAL().getHospital(Integer.parseInt(form[0]));
            
            if(h == null)
                limCad.mensagemErro("Nenhum hospital com o codigo informado cadastrado!");
            else
            {
                HospitaisRegiao hr = new HospitaisRegiao(new HospitaisRegiaoId(h.getId(),form[1]), h);
                
                if(DAO.cadastrarHospitalRegiao(hr))
                {
                    limCad.limparFormulario();
                    limCad.mensagemSucesso();
                }
                else
                    limCad.mensagemErro("Falha ao cadastrar regiao de referencia de hospital!");
                
            }
        } catch (Exception exc) {
            limCad.mensagemErro(exc.getMessage());
        }
    }
    
    public void atualizarRegiaoHospital()
    {
        
    }
}
