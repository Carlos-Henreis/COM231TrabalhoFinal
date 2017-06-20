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
    private JButton sair,pdf,svg;
    private DefaultCategoryDataset dados;
    private JFreeChart graficoBarras;
    private ChartPanel painelGrafico;
    private ArrayList<RelatorioContagemDRGEstado> listaDados;
    private ControleRelatorios objCtrl;
    private JFrame janela;
    
    
    private final ImageIcon pdfIcone = new ImageIcon("img/pdf1.png");
    private final ImageIcon svgIcone = new ImageIcon("img/svg1.png");
    
    public LimiteRelatorioContagemDrgAtendidaPorEstado(ControleRelatorios pCtrl,ArrayList<RelatorioContagemDRGEstado> lista)
    {
        objCtrl = pCtrl;
        listaDados = lista;
        
        //Criar botoes
        pdf = new JButton(pdfIcone);
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        sair = new JButton("SAIR");
        sair.setForeground(new Color(0,0,128));
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });
        svg = new JButton(svgIcone);
        svg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        //Criar grafico
        painelGrafico = gerarGraficoBarrasMenoresValores();
        
        //Criar demais paineis
        topExportar = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        grafico = new JPanel(new FlowLayout(FlowLayout.CENTER));
        principal = new JPanel(new BorderLayout());
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes a seus devidos paineis
        topExportar.add(pdf);
        topExportar.add(svg);
        topExportar.add(sair);
        painelRolagem.getViewport().add(painelGrafico);
        grafico.add(painelGrafico);
        principal.add(topExportar,BorderLayout.PAGE_START);
        principal.add(grafico,BorderLayout.CENTER);
        
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
        for(RelatorioContagemDRGEstado rel : listaDados)
        {
            dados.addValue(rel.getContagem(),rel.getEstado(),rel.getEstado());
        }

        //Gerar Grafico
        graficoBarras = ChartFactory.createBarChart("Drg's menos custosas do estado","Valor da DRG","Nome da DRG", dados);        
        graficoBarras.setBackgroundPaint(Color.white);
        
        //gerar paineis
        painelGrafico = new ChartPanel(graficoBarras);
        painelGrafico.setSize(800,600);
        
        return painelGrafico;        
    }
    
    public void gerarPDF()
    {
        
    }
    
    public void gerarSVG()
    {
        
    }
}
