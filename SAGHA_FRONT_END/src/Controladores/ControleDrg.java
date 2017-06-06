package Controladores;

import Model.Drg;
import Limites.DRG.*;
import Model.DAO_DRG;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Desenvolvedores SAGHA
 */
public class ControleDrg
{
    private ArrayList<Drg> listaDrg;
    private LimiteCadastroDRG limCad;
    private LimiteVisualizacaoDRG limVis;
    private LimiteAtualizacaoDRG limAtt;
    private LimiteRemocaoDRG limDel;
    private DAO_DRG DAO;
    
    public ControleDrg(Session session)
    {
        listaDrg = new ArrayList<>();
        DAO = new DAO_DRG(session);
    }
    
    public void interfaceCadastroDRG()
    {
        limCad = new LimiteCadastroDRG(this);
    }
    
    public void interfaceVisualizacaoDRG(String dados[][])
    {
        limVis = new LimiteVisualizacaoDRG(dados);
    }
    
    public void interfaceAtualizacaoDRG()
    {
        limAtt = new LimiteAtualizacaoDRG(this);
    }
    
    public void interfaceRemocaoDRG()
    {
        limDel = new LimiteRemocaoDRG(this);
    }
    
    /**
     * Metodo que cadastra uma DRG no sistema
     */
    public void cadastrarDrg()
    {
        try{
            String form[] = limCad.getDados();
            Drg d = new Drg(Short.parseShort(form[0]),form[1]);
            if(DAO.cadastrarDrg(d))
            {
                limCad.limparFormulario();
                limCad.mensagemSucesso();
            }
            else
            {
                limDel.mensagemErro("Nao foi possivel cadastrar a DRG!");
            }
        }catch(Exception exc){
            limCad.mensagemErro("Verifique os dados informados!\nFalha ao cadastrar DRG!");
        }
    }
    
    /**
     * Metodo que remove uma Drg do sistema
     */
    public void removerDrg()
    {
        try{
            int codigo = limDel.getCodigo();
            
            if(DAO.removerDrg(codigo))
                limDel.mensagemSucesso();
            else
                limDel.mensagemErro("Falha ao remover DRG\nVerifique os dados informados!");
        }catch(Exception exc){
            limDel.mensagemErro("Verfique os dados informados!");
        }
    }
    
    /**
     * Metodo que exibe todas as DRG's cadastradas no sistema
     */
    public void listarDrgs()
    {
        String form[][];
        listaDrg = DAO.listarDRGs();
        
        form = new String[listaDrg.size()][2];
        
        for(int i=0 ; i<listaDrg.size() ; i++)
        {
            Drg d = listaDrg.get(i);
            form[i][0] = ""+d.getCodigo();
            form[i][1] = d.getDefinicao();
        }
        
        interfaceVisualizacaoDRG(form);
    }
    
    public void atualizarDadosDrg()
    {
        try{
            String form[] = limAtt.getDados();
            
            Drg d = DAO.getDrg(Short.parseShort(form[0]));
            d.setDefinicao(form[1]);
            
            if(DAO.atualizarDadosDrg(d))
            {
                limAtt.limparFormulario();
                limAtt.mensagemSucesso();
            }
            else
            {
                limAtt.mensagemErro("Falha ao atualizar dados da DRG!");
            }
        }catch(Exception exc){
           limAtt.mensagemErro("Verifique se voce informou todos os campos!\nO codigo deve ser numerico!");
        }
    }
}