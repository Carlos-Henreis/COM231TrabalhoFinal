package Limites.Usuarios;

import Controladores.ControlePrincipal;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class LimiteCadastroUsuarios
{
    private JPanel grid,fluxo,principal,botoes;
    private JButton cadastrar,sair;
    private BoxLayout box;
    private JLabel nome,cpf,senha,tipo,confirmeSenha,idHospital;
    private JTextField nomeTF,cpfTF,idHospitalTF;
    private JPasswordField senhaPF,senha2PF;
    private JComboBox tipoCB;
    private JFrame frame;
    private ControlePrincipal objCtrl;
    
    private final String AGENTE = "AGENTE";
    private final String GERENTE = "GERENTE";

    public LimiteCadastroUsuarios(ControlePrincipal pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar JLabel's
        nome = new JLabel("Nome:");
        cpf = new JLabel("CPF:");
        senha = new JLabel("Senha");
        tipo = new JLabel("Tipo de usuario:");
        confirmeSenha = new JLabel("Confirme a senha:");
        idHospital = new JLabel("ID do hospital: ");
        
        //Criar ComboBox
        tipoCB = new JComboBox();
        tipoCB.addItem(AGENTE);
        tipoCB.addItem(GERENTE);
        tipoCB.setSelectedIndex(1);
        tipoCB.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(tipoCB.getSelectedIndex() == 0)
                {
                    idHospital.setForeground(Color.gray);
                    idHospitalTF.setEditable(false);
                }
                else
                {
                    idHospital.setForeground(cpf.getForeground());
                    idHospitalTF.setEditable(true);
                }
            }
        });
        
        //Criar caixas de texto
        nomeTF =  new JTextField(12);
        cpfTF =  new JTextField(12);
        idHospitalTF =  new JTextField(12);
        senhaPF =  new JPasswordField(12);
        senha2PF =  new JPasswordField(12);
        
        //Criar botoes
        cadastrar = new JButton("Cadastrar");
        cadastrar.setBackground(new Color(0,0,128));
        cadastrar.setForeground(Color.white);
        cadastrar.setBorderPainted(false);
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tipoCB.getSelectedIndex() == 0)
                    objCtrl.cadastrarAgenteOrgSaude();
                else
                    objCtrl.cadastrarGerenteDeHospital();
            }
        });
        sair = new JButton("Sair");
        sair.setBackground(new Color(0,0,128));
        sair.setForeground(Color.white);
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                frame.dispose();
            }
        });
        
        //Criar JPanels e seus gerenciadores de layout
        botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT,10, 10));
        grid = new JPanel(new GridLayout(7, 2,10,10));
        fluxo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        principal = new JPanel();
        box = new BoxLayout(principal, BoxLayout.Y_AXIS);
        principal.setLayout(box);
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        //Adicionar componentes aos paineis
        botoes.add(sair);
        botoes.add(cadastrar);
        
        grid.add(nome);
        grid.add(nomeTF);
        grid.add(cpf);
        grid.add(cpfTF);
        grid.add(tipo);
        grid.add(tipoCB);
        grid.add(senha);
        grid.add(senhaPF);
        grid.add(confirmeSenha);
        grid.add(senha2PF);
        grid.add(idHospital);
        grid.add(idHospitalTF);
        grid.add(Box.createGlue());
        grid.add(botoes);
        
        fluxo.add(grid);
        
        principal.add(Box.createGlue());
        principal.add(fluxo);
        principal.add(Box.createGlue());
        
        //Criar JFrame e definir suas opcoes
        frame = new JFrame();
        frame.add(principal);
        frame.setUndecorated(true);
        frame.setSize(800,600);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
     * Exibir mensagem de erro aos usuarios
     * @param msg mensagem a ser exibida
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(principal, msg,"Informaçao do sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibir mensagem de sucesso no cadastro ao usuario
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal,"Novo usuario cadastrado!","Informaçao do sistema",JOptionPane.INFORMATION_MESSAGE);
        resetarInterface();
    }
    
    /**
     * Limpar entradas de texto e voltar interface para Default
     */
    public void resetarInterface()
    {
        nomeTF.setText("");
        cpfTF.setText("");
        senhaPF.setText("");
        senha2PF.setText("");
        tipoCB.setSelectedIndex(0);
        idHospitalTF.setText("");
    }
    
    /**
     * 
     * @return 1: nome, 2: cpf, 3: Tipo de usuario, 4: senha, 5: Id do hospital (caso usuario seja gerente)
     * @throws Exception por dados inconsistentes
     */
    public String[] obterDadosUsuario() throws Exception
    {
        String form[];
        
        if(nomeTF.getText().isEmpty() || cpfTF.getText().isEmpty() || senhaPF.getText().isEmpty() || senha2PF.getText().isEmpty())
            throw new Exception("Informe todos os campos!");
        
        if(!senhaPF.getText().equals(senha2PF.getText()))
            throw new Exception("As senhas nao conferem!");
        
        if(tipoCB.getSelectedIndex() == 0)
        {
            form = new String[4];
            form[0] = nomeTF.getText();
            
            if(cpfTF.getText().length() != 11)
                throw new Exception("O cpf deve ter 11 digitos!");
            form[1] = cpfTF.getText();
            form[2] = senhaPF.getText();
            form[3] = (String) tipoCB.getItemAt(tipoCB.getSelectedIndex());
        }
        else
        {
            form = new String[5];
            
            form[0] = nomeTF.getText();
            
            if(cpfTF.getText().length() != 11)
                throw new Exception("O cpf deve ter 11 digitos!");
            form[1] = cpfTF.getText();
            form[2] = senhaPF.getText();
            form[3] = (String) tipoCB.getItemAt(tipoCB.getSelectedIndex());
            if(idHospitalTF.getText().isEmpty())
                throw new Exception("Voce deve informar o ID do hospital desse gerente!");
            form[4] = idHospitalTF.getText();
            
            try{
                int teste = Integer.parseInt(form[4]);
            }catch(Exception exc){
                throw new Exception("O ID do hospital deve ser numerico!");
            }
        }
        
        return form;
    }
}
