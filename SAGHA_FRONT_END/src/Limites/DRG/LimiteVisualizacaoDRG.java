package Limites.DRG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LimiteVisualizacaoDRG
{
    private JTable tabela;
    private JScrollPane painelRolagem;
    private JPanel painel,principal;
    private JButton sair;
    private JFrame frame;

    public LimiteVisualizacaoDRG(String dados[][])
    {
        String nomes[] = {"CODIGO","DESCRIÇAO"};
        
        //Criar JButton e adicionar listener
        sair = new JButton("SAIR");
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.white);
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
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
        principal.add(sair,BorderLayout.PAGE_END);
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Listagem dos grupos relacionados de diagnostico");
        frame.add(principal);
        frame.setSize(800, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }    
}
