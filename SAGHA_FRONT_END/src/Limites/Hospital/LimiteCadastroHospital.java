package Limites.Hospital;

import Controladores.ControleHospital;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteCadastroHospital
{
    private JFrame frame;
    private JButton cadastrar,sair;
    private JLabel idJL,nomeJL,ruaJL,cidadeJL,estadoJL;
    private JTextField idTF,nomeTF,ruaTF,cidadeTF,estadoTF;
    private ActionListener cadListener,exitListener;
    private JPanel formulario,pcad,principal,externo;
    private ControleHospital objCtrl;

    public LimiteCadastroHospital(ControleHospital pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar JLabel's
        idJL = new JLabel("ID do hospital:");
        nomeJL = new JLabel("Nome do hospital:");
        ruaJL = new JLabel("Rua:");
        cidadeJL = new JLabel("Cidade:");
        estadoJL = new JLabel("Estado:");
        
        //Criar TextField's
        idTF = new JTextField(20);
        nomeTF = new JTextField(20);
        ruaTF = new JTextField(20);
        cidadeTF = new JTextField(20);
        estadoTF = new JTextField(20);
        
        //Criar Jbuttons
        cadastrar = new JButton("CADASTRAR");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("SAIR");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        
        //Gerar Listener's e adicionar aos botoes
        cadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.cadastrarHospital();
            }
        };
        cadastrar.addActionListener(cadListener);
        
        exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        sair.addActionListener(exitListener);
        
        //Criar JPanel's
        formulario = new JPanel(new GridLayout(6, 2, 10, 20));
        pcad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        principal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        externo = new JPanel();
        BoxLayout box = new BoxLayout(externo,BoxLayout.Y_AXIS);
        externo.setLayout(box);
        externo.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes aos devidos paineis
        pcad.add(cadastrar);
        pcad.add(sair);
        
        formulario.add(idJL);
        formulario.add(idTF);
        formulario.add(nomeJL);
        formulario.add(nomeTF);
        formulario.add(ruaJL);
        formulario.add(ruaTF);
        formulario.add(cidadeJL);
        formulario.add(cidadeTF);
        formulario.add(estadoJL);
        formulario.add(estadoTF);
        formulario.add(Box.createHorizontalGlue());
        formulario.add(pcad);
        
        principal.add(formulario);
        
        externo.add(Box.createGlue());
        externo.add(principal);
        externo.add(Box.createGlue());
        
        frame = new JFrame("Cadastro de hospital");
        frame.add(externo);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Metodo que exibe uma mensagem de erro durante a atualizacao
     * @param msg Mensagem que sera exibida
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(externo,msg,"Erro na atualizaçao",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibir mensagem de sucesso de cadastro ao usuario
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal, "Hospital cadastrado!");
        JOptionPane.showMessageDialog(principal, "Para que esse hospital participe dos relatorios\n"
                + "cadastre a regiao de referencia no menu Hospital!\n\nObrigado(a)!");
    }
    
    /**
     * Metodo para obter os dados do novo hospital da interface de usuario
     * @return Dados do hospital
     * @throws Exception Caso o usuario nao informe todos os campos
     */
    public String[] getDados() throws Exception
    {
        String form[] = new String[5];
        
        if(idTF.getText().isEmpty() ||cidadeTF.getText().isEmpty() ||  estadoTF.getText().isEmpty() || nomeTF.getText().isEmpty() ||  ruaTF.getText().isEmpty())
            throw new Exception("Voce deve informar todos os campos");
        
        form[0] = idTF.getText();
        form[1] = nomeTF.getText();
        form[2] = ruaTF.getText();
        form[3] = cidadeTF.getText();
        form[4] = estadoTF.getText();
        
        return form;
    }
    
    /**
     * Metodo que limpa os campos de entrada de texto da interface
     */
    public void limparFormulario()
    {
        idTF.setText("");
        nomeTF.setText("");
        ruaTF.setText("");
        cidadeTF.setText("");
        estadoTF.setText("");
    }
}
