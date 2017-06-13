package Limites.AtendimentoDRG;

import Controladores.ControleAtendimentoDRG;
import Model.AtendimentoDrg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteAtualizacaoAtendimentoDRG
{
    private JFrame frame;
    private JButton cadastrar,sair,buscar,sair2;
    private JTextField codigoTF,idHospitalTF,numeroAltasTF,taxasMediasTF,pagmediosTF,mediapagTF;
    private JLabel codigoJL,idHospitalJL,numeroAltasJL,taxasMediasJL,pagmediosJL,mediapagJL;
    private JPanel pcod,pcad,pid,pnumaltas,ptxmedias,ppagmedios,pmediapg,principal,pdados,pidentificadores,psubdados,psubid;
    private JPanel pbusca,patt;
    private BoxLayout box;
    private CardLayout card;
    private AtendimentoDrg atend;
    private ActionListener cadListener,sairListener,buscaListener;
    private ControleAtendimentoDRG objCtrl;

    public LimiteAtualizacaoAtendimentoDRG(ControleAtendimentoDRG pCtrl)
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
        cadastrar = new JButton("ATUALIZAR");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("SAIR");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        sair2 = new JButton("SAIR");
        sair2.setBorderPainted(false);
        sair2.setForeground(Color.white);
        sair2.setBackground(new Color(0,0,128));
        buscar = new JButton("BUSCAR ATENDIMENTO");
        buscar.setBorderPainted(false);
        buscar.setForeground(Color.white);
        buscar.setBackground(new Color(0,0,128));
        
        //Criar listeners
        cadListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                objCtrl.atualizacaoParte2_CADASTRO();
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
        
        buscaListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.atualizacaoParte1_BUSCA();
            }
        };
        
        //Adicionar listeners aos botoes
        cadastrar.addActionListener(cadListener);
        sair.addActionListener(sairListener);
        buscar.addActionListener(buscaListener);
        sair2.addActionListener(sairListener);
        
        //Criar Jpanel's
        pcod = new JPanel(new FlowLayout(FlowLayout.CENTER,20,5));
        pid = new JPanel(new FlowLayout(FlowLayout.CENTER,29,5));
        pnumaltas = new JPanel(new FlowLayout(FlowLayout.CENTER,78,10));
        ptxmedias = new JPanel(new FlowLayout(FlowLayout.CENTER,42,10));
        ppagmedios = new JPanel(new FlowLayout(FlowLayout.CENTER,28,10));
        pmediapg = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        patt = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        pcad = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,0));
        pbusca = new JPanel(new FlowLayout(FlowLayout.CENTER,20,10));
        pidentificadores = new JPanel(new GridLayout(2, 1));
        pdados = new JPanel(new GridLayout(4, 1));
        pidentificadores.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Campos de busca"));
        pdados.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(0,0,128)),"Dados do atendimento"));
        psubdados = new JPanel(new FlowLayout(FlowLayout.CENTER));
        psubid = new JPanel(new GridLayout(4, 1));
        principal = new JPanel();
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        box = new BoxLayout(principal,BoxLayout.Y_AXIS);
        card = new CardLayout();
        principal.setLayout(card);

        //Adicionar componentes aos paineis
        pcod.add(codigoJL);
        pcod.add(codigoTF);
        pid.add(idHospitalJL);
        pid.add(idHospitalTF);
        pbusca.add(buscar);
        pbusca.add(sair2);
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
        patt.add(Box.createVerticalGlue());
        patt.add(pdados);
        patt.add(pcad);
        patt.add(Box.createVerticalGlue());
        //Ajustar paineis ao mesmo tamanho
        psubid.add(Box.createVerticalGlue());
        psubid.add(pidentificadores);
        psubid.add(pbusca);
        psubid.add(Box.createVerticalGlue());
        //Adicionar centralizados
        psubdados.add(psubid);
        //Adicionar paineis ao cardLayout
        principal.add(psubdados,"BUSCA");
        principal.add(patt,"ATUALIZACAO");
        card.addLayoutComponent(psubdados, "BUSCA");
        card.addLayoutComponent(patt, "ATUALIZACAO");
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Cadastro de atendimento de DRG");
        frame.getContentPane().add(principal);
        frame.setSize(800,250);
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
     * Mensagem de confirmaçao de atualizacao ao usuario
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal, "Atendimento de DRG atualizado com sucesso!");
        card.show(principal,"BUSCA");
    }
    
    /**
     * Limpar os dados presentes nos campos de texto (usado apos atualizaco com sucesso)
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
     * Obter dados para busca de atendimento de DRG
     * @return formulario com a ID do atendimento
     * @throws Exception caso os dados nao estejam seguindo os tipos corretos
     */
    public String[] getIdAtendimento() throws Exception
    {
        String id[] = new String[2];
        
        id[0] = ""+Short.parseShort(codigoTF.getText());
        id[1] = ""+Integer.parseInt(idHospitalTF.getText());
        
        return id;
    }
    
    /**
     * Recebe o atendimento a DRG buscado e seta seus dados na interface
     * @param att Atendimento a DRG que o usuario esta buscando
     */
    public void AtualizarInterface(AtendimentoDrg att)
    {
        atend = att;
        
        //Inserir valores atuais nos campos de texto
        numeroAltasTF.setText(""+att.getNumeroaltas());
        taxasMediasTF.setText(""+att.getTaxasmediascobertas());
        mediapagTF.setText(""+att.getMediapagamentosmedicare());
        pagmediosTF.setText(""+att.getPagamentosmediostotais());
        
        //Atualizar a interface
        frame.setSize(800, 250);
        frame.setLocationRelativeTo(null);
        card.show(principal, "ATUALIZACAO");
    }
    
    /**
     * Metodo que retorna o Atendimento a DRg com os novos dados vindos da interface
     * @return novo atendimento a DRG
     * @throws Exception Caso dado faltante ou dado em formato diferente do esperado
     */
    public AtendimentoDrg getAtendimentoAtualizado() throws Exception
    {
        if(numeroAltasTF.getText().isEmpty() || taxasMediasTF.getText().isEmpty() || mediapagTF.getText().isEmpty() || pagmediosTF.getText().isEmpty())
            throw new Exception("Preencha todos os dados do atendimento!");
        
        //Ao fazer o typecast de String para os tipos numericos
        //Pode ser disparada alguma outra excessao
        try{
            atend.setNumeroaltas(Short.parseShort(numeroAltasTF.getText()));
            atend.setTaxasmediascobertas(Float.parseFloat(taxasMediasTF.getText()));
            atend.setMediapagamentosmedicare(Float.parseFloat(mediapagTF.getText()));
            atend.setPagamentosmediostotais(Float.parseFloat(pagmediosTF.getText()));
        }catch(Exception exc){
            throw new Exception("Informe os dados no formato correto!");
        }
        
        return atend;
    }
}
