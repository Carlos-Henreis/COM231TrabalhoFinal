package Limites.LimitePrincipal;

import Controladores.ControlePrincipal;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class LimitePrincipal
{
    private JFrame frame;
    private JPanel painel,central,inicial,pfinal;
    private JLabel equipe,boasvindas;
    private JLabel logotipo;
    private JMenuBar barramenu;
    private JMenu hospital,drg,atendimentodrg;
    private ControlePrincipal objCtrl;
    private WindowListener winList;
    
    private final ImageIcon logoIMG = new ImageIcon("img/sagha13.png");
    private final ImageIcon create = new ImageIcon("img/create.png");
    private final ImageIcon delete = new ImageIcon("img/delete.png");
    private final ImageIcon read = new ImageIcon("img/read.png");
    private final ImageIcon update = new ImageIcon("img/update.png");
    

    public LimitePrincipal(ControlePrincipal pCtrl)
    {
        objCtrl = pCtrl;
        
        //Criar JLabel's
        equipe = new JLabel("Developed by: Carlos Henrique Reis, Jean Carlos de Oliveira, Mateus Henrique Toledo, Victor Rodrigues @ All rights reserved. UNIFEI, 2017");
        logotipo = new JLabel(logoIMG);
        boasvindas = new JLabel();
        
        //Criar JMenuItem's e adicionar action listeners
        //->  MENUS DE HOSPITAL
        JMenuItem cadastrarhospital = new JMenuItem("Cadastrar Hospital",create);
        cadastrarhospital.setBackground(Color.white);
        cadastrarhospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceCadastroHospital();
            }
        });
        JMenuItem removerhospital = new JMenuItem("Remover Hospital",delete);
        removerhospital.setBackground(Color.white);
        removerhospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceRemocaoHospital();
            }
        });
        JMenuItem atualizarhospital = new JMenuItem("Atualizar dados de Hospital",update);
        atualizarhospital.setBackground(Color.white);
        atualizarhospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceAtualizacaoHospital();
            }
        });
        JMenuItem visualizacaohospital = new JMenuItem("Listagem de hospitais",read);
        visualizacaohospital.setBackground(Color.white);
        visualizacaohospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().exibirHospitaisCadastrados();
            }
        });
        //-> MENUS DE DRG
        JMenuItem cadastrarDrg = new JMenuItem("Cadastrar DRG",create);
        cadastrarDrg.setBackground(Color.white);
        cadastrarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().interfaceCadastroDRG();
            }
        });
        JMenuItem removerDrg = new JMenuItem("Remover DRG",delete);
        removerDrg.setBackground(Color.white);
        removerDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().interfaceRemocaoDRG();
            }
        });
        JMenuItem visualizarDrg = new JMenuItem("Listagem de DRGs",read);
        visualizarDrg.setBackground(Color.white);
        visualizarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().listarDrgs();
            }
        });
        JMenuItem atualizarDrg = new JMenuItem("Atualizar dados de DRG",update);
        atualizarDrg.setBackground(Color.white);
        atualizarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               objCtrl.getControladorDRG().interfaceAtualizacaoDRG();
            }
        });
        //->MENUS DE ATENDIMENTO A DRG
        JMenuItem cadastrarAttDrg = new JMenuItem("Cadastrar atendimento a DRG",create);
        cadastrarAttDrg.setBackground(Color.white);
        cadastrarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().interfaceCadastroAtendimentoDRG();
            }
        });
        JMenuItem visualizarAttDrg = new JMenuItem("Listagem de atendimento a DRGs",read);
        visualizarAttDrg.setBackground(Color.white);
        visualizarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().exibirAtendimentosDRG();
            }
        });
        JMenuItem atualizarAttDrg = new JMenuItem("Atualizar dados de atendimento a DRG",update);
        atualizarAttDrg.setBackground(Color.white);
        atualizarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().interfaceAtualizacaoAtendimentoDRG();
            }
        });
        
        //Criar JMenu's
        hospital = new JMenu("Hospitais");
        drg = new JMenu("DRG");
        atendimentodrg = new JMenu("Atendimento de DRG");
        
        //Adicionar itens a seus respectivos menus
        //-> Menu Hospital
        hospital.add(cadastrarhospital);
        hospital.addSeparator();
        hospital.add(removerhospital);
        hospital.addSeparator();
        hospital.add(atualizarhospital);
        hospital.addSeparator();
        hospital.add(visualizacaohospital);
        //-> Menu DRG
        drg.add(cadastrarDrg);
        drg.addSeparator();
        drg.add(removerDrg);
        drg.addSeparator();
        drg.add(atualizarDrg);
        drg.addSeparator();
        drg.add(visualizarDrg);
        //->Menu Atendimento a DRG
        atendimentodrg.add(cadastrarAttDrg);
        atendimentodrg.addSeparator();
        atendimentodrg.add(atualizarAttDrg);
        atendimentodrg.addSeparator();
        atendimentodrg.add(visualizarAttDrg);
        
        //Criar JMenuBar e adicionar submenus
        barramenu = new JMenuBar();
        barramenu.add(hospital);
        barramenu.add(drg);
        barramenu.add(atendimentodrg);
        
        //Criar JPanel's
        pfinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pfinal.add(equipe);
        pfinal.setBackground(Color.white);
        painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.white);
        central = new JPanel(new FlowLayout(FlowLayout.CENTER));
        central.setBackground(Color.white);
        inicial = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,30));
        inicial.setBackground(Color.WHITE);
        
        
        //Adicionar componentes aos paineis
        inicial.add(boasvindas);
        central.add(logotipo);
        //painel.add(inicial,BorderLayout.PAGE_START);
        painel.add(pfinal,BorderLayout.PAGE_END);
        painel.add(central,BorderLayout.CENTER);
        
        //Gerar JFrame e setar configurações
        this.gerarListenerJFrame();
        frame = new  JFrame("Sistema de apoio à gestão de hospitais americanos - SAGHA");
        frame.addWindowListener(winList);
        frame.setSize(800, 600);
        frame.add(painel);
        frame.setJMenuBar(barramenu);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    public void gerarListenerJFrame()
    {
        winList = new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e)
            {/*JANELA ABERTA*/}

            @Override
            public void windowClosing(WindowEvent e)
            {/*QUANDO JANELAS ESTAO FECHANDO -- ESSE METODO*/}

            @Override
            public void windowClosed(WindowEvent e)
            {/*APOS MATAR A JANELA*/}

            @Override
            public void windowIconified(WindowEvent e)
            {/*JANELA MINIMIZADA*/}

            @Override
            public void windowDeiconified(WindowEvent e)
            {/*JANELA MAXIMIZADA*/}

            @Override
            public void windowActivated(WindowEvent e)
            {/*JANELA ABERTA*/}

            @Override
            public void windowDeactivated(WindowEvent e)
            {/*JANELA SOBREPOSTA OU ESCONDIDA*/}
        };
    }
}
