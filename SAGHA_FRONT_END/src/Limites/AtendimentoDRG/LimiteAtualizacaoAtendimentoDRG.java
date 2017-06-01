package Limites.AtendimentoDRG;

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
    private ActionListener cadListener,sairListener,buscaListener;

    public LimiteAtualizacaoAtendimentoDRG()
    {
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
                //Nesse caso devo acionar o controlador para realizar att no BD
                card.show(principal,"BUSCA");
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
                //Ao acionar a busca eu devo Buscar os dados no BD
                //Se encontrar pulo para tela de att
                //Senao uso JOptionPane e mensagem   
                AtualizarInterface(55, 90,(float)89.9997, 1247);
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
        principal.setBorder(BorderFactory.createLineBorder(new Color(0,0,128)));
        
        //Criar JFRame e definir opcoes
        frame = new JFrame("Cadastro de atendimento de DRG");
        frame.getContentPane().add(principal);
        frame.setSize(800,250);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }
    
    public void AtualizarInterface(int numAltas,float taxasMedCobertas,float pgmediostotais,float mediapagMedicare)
    {
        //Inserir valores atuais nos campos de texto
        numeroAltasTF.setText(""+numAltas);
        taxasMediasTF.setText(""+taxasMedCobertas);
        mediapagTF.setText(""+mediapagMedicare);
        pagmediosTF.setText(""+pgmediostotais);
        
        //Atualizar a interface
        frame.setSize(800, 250);
        frame.setLocationRelativeTo(null);
        card.show(principal, "ATUALIZACAO");
    }
}
