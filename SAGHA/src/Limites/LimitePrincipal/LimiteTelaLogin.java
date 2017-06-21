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
    JTextField cpf;
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
        cpf = new JTextField();
        cpf.setText("00000000002");
        cpf.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"CPF:"));
        senha = new JPasswordField();
        senha.setText("VICTOR");
        senha.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Senha:"));
        
        //Adicionar Listener aos botoes e ao campo de senha
        ActionListener go  = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.login();
            }
        };
        entrar.addActionListener(go);
        senha.addActionListener(go);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                objCtrl.encerrarSagha();
                System.exit(0);
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
        sub.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes a seus paineis
        botoes.add(sair);
        botoes.add(entrar);
        p1.add(cpf);
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
    
    /**
     * Metodo para ser executado apos cpf do sistema
     */
    public void sucessoLogin(String nomeusuario,String funcao)
    {
        JOptionPane.showMessageDialog(frame,"Bem vindo ao SAGHA Sr(a). "+nomeusuario+"!");
        
        frame.dispose();
        objCtrl.ExibirInterfacePrincipal(funcao);
    }
    
    /**
     * Exibir mensagem de erro ao usuario
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(frame,msg,"Informaçao do sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Obter dados da interface de usuario
     * @return cpf e senha
     */
    public String[] obterDadosInterface() throws Exception
    {        
        if(cpf.getText().isEmpty() || senha.getText().isEmpty())
            throw new Exception("Voce deve informar todos os campos!");
        
        String form[] = {cpf.getText(),senha.getText()};
        return form;
    }
}
