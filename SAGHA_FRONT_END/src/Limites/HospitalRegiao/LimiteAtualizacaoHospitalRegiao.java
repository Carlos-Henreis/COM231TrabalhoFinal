package Limites.HospitalRegiao;

import Controladores.ControleHospitaisRegiao;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LimiteAtualizacaoHospitalRegiao
{
    private JLabel codHospitalJL,regiaoReferenciaJL;
    private JButton cadastrar,sair;
    private JTextField codHospitalTF,regiaoReferenciaTF;
    private JPanel codHospitalJP,regiaoReferenciaJP,botoesJP,principal;
    private BoxLayout box;
    private JFrame frame;
    private ControleHospitaisRegiao objCtrl;
    
    public LimiteAtualizacaoHospitalRegiao(ControleHospitaisRegiao pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar JButtons e adicionar listener a eles
        cadastrar = new JButton("Atualizar");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        cadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para cadastrar Regiao de referencia
            }
        });
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        
        //criar JLabel's
        codHospitalJL       = new JLabel("Codigo  do  hospital:");
        regiaoReferenciaJL  = new JLabel("Regiao de referencia:");
        
        //Criar JTextFields
        codHospitalTF = new JTextField(10);
        regiaoReferenciaTF = new JTextField(10);
        
        //Criar JPanels e gerenciadores de Layout
        codHospitalJP = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        regiaoReferenciaJP = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        botoesJP = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        principal = new JPanel();
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        principal.setLayout(box);
        
        //Adicionar componentes aos paineis
        codHospitalJP.add(codHospitalJL);
        codHospitalJP.add(codHospitalTF);
        regiaoReferenciaJP.add(regiaoReferenciaJL);
        regiaoReferenciaJP.add(regiaoReferenciaTF);
        botoesJP.add(cadastrar);
        botoesJP.add(sair);
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(codHospitalJP);
        principal.add(regiaoReferenciaJP);
        principal.add(botoesJP);
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        
        //Criar JFrame e setar opcoes
        frame = new JFrame();
        frame.add(principal);
        frame.setUndecorated(true);
        frame.setSize(500, 300);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    /**
     * Exibir mensagem de confirmacao ao usuario
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal, "Regiao de hospital atualizada com sucesso!");
    }
    
    /**
     * Exibir mensagem de erro ao usuario
     * @param msg Mensagem que sera exibida na interface
     */
    public void mensagemErro(String msg)
    {
        JOptionPane.showMessageDialog(principal, msg,"Informaçao do sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Limpa os formularios de entrada de dados
     */
    public void limparFormulario()
    {
        codHospitalTF.setText("");
        regiaoReferenciaTF.setText("");
    }
    
    /**
     * Retorna os dados da interface de usuario
     * @return dados da interface
     * @throws Exception Caso dados inconsistentes ou vazios
     */
    public String[] getDados() throws Exception
    {
        String form[] = new String[2];
        
        if(codHospitalTF.getText().isEmpty() || regiaoReferenciaTF.getText().isEmpty())
            throw new Exception("Informe todos os campos!");
        
        try {
            form[0] = ""+Integer.parseInt(codHospitalTF.getText());
            form[1] = regiaoReferenciaTF.getText();
        } catch (Exception exc) {
            throw new Exception("O campo codigo do hospital deve ser numerico!");
        }
        
        return form;
    }
}
