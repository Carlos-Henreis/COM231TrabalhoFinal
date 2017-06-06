package Controladores;

import Limites.AtendimentoDRG.*;
import Model.*;
import java.util.ArrayList;
import org.hibernate.Session;

public class ControleAtendimentoDRG
{
    private DAO_ATENDIMENTO_DRG DAO;
    private ArrayList<AtendimentoDrg> lista;
    private LimiteCadastroAtendimentoDRG limCad;
    private LimiteVisualizacaoAtendimentoDRG limVis;
    private LimiteAtualizacaoAtendimentoDRG limAtt;

    public ControleAtendimentoDRG(Session sessao)
    {
        lista = new ArrayList<>();
        DAO =  new DAO_ATENDIMENTO_DRG(sessao);
    }
    
    public void interfaceCadastroAtendimentoDRG()
    {
        limCad = new LimiteCadastroAtendimentoDRG(this);
    }
    
    public void interfaceVisualizacaoAtendimentos(String form[][])
    {
        limVis =  new LimiteVisualizacaoAtendimentoDRG(form);
    }
    
    public void interfaceAtualizacaoAtendimentoDRG()
    {
        limAtt = new LimiteAtualizacaoAtendimentoDRG(this);
    }
    
    /**
     * Metodo que exibe ao usuario todos os atendimentos a DRG cadastrados
     */
    public void exibirAtendimentosDRG()
    {
        String form[][];
        lista = DAO.obterListaAtendimentoDRGs();
        form = new String[lista.size()][6];
        
        for(int i=0 ; i<lista.size() ; i++)
        {
            AtendimentoDrg att = lista.get(i);
            form[i][0] = ""+att.getHospital().getId()+" - "+att.getHospital().getNome();
            form[i][1] = ""+att.getDrg().getCodigo()+" - "+att.getDrg().getDefinicao();
            form[i][2] = ""+att.getNumeroaltas();
            form[i][3] = ""+att.getTaxasmediascobertas();
            form[i][4] = ""+att.getPagamentosmediostotais();
            form[i][5] = ""+att.getMediapagamentosmedicare();
        }
        
        interfaceVisualizacaoAtendimentos(form);
    }
    
    /**
     * Metodo que verifica se os dados do atendimento informado estao corretos
     */
    public void cadastrarAtendimentoDRG()
    {
        try {
            String form[] = limCad.getDados();
            
            AtendimentoDrgId id = new AtendimentoDrgId(Integer.parseInt(form[0]),Short.parseShort(form[1]));
            Hospital hosp = DAO.getDAO_HOSPITAL().getHospital(id.getIdhospital());
            
            if(hosp == null)
                limCad.mensagemErro("Nenhum hospital encontrado!");
            else
            {
                Drg drg = DAO.getDAO_DRG().getDrg(id.getCodigodrg());
                
                if(drg == null)
                    limCad.mensagemErro("Nenhuma DRG encontrada com os dados informados!");
                else
                {
                    AtendimentoDrg att = new AtendimentoDrg(id, drg, hosp,Short.parseShort(form[2]),Float.parseFloat(form[3]),Float.parseFloat(form[4]),Float.parseFloat(form[5]));
                    
                    if(DAO.cadastrarAtendimentoDRG(att))
                    {
                        limCad.limparFormulario();
                        limCad.mensagemSucesso();
                    }
                    else
                        limCad.mensagemErro("Falha ao cadastrar atendimento!\nVerifique os dados informados!");
                }
            }
        } catch (Exception exc) {
            limCad.mensagemErro("Erro ao cadastrar atendimento!\nVerifique os dados informados!");
        }
    }
    
    /**
     * Metodo que busca a DRG no banco e atualiza a interface de usuario
     */
    public void atualizacaoParte1_BUSCA()
    {
        try {
            String form[] = limAtt.getIdAtendimento();
            AtendimentoDrgId id = new AtendimentoDrgId(Integer.parseInt(form[1]),Short.parseShort(form[0]));
            
            AtendimentoDrg att = DAO.getAtendimentoDrg(id);
            
            if(att == null)
                limAtt.mensagemErro("O atendimento nao foi encontrado!\nVerifique os dados informados!");
            else
            {
                limAtt.AtualizarInterface(att);
            }
        } catch (Exception exc) {
            limAtt.mensagemErro("Verifique os dados informados!");
        }
    }
    
    /**
     * Metodo que atualiza o atendimento a DRG com os novos dados
     */
    public void atualizacaoParte2_CADASTRO()
    {
        try {
            AtendimentoDrg att = limAtt.getAtendimentoAtualizado();
            
            if(DAO.atualizarDadosAtendimentoDRG(att))
            {
                limAtt.limparFormulario();
                limAtt.mensagemSucesso();
            }
            else
                limAtt.mensagemErro("Falha ao atualizar dados de atendimento!\n"
                        + "Verfique os dados informados");
        } catch (Exception exc) {
            limAtt.mensagemErro(exc.getMessage());
        }
    }
}
