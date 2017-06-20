package Limites.Relatorios;

import Controladores.ControleRelatorios;
import Model.RelatorioDRGGeral;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

public class LimiteRelatorioGeralDRG
{
    private JFrame janela;
    private JTable tabela;
    private JScrollPane painelRolagem;
    private JPanel principal,fundoExportar,topCriterio;
    private JComboBox criterioOrdenacao;
    private AbstractTableModel modeloTabela;
    private ControleRelatorios objCtrl;
    private ArrayList<RelatorioDRGGeral> listaDados;
    private JButton sair,pdf,svg;

    private final ImageIcon pdfIcone = new ImageIcon("img/pdf1.png");
    private final ImageIcon svgIcone = new ImageIcon("img/svg1.png");
    
    public LimiteRelatorioGeralDRG(ControleRelatorios pCtrl,ArrayList<RelatorioDRGGeral> lista)
    {
        objCtrl = pCtrl;
        listaDados = lista;
        
        //Criar botoes
        pdf = new JButton(pdfIcone);
        pdf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        sair = new JButton("SAIR");
        sair.setForeground(new Color(0,0,128));
        sair.setBorderPainted(false);
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
            }
        });
        svg = new JButton(svgIcone);
        svg.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        
        //Criar JTable e adicionar ao painel de rolagem
        criarTabela();
        painelRolagem = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelRolagem.getViewport().add(tabela);
        
        //Criar comboBox com o criterio de ordenacao
        String criterios[] = {"DEFINICAO DRG","TOTAL ALTAS","Nº HOSPITAIS CAPACITADOS"};
        criterioOrdenacao = new JComboBox(criterios);
        criterioOrdenacao.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                atualizarModeloDeDados(criterioOrdenacao.getSelectedIndex());
            }
        });
        
        //Criar paineis e adicionar seus devidos componentes
        fundoExportar = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        topCriterio = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        principal = new JPanel(new BorderLayout());
        JPanel painelTabela = new JPanel(new FlowLayout(FlowLayout.CENTER));
        
        //Adicionar componentes a seus devidos paineis
        painelTabela.add(tabela);
        
        topCriterio.add(new JLabel("Criterio de ordenaçao"));
        topCriterio.add(criterioOrdenacao);
        
        fundoExportar.add(pdf);
        fundoExportar.add(sair);
        fundoExportar.add(svg);
        
        principal.add(topCriterio,BorderLayout.PAGE_START);
        principal.add(fundoExportar,BorderLayout.PAGE_END);
        principal.add(painelTabela,BorderLayout.CENTER);
        
        //Criar JFrame e definir suas configuracoes
        janela = new JFrame();
        janela.add(principal);
        janela.setUndecorated(true);
        janela.setSize(800, 600);
        janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        janela.setLocationRelativeTo(null);
        janela.setAlwaysOnTop(true);
        janela.setVisible(true);
    }
    
    public final void criarTabela()
    {
        String colunas[] = {"Definicao da DRG","Total de altas","Hospitais capacitados"};
        modeloTabela = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return listaDados.size();
            }

            @Override
            public int getColumnCount() {
                return 3;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                switch(columnIndex)
                {
                    case 1:
                        return listaDados.get(rowIndex).getDefinicao();
                    case 2:
                        return  listaDados.get(rowIndex).getNumero_total_altas();
                    default:
                        return listaDados.get(rowIndex).getNumero_hospitais_capacitados();
                }
            }
        };
        
        tabela = new JTable(modeloTabela);
        tabela.getColumnModel().getColumn(0).setHeaderValue("Definicao da DRG");
        tabela.getColumnModel().getColumn(1).setHeaderValue("Total de altas");
        tabela.getColumnModel().getColumn(2).setHeaderValue("Hospitais capacitados");
    }
    
    /**
     * 
     * @param criterio criterio de ordenacao
     */
    public void atualizarModeloDeDados(int criterio)
    {
        criterio++;
        objCtrl.atualizarRelatorioGeral(criterio);
    }
    
    /**
     * Atualizar os dados da JTable de acordo com o criterio de ordenacao definido
     * @param listaNova 
     */
    public void atualizarInterface(ArrayList<RelatorioDRGGeral> listaNova)
    {
        //Remover todos os dados do array
        while(!listaDados.isEmpty())
            listaDados.remove(0);
        
        //Inserir novos dados no array
        for(RelatorioDRGGeral rel : listaNova)
            listaDados.add(new RelatorioDRGGeral(rel.getDefinicao(), rel.getNumero_total_altas(), rel.getNumero_hospitais_capacitados()));
        
        tabela.revalidate();
    }
}
