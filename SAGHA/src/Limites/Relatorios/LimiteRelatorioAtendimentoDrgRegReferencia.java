package Limites.Relatorios;

import Controladores.ControleRelatorios;
import Model.RelatorioNumDRGPorRef;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class LimiteRelatorioAtendimentoDrgRegReferencia
{
    private JTable tabela;
    private JScrollPane painelRolagem;
    private JPanel topExportar,principal;
    private JButton sair,pdf,svg;
    private ControleRelatorios objCtrl;
    private String dadosRelatorio[][];
    private ArrayList<RelatorioNumDRGPorRef> listaDados;
    private JFrame janela;

    private final ImageIcon pdfIcone = new ImageIcon("img/pdf1.png");
    private final ImageIcon svgIcone = new ImageIcon("img/svg1.png");
    
    public LimiteRelatorioAtendimentoDrgRegReferencia(ControleRelatorios pCtrl,ArrayList<RelatorioNumDRGPorRef> lista)
    {
        objCtrl = pCtrl;
        listaDados = lista;
        
        //Criar JTable
        String colunas[] = {"Estado de referencia","Numero de DRG's Atendidas"};
        dadosRelatorio = new String[listaDados.size()][2];
        int i=0;
        for(RelatorioNumDRGPorRef rel : listaDados)
        {
            dadosRelatorio[i][0] = rel.getRegiao_referencia();
            dadosRelatorio[i][1] = ""+rel.getQtd_DRG();
            i++;
        }
        
        tabela = new JTable(dadosRelatorio, colunas);
        
        //Criar painel de rolagem e adicionar tabela
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelRolagem.getViewport().add(tabela);
        
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
        
        //Criar paineis
        principal = new JPanel(new BorderLayout());
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        topExportar = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        
        //Adicionar componentes a seus paineis
        topExportar.add(pdf);
        topExportar.add(svg);
        topExportar.add(sair);
        
        principal.add(topExportar,BorderLayout.PAGE_START);
        principal.add(painelRolagem,BorderLayout.CENTER);
        
        //Criar JFrame e definir suas configuracoes
        janela = new JFrame();
        janela.add(principal);
        janela.setUndecorated(true);
        janela.setSize(800, 600);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setLocationRelativeTo(null);
        janela.setAlwaysOnTop(true);
        janela.setVisible(true);
    }
    
    /**
     * GERA UM .PDF DO RELATORIO
     */
    public void exportarPDF()
    {
        
    }
    
    /**
     * GERA UM .SVG DO RELATORIO
     */
    public void exportarSVG()
    {
        
    }
}
