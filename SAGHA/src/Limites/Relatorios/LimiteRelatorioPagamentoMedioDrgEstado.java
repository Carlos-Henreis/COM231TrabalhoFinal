package Limites.Relatorios;

import Controladores.ControleRelatorios;
import Model.RelatorioPagMedioDrgEstado;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.data.category.DefaultCategoryDataset;

public class LimiteRelatorioPagamentoMedioDrgEstado
{
    private JComboBox estadosCB;
    private JPanel principal,central,topCombo,fundo;
    private JButton sair,pdf;
    private ChartPanel painelGraficoMaior,painelGraficoMenor;
    private JFreeChart grafmaioresVal,grafmenoresVal;
    private DefaultCategoryDataset dados1,dados2;
    private BoxLayout box;
    private ArrayList<RelatorioPagMedioDrgEstado> maioresValores,menoresValores;
    private JFrame tela;
    private ControleRelatorios objCtrl;
    /**
     * Gera a interface do LimiteRelatorio de pagamentos medios de DRG por estado
     * @param lista lista de estados disponiveis
     * @param maioresVal lista com as 5 DRG's mais caras do estado
     * @param menoresVal lista com as 5 DRG's mais baratas do estado
     */
    private final ImageIcon pdfIcone = new ImageIcon("img/pdf1.png");
    private final ImageIcon sairIcone = new ImageIcon("img/exit.png");
    
    public LimiteRelatorioPagamentoMedioDrgEstado(ControleRelatorios pCtrl,String lista[],ArrayList<RelatorioPagMedioDrgEstado> maioresVal,ArrayList<RelatorioPagMedioDrgEstado> menoresVal)
    {
        objCtrl = pCtrl;
        
        maioresValores = menoresVal;
        menoresValores = menoresVal;
        
        //Criar JComboBox
        estadosCB = new JComboBox();
        estadosCB.addItem("Nenhum estado selecionado");
        for(String st : lista)
            estadosCB.addItem(st);
        estadosCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(estadosCB.getSelectedIndex() == 0)
                    central.setVisible(false);
                else
                {
                    String estado = (String) estadosCB.getItemAt(estadosCB.getSelectedIndex());
                    objCtrl.atualizarInterfaceRelatorioPagMedioDrgEstado(estado);
                }
            }
        });
        
        //Criar Jbutton
        sair = new JButton(sairIcone);
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.white);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tela.dispose();
            }
        });
        pdf = new JButton(pdfIcone);
        pdf.setBackground(new Color(0,0,128));
        pdf.setForeground(Color.white);
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        //Gerar paineis com os graficos
        painelGraficoMaior = gerarGraficoBarrasMaioresValores();
        painelGraficoMenor = gerarGraficoBarrasMenoresValores();
        
        //Criar paineis
        fundo = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        fundo.setBackground(new Color(0,0,128));
        topCombo = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        topCombo.setBackground(Color.white);
        JPanel subGrafico1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subGrafico1.setBackground(Color.white);
        JPanel subGrafico2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subGrafico2.setBackground(Color.white);
        central = new JPanel();
        box = new BoxLayout(central, BoxLayout.Y_AXIS);
        central.setBackground(Color.white);
        central.setLayout(box);
        principal = new JPanel(new BorderLayout(30, 30));
        principal.setBackground(Color.white);
        
        //Adicionar componentes a seus devidos paineis
        //->PAINEL NO TOPO, COM COMBOBOX
        topCombo.add(new JLabel("Selecione o estado:"));
        topCombo.add(estadosCB);
        //->PAINEL FINAL COM OS BOTOES
        fundo.add(pdf);
        fundo.add(sair);
        //->SUBPAINEIS, UM PARA CADA GRAFICO
        subGrafico1.add(painelGraficoMaior);
        subGrafico2.add(painelGraficoMenor);
        //->PAINEL CENTRAL, COM OS GRAFICOS
        central.add(Box.createGlue());
        central.add(subGrafico1);
        central.add(Box.createGlue());
        central.add(subGrafico2);
        central.add(Box.createGlue());
        central.setVisible(false);
        //->PAINEL PRINCIPAL, COM TUDO DENTRO DELE
        principal.add(topCombo,BorderLayout.PAGE_START);
        principal.add(central,BorderLayout.CENTER);
        principal.add(fundo,BorderLayout.PAGE_END);
        
        //Setar operacoes da JFrame e iniciar tela
        tela = new JFrame();
        tela.add(principal);
        tela.setUndecorated(true);
        tela.setSize(800, 600);
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setVisible(true);
    }
    
    /**
     * Metodo para gerar grafico de barras com os dados dos valores maiores
     * @return painel com o grafico
     */
    public final ChartPanel gerarGraficoBarrasMaioresValores()
    {
        //Gerar dados1 do grafmaioresVal
        dados1 = new DefaultCategoryDataset();
        for(RelatorioPagMedioDrgEstado rel : maioresValores)
        {
            dados1.addValue(rel.getMedia(),rel.getDefinicao(),rel.getDefinicao());
        }

        //Gerar Grafico
        grafmaioresVal = ChartFactory.createBarChart("Drg's mais caras do estado","Valor da DRG","Nome da DRG", dados1);
        grafmaioresVal.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGraficoMaior = new ChartPanel(grafmaioresVal);
        painelGraficoMaior.setSize(800,600);
        
        return painelGraficoMaior;        
    }
    
    /**
     * Metodo para gerar grafico de barras com os dados dos valores menores
     * @return painel com o grafico
     */
    public final ChartPanel gerarGraficoBarrasMenoresValores()
    {
        //Gerar dados1 do grafmaioresVal
        dados2 = new DefaultCategoryDataset();
        for(RelatorioPagMedioDrgEstado rel : menoresValores)
        {
            dados2.addValue(rel.getMedia(),rel.getDefinicao(),rel.getDefinicao());
        }

        //Gerar Grafico
        grafmenoresVal = ChartFactory.createBarChart("Drg's menos custosas do estado","Valor da DRG","Nome da DRG", dados2);
        grafmenoresVal.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGraficoMenor = new ChartPanel(grafmenoresVal);
        painelGraficoMenor.setSize(800,600);
        
        return painelGraficoMenor;        
    }
    
    public void atualizarParaNovoEstado(ArrayList<RelatorioPagMedioDrgEstado> maioresVal,ArrayList<RelatorioPagMedioDrgEstado> menoresVal)
    {
        //Atualizar dados dos Array's
        maioresValores = maioresVal;
        menoresValores = menoresVal;
        
        //Gerar novo grafico com dados de maior valor
        dados1 = new DefaultCategoryDataset();
        for(RelatorioPagMedioDrgEstado rel : maioresVal)
        {
            dados1.addValue(rel.getMedia(),rel.getDefinicao(),rel.getDefinicao());
        }

        //Gerar Grafico
        grafmaioresVal = ChartFactory.createBarChart("Drg's mais caras do estado","Valor da DRG","Nome da DRG", dados1);
        grafmaioresVal.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGraficoMaior = new ChartPanel(grafmaioresVal);
        painelGraficoMaior.setSize(800,600);
        
        
        //Gerar novo grafico com dados de menor valor
        dados2 = new DefaultCategoryDataset();
        for(RelatorioPagMedioDrgEstado rel : menoresVal)
        {
            dados2.addValue(rel.getMedia(),rel.getDefinicao(),rel.getDefinicao());
        }

        //Gerar Grafico
        grafmenoresVal = ChartFactory.createBarChart("Drg's menos custosas do estado","Valor da DRG","Nome da DRG", dados2);
        grafmenoresVal.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGraficoMenor = new ChartPanel(grafmenoresVal);
        painelGraficoMenor.setSize(800,600);
        
        //Gerar subpaineis e adicionar os graficos
        JPanel subGrafico1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subGrafico1.setBackground(Color.white);
        subGrafico1.add(painelGraficoMaior);
        JPanel subGrafico2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subGrafico2.setBackground(Color.white);
        subGrafico2.add(painelGraficoMenor);
        
        //Atualizar painel central da JFrame
        central.removeAll();
        central.add(Box.createGlue());
        central.add(subGrafico1);
        central.add(Box.createGlue());
        central.add(subGrafico2);
        central.add(Box.createGlue());
        central.setVisible(true);
        central.revalidate();
        
        //Atualizar painel principal e JFrame
        principal.revalidate();
        tela.revalidate();
    }
}
