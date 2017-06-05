package Limites.AtendimentoDRG;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LimiteVisualizacaoAtendimentoDRG
{
    private JTable tabela;
    private JScrollPane painelRolagem;
    private JPanel painel,principal;
    private JFrame frame;
    private JButton sair;

    public LimiteVisualizacaoAtendimentoDRG(String dados[][])
    {
        String nomes[] = {"CODIGO DRG","ID HOSPITAL","NUMERO ALTAS","TX MED. COBERTAS","PAG. MED. TOTAIS","MED. PAG. MEDICARE"};
        
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
        principal.add(painel,BorderLayout.CENTER);
        principal.add(Box.createVerticalGlue(),BorderLayout.PAGE_START);
        principal.add(Box.createVerticalGlue(),BorderLayout.LINE_START);
        principal.add(Box.createVerticalGlue(),BorderLayout.LINE_END);
        principal.add(sair,BorderLayout.PAGE_END);
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Listagem do atendimento a DRG's");
        frame.add(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }    
}
