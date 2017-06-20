package Controladores;

import Limites.Relatorios.*;
import Model.*;
import java.util.ArrayList;
import org.hibernate.Session;

public class ControleRelatorios
{
    private DAO_RELATORIOS DAO;
    private Session sessao;
    private ArrayList <RelatorioPagMedioDrgEstado> listaDados;
    private LimiteRelatorioPagamentoMedioDrgEstado limRelatorioPagMedioDrgEstado;
    private LimiteRelatorioPagamentoMedioDRG limRelatorioPagMedioDRG;
    private LimiteRelatorioContagemDrgAtendidaPorEstado limRelatorioContagemDrgAtendidaEstado;
    private LimiteRelatorioGeralDRG limRelatorioGeralDRG;
    private LimiteRelatorioAtendimentoDrgRegReferencia limRelatorioPorRegiao;
    
    public ControleRelatorios(Session sessao)
    {
        this.sessao = sessao;
        DAO = new DAO_RELATORIOS(sessao);
        listaDados = new ArrayList<>();
    }
    
    /**
     * Exibe o GUI do relatorio ao usuario
     */
    public void interfaceRelatorioPagMedioDrgEstado()
    {
        limRelatorioPagMedioDrgEstado = new LimiteRelatorioPagamentoMedioDrgEstado(this,DAO.obterListaDeEstados(), listaDados, listaDados);
    }
    
    /**
     * Atualiza a interface do relatorio com os novos dados do estado selecionado
     * @param estado o estado selecionado pelo usuario
     */
    public void atualizarInterfaceRelatorioPagMedioDrgEstado(String estado)
    {
        listaDados = DAO.relatorioPagMedioDrgEstado(estado);
        
        //Gerar sublistas com os maiores e menores valores
        ArrayList<RelatorioPagMedioDrgEstado> maior = new ArrayList<>();
        ArrayList<RelatorioPagMedioDrgEstado> menor = new ArrayList<>();
        
        //Adicionar dados a lista com os menores valores
        for(int i=0 ; i<5 ; i++)
        {
            if(listaDados.size() > i)
                menor.add(listaDados.get(i));
        }
        
        int dim = listaDados.size();
        if(listaDados.size() >= 5)
            dim = 5;
        else
            dim = listaDados.size();
        
        //Adicionar dados a lista com os maiores valores
        for(int j=1 ; j<=dim ; j++)
        {
            maior.add(listaDados.get(listaDados.size()-j));
        }
        
        //Atualizar a interface
        limRelatorioPagMedioDrgEstado.atualizarParaNovoEstado(maior, menor);
    }
    
    /**
     * Exibir interface do relatorio de pagamentos medios de acordo com DRG
     */
    public void interfaceRelatorioPagamentoMedioDRG()
    {
        listaDados = DAO.relatorioPagMedioPorDrg();
        String dados[][] = new String[listaDados.size()][2];
        
        int i=0;
        for(RelatorioPagMedioDrgEstado rel : listaDados)
        {
            dados[i][0] = rel.getDefinicao();
            dados[i][1] = ""+rel.getMedia();
            i++;
        }
        
        limRelatorioPagMedioDRG = new LimiteRelatorioPagamentoMedioDRG(this, dados);
    }
    
    /**
     * Exibe a interface do relatorio de contagem de DRG's atendidas em cada estado
     */
    public void interfaceRelatorioContagemDrgAtendidaPorEstado()
    {
        limRelatorioContagemDrgAtendidaEstado = new LimiteRelatorioContagemDrgAtendidaPorEstado(this,DAO.relatorioContNumeroDRGsEstado());
    }
    
    /**
     * Exibe a interface do relatorio geral de DRG
     */
    public void interfaceRelatorioGeralDRG()
    {
        limRelatorioGeralDRG = new LimiteRelatorioGeralDRG(this, DAO.relatorioDRG(1));
    }
    
    /**
     * Atualizar dados do relatorio - Usuario acionou o criterio de ordenaçao
     * @param criterio criterio de ordenacao escolhido
     */
    public void atualizarRelatorioGeral(int criterio)
    {
        limRelatorioGeralDRG.atualizarInterface(DAO.relatorioDRG(criterio));
    }
    
    /**
     * Visualizar relatorio de DRGs atendidas de acordo com a regiao de referencia
     */
    public void interfaceRelatorioDrgRegiaoReferecia()
    {
        limRelatorioPorRegiao = new LimiteRelatorioAtendimentoDrgRegReferencia(this, DAO.relatorioNumDRGPorRef());
    }
}
