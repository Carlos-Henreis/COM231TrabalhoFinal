package Limites.Usuarios;

import Controladores.ControlePrincipal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteRemocaoUsuario
{
    private JFrame frame;
    private JButton remover,sair;
    private JTextField cpfTF;
    private JRadioButton sim;
    private JLabel cpfJL,confirmacaoJL;
    private JPanel pcod,pconfirm,psair,principal;
    private BoxLayout box;
    private ActionListener cadListener,sairListener;
    private ControlePrincipal objCtrl;

    public LimiteRemocaoUsuario(ControlePrincipal pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar text fields
        cpfTF = new JTextField(12);
        
        //Criar JLabel's
        cpfJL     = new JLabel("CPF do usuario:");
        confirmacaoJL  = new JLabel("Confirma a exclusao?");
        
        //Criar Botoes
        remover = new JButton("Remover usuario");
        remover.setBorderPainted(false);
        remover.setForeground(Color.white);
        remover.setBackground(new Color(0,0,128));
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        sim = new JRadioButton("Confirmo");
        
        //Criar listeners
        cadListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //Nesse caso devo pegar o codigo e remover do banco!
                if(sim.isSelected())
                    objCtrl.removerUsuarioSagha();
                else
                    mensagemErro("Confirme a remoçao!");
            }
        };
        
        sairListener =  new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                frame.dispose();
            }
        };
        
        sim.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sim.isSelected())
                    sim.setForeground(Color.GREEN.darker());                
               else
                    sim.setForeground(Color.black);
            }
        });
        
        
        //Adicionar listeners aos botoes
        remover.addActionListener(cadListener);
        sair.addActionListener(sairListener);
        
        //Criar Jpanel's
        pcod = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        pconfirm = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        psair = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        principal = new JPanel();
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        principal.setLayout(box);

        //Adicionar componentes aos paineis
        pcod.add(cpfJL);
        pcod.add(cpfTF);
        pconfirm.add(confirmacaoJL);
        pconfirm.add(sim);
        psair.add(remover);
        psair.add(sair);
        
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(pcod);
        principal.add(pconfirm);
        principal.add(psair);
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Remocao de usuarios");
        frame.getContentPane().add(principal);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Mensagem de erro ao usuario
     * @param msg Mensagem exibida ao usuario
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(principal, msg, "Informaçao do sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensagem de confirmaçao de remoçao de hospital
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal, "Usuario removido com sucesso!");
    }
    
    /**
     * Metodo que retorna o ID do hospital informado pelo usuario
     * @return CPF do usuario
     * @throws Exception caso o campo informado nao seja numerico
     */
    public String getCPF() throws Exception
    {
        if(cpfTF.getText().isEmpty())
            throw new Exception("Voce deve informar o cpf do usuario!");
        
        if(cpfTF.getText().length() != 11)
            throw new Exception("O CPF deve ter 11 digitos!");
        
        try {
            int aux = Integer.parseInt(cpfTF.getText());
        } catch (Exception exc) {
            throw new Exception("O CPF deve ser numerico!");
        }
        
        return cpfTF.getText();
    }
    
    /**
     * Metodo que deixa a interface no seu padrao DEFAULT (inicial)
     */
    public void limparEntradaTexto()
    {
        cpfTF.setText("");
        sim.setForeground(Color.black);
        sim.setSelected(false);
    }
}
