package Limites.LimitePrincipal;

import Controladores.ControlePrincipal;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LimiteTelaLogin
{
    JFrame frame;
    JButton entrar,sair;
    JTextField login;
    JPasswordField senha;
    private ControlePrincipal objCtrl;

    public LimiteTelaLogin(ControlePrincipal pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar Jbuttons
        entrar = new JButton("Login");
        entrar.setBorderPainted(false);
        entrar.setBackground(new Color(0,0,128));
        entrar.setForeground(Color.WHITE);
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.WHITE);
        
        //Criar TextFields
        login = new JTextField();
        login.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Login:"));
        senha = new JPasswordField();
        senha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Senha:"));
        
        //Adicionar Listener aos botoes e ao campo de senha
        ActionListener go  = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                objCtrl.ExibirInterfacePrincipal();
            }
        };
        entrar.addActionListener(go);
        senha.addActionListener(go);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        //Criar paineis
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        botoes.setBackground(Color.white);
        JPanel p1 = new JPanel(new GridLayout(3, 1));
        p1.setBackground(Color.white);
        JPanel p2 = new JPanel();
        BoxLayout box0 = new BoxLayout(p2, BoxLayout.X_AXIS);
        p2.setLayout(box0);
        p2.setBackground(Color.white);
        JPanel sub = new JPanel();
        BoxLayout box = new BoxLayout(sub, BoxLayout.Y_AXIS);
        sub.setLayout(box);
        sub.setBackground(Color.white);
        sub.setBorder(BorderFactory.createLineBorder(new Color(0,0,128)));
        
        //Adicionar componentes a seus paineis
        botoes.add(sair);
        botoes.add(entrar);
        p1.add(login);
        p1.add(senha);
        p1.add(botoes);
        
        p2.add(Box.createHorizontalGlue());
        p2.add(p1);
        p2.add(Box.createHorizontalGlue());
        
        sub.add(new JLabel(new ImageIcon("img/logo_login.jpeg")));
        sub.add(p2);
        sub.add(Box.createGlue());
        
        //Criar Frame e setar opçoes
        frame = new JFrame();
        frame.add(sub);
        frame.setAlwaysOnTop(true);
        frame.setUndecorated(true);
        frame.setSize(500,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    
}
