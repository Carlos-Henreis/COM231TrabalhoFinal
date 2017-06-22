package Limites.LimitePrincipal;

import Controladores.ControlePrincipal;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LimitePrincipal {

    private JFrame frame;
    private JPanel painel, central, inicial, pfinal;
    private JLabel equipe, boasvindas;
    private JLabel logotipo;
    private JMenuBar barramenu;
    private JButton btnExit;
    private JMenu hospital, drg, atendimentodrg,usuarios,relatorios;
    private ControlePrincipal objCtrl;
    private WindowListener winList;

    private final ImageIcon logoIMG = new ImageIcon("img/sagha13.png");
    private final ImageIcon create = new ImageIcon("img/create.png");
    private final ImageIcon delete = new ImageIcon("img/delete.png");
    private final ImageIcon read = new ImageIcon("img/read.png");
    private final ImageIcon update = new ImageIcon("img/update.png");
    private final ImageIcon regiao = new ImageIcon("img/local.png");    
    private final ImageIcon userADD = new ImageIcon("img/usuario.png");
    private final ImageIcon userDEL = new ImageIcon("img/usuarioDEL.png");
    private final ImageIcon userSHOW = new ImageIcon("img/usuarioVIEW.png");
    private final ImageIcon relatorio = new ImageIcon("img/relatorio2.png");
    
    private final String AGENTE = "AGENTE";
    
    public LimitePrincipal(ControlePrincipal pCtrl,String tipo) {
        objCtrl = pCtrl;

        //Criar JLabel's
        equipe = new JLabel("Developed by: Carlos Henrique Reis, Jean Carlos de Oliveira, Mateus Henrique Toledo, Victor Rodrigues @ All rights reserved. UNIFEI, 2017");
        logotipo = new JLabel(logoIMG);
        boasvindas = new JLabel();

        //Criar JMenuItem's e adicionar action listeners
        //->  MENUS DE USUARIOS
        JMenuItem cadastrarUsuario = new JMenuItem("Cadastrar Usuario",userADD);
        cadastrarUsuario.setBackground(Color.white);
        cadastrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.interfaceCadastroUsuario();
            }
        });
        JMenuItem removerUsuario = new JMenuItem("Remover Usuario",userDEL);
        removerUsuario.setBackground(Color.white);
        removerUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.interfaceRemocaoUsuario();
            }
        });
        JMenuItem visualizarUsuarios = new JMenuItem("Exibir usuarios",userSHOW);
        visualizarUsuarios.setBackground(Color.white);
        visualizarUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.interfaceVisualizacaoUsuarios();
            }
        });
        //->  MENUS DE HOSPITAL
        JMenuItem cadastrarhospital = new JMenuItem("Cadastrar Hospital", create);
        cadastrarhospital.setBackground(Color.white);
        cadastrarhospital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceCadastroHospital();
            }
        });
        JMenuItem removerhospital = new JMenuItem("Remover Hospital", delete);
        removerhospital.setBackground(Color.white);
        removerhospital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceRemocaoHospital();
            }
        });
        JMenuItem atualizarhospital = new JMenuItem("Atualizar dados de Hospital", update);
        atualizarhospital.setBackground(Color.white);
        atualizarhospital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().interfaceAtualizacaoHospital();
            }
        });
        JMenuItem visualizacaohospital = new JMenuItem("Listagem de hospitais", read);
        visualizacaohospital.setBackground(Color.white);
        visualizacaohospital.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitais().exibirHospitaisCadastrados();
            }
        });
        JMenuItem cadastrarRegiaoReferencia = new JMenuItem("Cadastrar regiao de referencia", regiao);
        cadastrarRegiaoReferencia.setBackground(Color.white);
        cadastrarRegiaoReferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorHospitaisRegiao().interfaceCadastroHospitalRegiao();
            }
        });
        //-> MENUS DE DRG
        JMenuItem cadastrarDrg = new JMenuItem("Cadastrar DRG", create);
        cadastrarDrg.setBackground(Color.white);
        cadastrarDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().interfaceCadastroDRG();
            }
        });
        JMenuItem removerDrg = new JMenuItem("Remover DRG", delete);
        removerDrg.setBackground(Color.white);
        removerDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().interfaceRemocaoDRG();
            }
        });
        JMenuItem visualizarDrg = new JMenuItem("Listagem de DRGs", read);
        visualizarDrg.setBackground(Color.white);
        visualizarDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().listarDrgs();
            }
        });
        JMenuItem atualizarDrg = new JMenuItem("Atualizar dados de DRG", update);
        atualizarDrg.setBackground(Color.white);
        atualizarDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorDRG().interfaceAtualizacaoDRG();
            }
        });
        //->MENUS DE ATENDIMENTO A DRG
        JMenuItem cadastrarAttDrg = new JMenuItem("Cadastrar atendimento a DRG", create);
        cadastrarAttDrg.setBackground(Color.white);
        cadastrarAttDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().interfaceCadastroAtendimentoDRG();
            }
        });
        JMenuItem visualizarAttDrg = new JMenuItem("Listagem de atendimento a DRGs", read);
        visualizarAttDrg.setBackground(Color.white);
        visualizarAttDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().exibirAtendimentosDRG();
            }
        });
        JMenuItem atualizarAttDrg = new JMenuItem("Atualizar dados de atendimento a DRG", update);
        atualizarAttDrg.setBackground(Color.white);
        atualizarAttDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorAtendimentoDRG().interfaceAtualizacaoAtendimentoDRG();
            }
        });
        //-> MENUS DE RELATORIOS
        JMenuItem relPagMedioAttDrgEstado = new JMenuItem("Pagamento medio do atendimento de DRG por estado", relatorio);
        relPagMedioAttDrgEstado .setBackground(Color.white);
        relPagMedioAttDrgEstado .addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorRelatorios().interfaceRelatorioPagMedioDrgEstado();
            }
        });
        JMenuItem relPagMedioAttDrg = new JMenuItem("Pagamento medio do atendimento de DRG (geral)", relatorio);
        relPagMedioAttDrg .setBackground(Color.white);
        relPagMedioAttDrg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorRelatorios().interfaceRelatorioPagamentoMedioDRG();
            }
        });
        JMenuItem relContNumDrgEstado = new JMenuItem("Contagem de numero de DRG's atendidas por estado", relatorio);
        relContNumDrgEstado.setBackground(Color.white);
        relContNumDrgEstado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorRelatorios().interfaceRelatorioContagemDrgAtendidaPorEstado();
            }
        });
        JMenuItem relGeralDRG = new JMenuItem("Relatorio geral de DRG - Altas totais e hospitais capacitados", relatorio);
        relGeralDRG.setBackground(Color.white);
        relGeralDRG.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorRelatorios().interfaceRelatorioGeralDRG();
            }
        });
        JMenuItem relDrgRegRef= new JMenuItem("Numero de DRG's atendidas por regiao de referencia", relatorio);
        relDrgRegRef.setBackground(Color.white);
        relDrgRegRef.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                objCtrl.getControladorRelatorios().interfaceRelatorioDrgRegiaoReferecia();
            }
        });

        //Criar JMenu's
        hospital = new JMenu("Hospitais");
        hospital.setForeground(Color.white);
        hospital.setBackground(new Color(124, 1, 45));
        usuarios = new JMenu("Usuarios");
        usuarios.setForeground(Color.white);
        usuarios.setBackground(new Color(124, 1, 45));
        drg = new JMenu("DRG");
        drg.setForeground(Color.white);
        drg.setBackground(new Color(124, 1, 45));
        atendimentodrg = new JMenu("Atendimento de DRG");
        atendimentodrg.setForeground(Color.white);
        atendimentodrg.setBackground(new Color(124, 1, 45));
        relatorios = new JMenu("Relatorios");
        relatorios.setForeground(Color.white);
        relatorios.setBackground(new Color(124, 1, 45));

        //Adicionar itens a seus respectivos menus
        //-> Menu usuarios
        usuarios.add(cadastrarUsuario);
        usuarios.addSeparator();
        usuarios.add(visualizarUsuarios);
        usuarios.addSeparator();
        usuarios.add(removerUsuario);
        //-> Menu Hospital
        hospital.add(cadastrarhospital);
        hospital.addSeparator();
        hospital.add(removerhospital);
        hospital.addSeparator();
        hospital.add(atualizarhospital);
        hospital.addSeparator();
        hospital.add(visualizacaohospital);
        hospital.addSeparator();
        hospital.add(cadastrarRegiaoReferencia);
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
        //->Menu de Relatorios
        relatorios.add(relContNumDrgEstado);
        relatorios.addSeparator();
        relatorios.add(relDrgRegRef);
        relatorios.addSeparator();
        relatorios.add(relGeralDRG);
        relatorios.addSeparator();
        relatorios.add(relPagMedioAttDrg);
        relatorios.addSeparator();
        relatorios.add(relPagMedioAttDrgEstado);

        //Criar JMenuBar e adicionar submenus
        barramenu = new JMenuBar();
        //Layout barramenu
        BoxLayout box = new BoxLayout(barramenu, BoxLayout.X_AXIS);
        barramenu.setLayout(box);

        //Configurações barramenu
        barramenu.setBackground(new Color(124, 1, 45));

        //Exit
        btnExit = new JButton(new ImageIcon("img/exit.png"));
        btnExit.setBorderPainted(false);
        btnExit.setBackground(new Color(124, 1, 45));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
                objCtrl.interfaceDeLogin();
            }
        });        
        
        //Gerar interface se o usuario for o AGENTE
        if(tipo.equals(AGENTE))
        {
            barramenu.add(usuarios);
            barramenu.add(hospital);
        }
        
        //Adicionar itens ao menu
        barramenu.add(drg);
        barramenu.add(atendimentodrg);
        barramenu.add(relatorios);
        barramenu.add(Box.createGlue());
        barramenu.add(btnExit);

        //Criar JPanel's
        pfinal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pfinal.add(equipe);
        pfinal.setBackground(Color.white);
        painel = new JPanel(new BorderLayout());
        painel.setBackground(Color.white);
        central = new JPanel(new FlowLayout(FlowLayout.CENTER));
        central.setBackground(Color.white);
        inicial = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 30));
        inicial.setBackground(Color.WHITE);

        //Adicionar componentes aos paineis
        inicial.add(boasvindas);
        central.add(logotipo);
        //painel.add(inicial,BorderLayout.PAGE_START);
        painel.add(pfinal, BorderLayout.PAGE_END);
        painel.add(central, BorderLayout.CENTER);

        //Gerar JFrame e setar configurações
        frame = new JFrame("Sistema de apoio à gestão de hospitais americanos - SAGHA");
        frame.addWindowListener(winList);
        frame.setSize(800, 600);
        frame.add(painel);
        frame.setJMenuBar(barramenu);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);
    }
}
