package Limites.LimitePrincipal;

import Controladores.ControlePrincipal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Desenvolvedores SAGHA
 */
public class SaghaDashboard
{
    private JFrame tela;
    private JPanel principal,pcreditos;
    private JMenuBar barraMenu;
    private JLabel creditos;
    private JMenu publico;
    private JButton administrativo;
    private ControlePrincipal objCtrl;
    
    private final ImageIcon logoIMG = new ImageIcon("img/sagha_dashboard.png");
    private final ImageIcon publicoIMG = new ImageIcon("img/publico.png");
    private final ImageIcon privadoIMG = new ImageIcon("img/privado.png");
    
    public SaghaDashboard(ControlePrincipal pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar JMenuBar
        barraMenu = new JMenuBar();
        
        //Criar JMenu's
        publico = new JMenu("Publico");
        administrativo = new JButton("Administrativo");
        administrativo.setBorderPainted(false);
        administrativo.setBackground(Color.white);
        administrativo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                tela.dispose();
                objCtrl.interfaceDeLogin();
            }
        });
        
        //Criar label
        creditos = new JLabel("UNIFEI, Desenvolvedores sagha | 1º Semestre de 2017.");
        
        //Adicionar itens a barra de menu
        barraMenu.add(publico);
        barraMenu.add(administrativo);
        
        //Criar painel e adicionar componentes a ele
        pcreditos = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pcreditos.setBackground(Color.white);
        pcreditos.add(creditos);
        principal = new JPanel(new BorderLayout());
        principal.setBackground(Color.white);
        principal.add(new JLabel(logoIMG),BorderLayout.CENTER);
        principal.add(pcreditos,BorderLayout.PAGE_END);        
        
        tela = new JFrame("Sistema de apoio a gestao de hospitais americanos - DASHBOARD");
        tela.add(principal);
        tela.setSize(1000,700);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setJMenuBar(barraMenu);
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setAlwaysOnTop(true);
        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }
    
}
