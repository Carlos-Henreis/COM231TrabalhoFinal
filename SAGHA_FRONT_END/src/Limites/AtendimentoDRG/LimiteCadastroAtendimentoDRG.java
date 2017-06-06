package Limites.AtendimentoDRG;

import Controladores.ControleAtendimentoDRG;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author DESENVOLVEDORES SAGHA
 */
public class LimiteCadastroAtendimentoDRG
{
    private JFrame frame;
    private JButton cadastrar,sair;
    private JTextField codigoTF,idHospitalTF,numeroAltasTF,taxasMediasTF,pagmediosTF,mediapagTF;
    private JLabel codigoJL,idHospitalJL,numeroAltasJL,taxasMediasJL,pagmediosJL,mediapagJL;
    private JPanel pcod,pcad,pid,pnumaltas,ptxmedias,ppagmedios,pmediapg,principal,pdados,pidentificadores,psubdados,psubid;
    private BoxLayout box;
    private ActionListener cadListener,sairListener;
    private ControleAtendimentoDRG objCtrl;

    public LimiteCadastroAtendimentoDRG(ControleAtendimentoDRG pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar text fields
        codigoTF = new JTextField(20);
        idHospitalTF = new JTextField(20);
        numeroAltasTF = new JTextField(20);
        taxasMediasTF = new JTextField(20);
        pagmediosTF = new JTextField(20);
        mediapagTF = new JTextField(20);
        
        //Criar JLabel's
        codigoJL        = new JLabel("Codigo da DRG:");
        idHospitalJL    = new JLabel("ID do hospital:");
        numeroAltasJL   = new JLabel("Numero de altas:");
        taxasMediasJL   = new JLabel("Taxas medias cobertas:");
        pagmediosJL     = new JLabel("Pagamentos medios totais:");
        mediapagJL      = new JLabel("Media pagamentos Medicare:");
        
        //Criar Botoes
        cadastrar = new JButton("CADASTRAR");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("SAIR");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        
        //Criar listeners
        cadListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                objCtrl.cadastrarAtendimentoDRG();
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
        
        //Adicionar listeners aos botoes
        cadastrar.addActionListener(cadListener);
        sair.addActionListener(sairListener);
        
        //Criar Jpanel's
        pcod = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        pid = new JPanel(new FlowLayout(FlowLayout.CENTER,29,5));
        pnumaltas = new JPanel(new FlowLayout(FlowLayout.CENTER,78,10));
        ptxmedias = new JPanel(new FlowLayout(FlowLayout.CENTER,42,10));
        ppagmedios = new JPanel(new FlowLayout(FlowLayout.CENTER,28,10));
        pmediapg = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        pcad = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,25));
        pidentificadores = new JPanel(new GridLayout(2, 1));
        pdados = new JPanel(new GridLayout(4, 1));
        pidentificadores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Identificadores"));
        pdados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Dados do atendimento"));
        psubdados = new JPanel(new FlowLayout(FlowLayout.CENTER));
        psubid = new JPanel(new GridLayout(3, 1));
        principal = new JPanel();
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        principal.setLayout(box);

        //Adicionar componentes aos paineis
        pcod.add(codigoJL);
        pcod.add(codigoTF);
        pid.add(idHospitalJL);
        pid.add(idHospitalTF);
        pnumaltas.add(numeroAltasJL);
        pnumaltas.add(numeroAltasTF);
        ptxmedias.add(taxasMediasJL);
        ptxmedias.add(taxasMediasTF);
        ppagmedios.add(pagmediosJL);
        ppagmedios.add(pagmediosTF);
        pmediapg.add(mediapagJL);
        pmediapg.add(mediapagTF);
        pcad.add(cadastrar);
        pcad.add(sair);
        //Painel do topo c/ borda com titulo
        pidentificadores.add(pcod);
        pidentificadores.add(pid);
        //Painel central com borda c/ titulo
        pdados.add(pnumaltas);
        pdados.add(ptxmedias);
        pdados.add(ppagmedios);
        pdados.add(pmediapg);
        //Ajustar paineis ao mesmo tamanho
        psubid.add(pidentificadores);
        psubid.add(pdados);
        psubid.add(pcad);
        //Adicionar centralizados
        psubdados.add(psubid);
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(Box.createVerticalGlue());
        principal.add(psubdados);
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Cadastro de atendimento de DRG");
        frame.getContentPane().add(principal);
        frame.setSize(800, 600);
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
        JOptionPane.showMessageDialog(principal, "Atendimento de DRG cadastrado com sucesso!");
    }
    
    /**
     * Limpar os dados presentes nos campos de texto (usado apos um cadastro com sucesso)
     */
    public void limparFormulario()
    {
        codigoTF.setText("");
        idHospitalTF.setText("");
        numeroAltasTF.setText("");
        taxasMediasTF.setText("");
        pagmediosTF.setText("");
        mediapagTF.setText("");
    }
    
    /**
     * Obter dados da interface de usuario
     * @return Formulario com os dados
     * @throws Exception Caso nao sejam informados valores numericos ou dado faltante
     */
    public String[] getDados() throws Exception
    {
        String form[] = new String[6];
        
        //Testar se todos os campos foram preenchidos
        if(codigoTF.getText().isEmpty() || idHospitalTF.getText().isEmpty() || numeroAltasTF.getText().isEmpty() || taxasMediasTF.getText().isEmpty() || pagmediosTF.getText().isEmpty() || mediapagTF.getText().isEmpty())
            throw new Exception("Voce deve preencher todos os campos!");
        
        //testar se os dados estao de acordo com seus tipos
        Short sht = Short.parseShort(codigoTF.getText());
        sht = Short.parseShort(numeroAltasTF.getText());
        int in = Integer.parseInt(idHospitalTF.getText());
        Float fl = Float.parseFloat(taxasMediasTF.getText());
        fl = Float.parseFloat(pagmediosTF.getText());
        fl = Float.parseFloat(mediapagTF.getText());
        
        //Anexar dados ao formulario
        form[0] = codigoTF.getText();
        form[1] = idHospitalTF.getText();
        form[2] = numeroAltasTF.getText();
        form[3] = taxasMediasTF.getText();
        form[4] = pagmediosTF.getText();
        form[5] = mediapagTF.getText();
        
        return form;
    }
}
