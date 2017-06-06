package Limites.DRG;

import Controladores.ControleDrg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteRemocaoDRG
{
    private JFrame frame;
    private JButton cadastrar,sair;
    private JTextField codigoTF;
    private JRadioButton sim;
    private JLabel codigoJL,confirmacaoJL;
    private JPanel pcod,pconfirm,psair,principal;
    private BoxLayout box;
    private ActionListener cadListener,sairListener;
    private ControleDrg objCtrl;
    
    public LimiteRemocaoDRG(ControleDrg pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar text fields
        codigoTF = new JTextField(10);
        
        //Criar JLabel's
        codigoJL     = new JLabel("Codigo da DRG :");
        confirmacaoJL  = new JLabel("Confirma a exclusao?");
        
        //Criar Botoes
        cadastrar = new JButton("Remover DRG");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
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
                    objCtrl.removerDrg();
                else
                    mensagemErro("Voce deve confirmar a remoçao!");
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
        cadastrar.addActionListener(cadListener);
        sair.addActionListener(sairListener);
        
        //Criar Jpanel's
        pcod = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        pconfirm = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        psair = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        principal = new JPanel();
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        principal.setLayout(box);

        //Adicionar componentes aos paineis
        pcod.add(codigoJL);
        pcod.add(codigoTF);
        pconfirm.add(confirmacaoJL);
        pconfirm.add(sim);
        //psair.add(Box.createHorizontalGlue());
        //psair.add(Box.createHorizontalGlue());
        //psair.add(Box.createHorizontalGlue());
        psair.add(cadastrar);
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
        frame = new JFrame("Cadastro do grupo relacionado de diagnostico");
        frame.getContentPane().add(principal);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Metodo que exibe uma mensagem de erro ao usuario
     * @param msg Mensagem que sera exibida na interface
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(principal, msg, "Informaçao do sistema", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Mensagem de confirmaçao de cadastro ao usuario
     */
    public void mensagemSucesso()
    {
        //Limpar formulario
        codigoTF.setText("");
        
        //Mensagem ao usuario
        JOptionPane.showMessageDialog(principal, "DRG removida com sucesso!");
    }
    
    /**
     * Metodo que retorna o codigo da DRG
     * @return codigo da DRG a ser removida
     * @throws Exception caso o valor informado nao seja inteiro
     */
    public int getCodigo() throws Exception
    {
        return Integer.parseInt(codigoTF.getText());
    }
}
