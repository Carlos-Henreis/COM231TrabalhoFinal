package Limites.LimitePrincipal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Limites.AtendimentoDRG.*;
import Limites.DRG.*;
import Limites.Hospital.*;

public class LimitePrincipal
{
    private JFrame frame;
    private JPanel painel,central,inicial,pfinal;
    private JLabel equipe,boasvindas;
    private JLabel logotipo;
    private JMenuBar barramenu;
    private JMenu hospital,drg,atendimentodrg;
    
    private final ImageIcon logoIMG = new ImageIcon("img/sagha13.png");
    
    private final ImageIcon create = new ImageIcon("img/create.png");
    private final ImageIcon delete = new ImageIcon("img/delete.png");
    private final ImageIcon read = new ImageIcon("img/read.png");
    private final ImageIcon update = new ImageIcon("img/update.png");
    

    public LimitePrincipal()
    {
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
                new LimiteCadastroHospital();
            }
        });
        JMenuItem removerhospital = new JMenuItem("Remover Hospital",delete);
        removerhospital.setBackground(Color.white);
        removerhospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteRemocaoHospital();
            }
        });
        JMenuItem atualizarhospital = new JMenuItem("Atualizar dados de Hospital",update);
        atualizarhospital.setBackground(Color.white);
        atualizarhospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteAtualizacaoHospital();
            }
        });
        JMenuItem visualizacaohospital = new JMenuItem("Listagem de hospitais",read);
        visualizacaohospital.setBackground(Color.white);
        visualizacaohospital.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String vet[][] = new String[1][5];
                vet[0][0] = "da";
                vet[0][1] = "da";
                vet[0][2] = "da";
                vet[0][3] = "da";
                vet[0][4] = "da";
                
                new LimiteVisualizacaoHospital(vet);
            }
        });
        //-> MENUS DE DRG
        JMenuItem cadastrarDrg = new JMenuItem("Cadastrar DRG",create);
        cadastrarDrg.setBackground(Color.white);
        cadastrarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteCadastroDRG();
            }
        });
        JMenuItem removerDrg = new JMenuItem("Remover DRG",delete);
        removerDrg.setBackground(Color.white);
        removerDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteRemocaoDRG();
            }
        });
        JMenuItem visualizarDrg = new JMenuItem("Listagem de DRGs",read);
        visualizarDrg.setBackground(Color.white);
        visualizarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String vet[][] = new String[1][2];
                vet[0][0] = vet[0][1] = "DAda";
                
                new LimiteVisualizacaoDRG(vet);
            }
        });
        JMenuItem atualizarDrg = new JMenuItem("Atualizar dados de DRG",update);
        atualizarDrg.setBackground(Color.white);
        atualizarDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteAtualizacaoDRG();
            }
        });
        //->MENUS DE ATENDIMENTO A DRG
        JMenuItem cadastrarAttDrg = new JMenuItem("Cadastrar atendimento a DRG",create);
        cadastrarAttDrg.setBackground(Color.white);
        cadastrarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteCadastroAtendimentoDRG();
            }
        });
        JMenuItem removerAttDrg = new JMenuItem("Remover atendimento de DRG",delete);
        removerAttDrg.setBackground(Color.white);
        removerAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(central,"A remoçao nao pode ser feita de forma direta!");
            }
        });
        JMenuItem visualizarAttDrg = new JMenuItem("Listagem de atendimento a DRGs",read);
        visualizarAttDrg.setBackground(Color.white);
        visualizarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteVisualizacaoAtendimentoDRG(null);
            }
        });
        JMenuItem atualizarAttDrg = new JMenuItem("Atualizar dados de atendimento a DRG",update);
        atualizarAttDrg.setBackground(Color.white);
        atualizarAttDrg.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                new LimiteAtualizacaoAtendimentoDRG();
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
        atendimentodrg.add(removerAttDrg);
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
        frame = new  JFrame("Sistema de apoio à gestão de hospitais americanos - SAGHA");
        frame.add(painel);
        frame.setJMenuBar(barramenu);
        frame.setBackground(Color.white);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        new LimitePrincipal();
    }
}
