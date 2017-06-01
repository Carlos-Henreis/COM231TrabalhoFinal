package Limites.DRG;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.*;

public class LimiteVisualizacaoDRG
{
    private JTable tabela;
    private JScrollPane painelRolagem;
    private JPanel painel,principal;
    private JFrame frame;

    public LimiteVisualizacaoDRG(String dados[][])
    {
        String nomes[] = {"CODIGO","DESCRIÇAO"};
        
        //Criar JTable
        tabela = new JTable(dados, nomes);
        tabela.setBackground(new Color(245,245,245));
        
        //Criar painel de rolagem
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelRolagem.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //Adicionar tabela ao painel de rolagem
        painelRolagem.getViewport().add(tabela);
        
        //Criar painel
        painel = new JPanel(new BorderLayout(50,50));
        painel.add(painelRolagem,BorderLayout.CENTER);
        principal = new JPanel(new BorderLayout(100, 100));
        //principal.setBackground(Color.white);
        principal.add(painel,BorderLayout.CENTER);
        principal.add(Box.createVerticalGlue(),BorderLayout.PAGE_START);
        principal.add(Box.createVerticalGlue(),BorderLayout.LINE_START);
        principal.add(Box.createVerticalGlue(),BorderLayout.LINE_END);
        principal.add(Box.createVerticalGlue(),BorderLayout.PAGE_END);
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Listagem dos grupos relacionados de diagnostico");
        frame.add(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }    
}
