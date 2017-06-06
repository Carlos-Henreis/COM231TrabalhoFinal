package Limites.Hospital;

import Controladores.ControleHospital;
import Model.Hospital;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LimiteAtualizacaoHospital
{
    private JFrame frame;
    private JButton cadastrar,sair,buscar,sair2;
    private JLabel idJL,nomeJL,ruaJL,cidadeJL,estadoJL;
    private JTextField idTF,nomeTF,ruaTF,cidadeTF,estadoTF;
    private ActionListener cadListener,exitListener;
    private JPanel formulario1,formulario2,pcad,principal,externo1,externo2,cartoes,pbusca;
    private Hospital h;
    private CardLayout card;
    private ControleHospital objCtrl;

    public LimiteAtualizacaoHospital(ControleHospital pCtrl)
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
        buscar = new JButton("Buscar hospital");
        buscar.setBorderPainted(false);
        buscar.setForeground(Color.white);
        buscar.setBackground(new Color(0,0,128));
        cadastrar = new JButton("Atualizar");
        cadastrar.setBorderPainted(false);
        cadastrar.setForeground(Color.white);
        cadastrar.setBackground(new Color(0,0,128));
        sair = new JButton("Sair");
        sair.setBorderPainted(false);
        sair.setForeground(Color.white);
        sair.setBackground(new Color(0,0,128));
        sair2 = new JButton("Sair");
        sair2.setBorderPainted(false);
        sair2.setForeground(Color.white);
        sair2.setBackground(new Color(0,0,128));
        
        //Gerar Listener's e adicionar aos botoes
        cadListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(cadastrar))
                {
                    //BOTAO DE CADASTRO ACIONADO
                    objCtrl.atualizarDadosHospital_Parte2();
                }
                else
                {
                    //BOTAO DE BUSCA ACIONADO
                    objCtrl.parte1_buscaAtualizacao();
                }
            }
        };
        cadastrar.addActionListener(cadListener);
        buscar.addActionListener(cadListener);
        
        exitListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        };
        sair.addActionListener(exitListener);
        sair2.addActionListener(exitListener);
        
        //Criar JPanel's
        formulario1 = new JPanel(new GridLayout(2, 2, 10, 20));
        formulario2 = new JPanel(new GridLayout(5, 2, 10, 20));
        pcad = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        pbusca = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        principal = new JPanel(new FlowLayout(FlowLayout.CENTER));
        externo1 = new JPanel();
        externo2 = new JPanel();
        BoxLayout box = new BoxLayout(externo1,BoxLayout.Y_AXIS);
        externo1.setLayout(box);
        externo1.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        BoxLayout box2 = new BoxLayout(externo2,BoxLayout.Y_AXIS);
        externo2.setLayout(box2);
        externo2.setBorder(BorderFactory.createLineBorder(new Color(0,0,128),2));
        
        card = new CardLayout();
        cartoes = new JPanel(card);
        
        //Adicionar componentes aos devidos paineis
        pcad.add(cadastrar);
        pcad.add(sair);
        
        pbusca.add(buscar);
        pbusca.add(sair2);
        
        formulario1.add(idJL);
        formulario1.add(idTF);
        formulario1.add(Box.createHorizontalGlue());
        formulario1.add(pbusca);
        formulario2.add(nomeJL);
        formulario2.add(nomeTF);
        formulario2.add(ruaJL);
        formulario2.add(ruaTF);
        formulario2.add(cidadeJL);
        formulario2.add(cidadeTF);
        formulario2.add(estadoJL);
        formulario2.add(estadoTF);
        formulario2.add(Box.createHorizontalGlue());
        formulario2.add(pcad);
        
        principal.add(formulario1);
        
        externo1.add(Box.createGlue());
        externo1.add(principal);
        externo1.add(Box.createGlue());
        
        externo2.add(Box.createGlue());
        JPanel aux = new JPanel(new FlowLayout(FlowLayout.CENTER));
        aux.add(formulario2);
        externo2.add(aux);
        externo2.add(Box.createGlue());
        
        cartoes.add(externo1);
        cartoes.add(externo2);
        card.addLayoutComponent(externo1, "BUSCA");
        card.addLayoutComponent(externo2,"ATUALIZACAO");
        
        frame = new JFrame("Cadastro de hospital");
        frame.add(cartoes);
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
        JOptionPane.showMessageDialog(cartoes,msg,"Erro na atualizaçao",JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * Exibe mensagem de sucesso no cadastro e reseta interface para DEFAULT
     */
    public void mensagemSucesso()
    {
        JOptionPane.showMessageDialog(principal, "Dados do hospital atualizados!");
        card.show(cartoes,"BUSCA");
    }
    
    /**
     * Metodo que retorna o ID do hospital informado pelo usuario
     * @return id do hospotal a ser buscado
     * @throws Exception caso o valor digitado nao seja inteiro ou nao foi nada digitado
     */
    public int getIdHospital() throws Exception
    {
        return Integer.parseInt(idTF.getText());
    }
    
    /**
     * Metodo para obter os novos dados do hospital da interface de usuario
     * @return Novos dados do hospital
     * @throws Exception Caso o usuario nao informe todos os campos
     */
    public Hospital getDadosAtualizados() throws Exception
    {
        String form[] = new String[4];
        
        //Verificar se todos os dados foram preenchidos
        if(cidadeTF.getText().isEmpty() ||  estadoTF.getText().isEmpty() || nomeTF.getText().isEmpty() ||  ruaTF.getText().isEmpty())
            throw new Exception("Voce deve informar todos os campos!");
        
        //Verificar se o campo estado foi preenchido direito
        if(estadoTF.getText().length() > 2)
            throw new Exception("O estado e um campo numerico com no maximo 2 caracteres!");
        
        //Atualizar dados do hospital
        h.setNome(nomeTF.getText());
        h.setRua(ruaTF.getText());
        h.setCidade(cidadeTF.getText());
        h.setEstado(estadoTF.getText());
        
        return h;
    }
    
    /**
     * Metodo que seta os dados vindos da busca na interface de autualizacao de hospital
     * @param h2 dados do hospital obtido
     */
    public void interfaceAtualizacaoHospital(Hospital h2)
    {
        //Manter hospital que esta sendo atualizado
        h = h2;
        
        //Inserir dados desse hospital na interface
        nomeTF.setText(h.getNome());
        ruaTF.setText(h.getRua());
        cidadeTF.setText(h.getCidade());
        estadoTF.setText(h.getEstado());
        
        //Atualizar interface
        card.show(cartoes,"ATUALIZACAO");
    }
    
    /**
     * Metodo que limpa as entradas de texto da interface
     */
    public void limparEntradaDados()
    {
        idTF.setText("");
        nomeTF.setText("");
        ruaTF.setText("");
        cidadeTF.setText("");
        estadoTF.setText("");
    }
}
