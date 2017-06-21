package Limites.Relatorios;

import Controladores.ControleRelatorios;
import Model.RelatorioContagemDRGEstado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class LimiteRelatorioContagemDrgAtendidaPorEstado
{
    private JScrollPane painelRolagem;
    private JPanel principal, grafico,topExportar;
    private JButton sair,pdf;
    private DefaultCategoryDataset dados;
    private JFreeChart graficoBarras;
    private ChartPanel painelGrafico;
    private ArrayList<RelatorioContagemDRGEstado> listaDados;
    private ControleRelatorios objCtrl;
    private JFrame janela;
    
    
    private final ImageIcon pdfIcone = new ImageIcon("img/pdf1.png");
    private final ImageIcon sairIcone = new ImageIcon("img/exit.png");
    
    public LimiteRelatorioContagemDrgAtendidaPorEstado(ControleRelatorios pCtrl,ArrayList<RelatorioContagemDRGEstado> lista)
    {
        objCtrl = pCtrl;
        listaDados = lista;
        
        //Criar botoes
        pdf = new JButton(pdfIcone);
        pdf.setBackground(new Color(0,0,128));
        pdf.setBorderPainted(false);
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarPDF();
            }
        });
        sair = new JButton(sairIcone);
        sair.setBackground(new Color(0,0,128));
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });

        //Criar grafico
        painelGrafico = gerarGraficoBarrasMenoresValores();
        
        //Criar demais paineis
        topExportar = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        grafico = new JPanel(new FlowLayout(FlowLayout.CENTER));
        principal = new JPanel(new BorderLayout());
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes a seus devidos paineis
        topExportar.add(pdf);
        topExportar.add(sair);
        painelRolagem.getViewport().add(painelGrafico);
        principal.add(topExportar,BorderLayout.PAGE_START);
        principal.add(painelRolagem,BorderLayout.CENTER);
        
        //Criar JFrame e setar opçoes
        janela = new JFrame();
        janela.add(principal);
        janela.setUndecorated(true);
        janela.setSize(800, 600);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setLocationRelativeTo(null);
        janela.setAlwaysOnTop(true);
        janela.setVisible(true);
    }
    
    public final ChartPanel gerarGraficoBarrasMenoresValores()
    {
        //Gerar dados1 do grafmaioresVal
        dados = new DefaultCategoryDataset();
        for(int i=0 ; i<5 ; i++)
        {
            RelatorioContagemDRGEstado rel = listaDados.get(i);
            dados.addValue(rel.getContagem(),rel.getEstado(),"Numero de DRG's atendidas");
            
        }
        
        for(int i = listaDados.size()-1 ; i> listaDados.size()-6 ; i--)
        {
            RelatorioContagemDRGEstado rel = listaDados.get(i);
            dados.addValue(rel.getContagem(),rel.getEstado(),"Numero de DRG's atendidas");
        }
        
        /*for(RelatorioContagemDRGEstado rel : listaDados)
        {
            dados.addValue(rel.getContagem(),rel.getEstado(),rel.getEstado());
        }*/

        //Gerar Grafico
        graficoBarras = ChartFactory.createBarChart("Quantidade de DRG's atendidas por estado","Estados listados","Numero de DRG's atendidas", dados);        
        graficoBarras.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGrafico = new ChartPanel(graficoBarras);
        painelGrafico.setSize(4000,2000);
        
        return painelGrafico;        
    }
    
    /**
     * Gerar um pdf a partir dos dados filtrados no relatorio
     */
    public void gerarPDF()
    {
        JTable tabela;
        String dadosTabela[][] = new String[listaDados.size()][2];
        String cabecalho[] = {"Estado","Numero de DRG's Atendidas"};
        int i=0;
        
        for(RelatorioContagemDRGEstado rel : listaDados)
        {
            dadosTabela[i][0] = rel.getEstado();
            dadosTabela[i][1] = ""+rel.getContagem();
            i++;
        }
        tabela = new JTable(dadosTabela, cabecalho);
        
        objCtrl.gerarPDF("rel_NumDRGsAtendidasPorEstado", "Relatorio de numero de DRG's atendidas por cada estado", cabecalho, tabela);
    }
}
