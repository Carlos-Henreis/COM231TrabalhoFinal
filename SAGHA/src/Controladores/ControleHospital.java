package Controladores;

import Limites.Hospital.*;
import Model.*;
import Model.Hospital;
import java.util.ArrayList;
import org.hibernate.Session;

/**
 *
 * @author Desenvolvedores SAGHA
 */
public class ControleHospital {

    private LimiteCadastroHospital limCad;
    private LimiteVisualizacaoHospital limVis;
    private LimiteAtualizacaoHospital limAtt;
    private LimiteRemocaoHospital limDel;
    private DAO_HOSPITAL DAO;
    private ArrayList<Hospital> lista;
    private Hospital h;

    public ControleHospital(Session sessao) {
        lista = new ArrayList<>();
        DAO = new DAO_HOSPITAL(sessao);
    }

    public void interfaceCadastroHospital() {
        limCad = new LimiteCadastroHospital(this);
    }

    public void interfaceRemocaoHospital() {
        limDel = new LimiteRemocaoHospital(this);
    }

    public void interfaceListagemHospitais(String form[][]) {
        limVis = new LimiteVisualizacaoHospital(form);
    }

    public void interfaceAtualizacaoHospital() {
        limAtt = new LimiteAtualizacaoHospital(this);
    }

    /**
     * Metodo que cadastra um hospital novo no sistema
     */
    public void cadastrarHospital() {
        try {
            String form[] = limCad.getDados();
            Hospital h = new Hospital(Integer.parseInt(form[0]), form[1], form[2], form[3], form[4]);

            if (DAO.cadastrarHospital(h)) {
                limCad.limparFormulario();
                limCad.mensagemSucesso();
            } else {
                limCad.mensagemErro("Falha ao cadastrar hospital!");
            }
        } catch (Exception exc) {
            limCad.mensagemErro("Verifique os dados informados!");
        }
    }

    /**
     * Metodo que remove um hospital cadastrado no sistema
     */
    public void removerHospital() {
        try {
            if (DAO.removerHospital(limDel.getID())) {
                limDel.limparEntradaTexto();
                limDel.mensagemSucesso();
            } else {
                limDel.mensagemErro("Falha ao remover hospital!");
            }
        } catch (Exception exc) {
            limDel.mensagemErro("Falha ao remover hospital!\nO ID deve ser numerico!");
        }
    }

    /**
     * Metodo que exibe os hospitais cadastrados no sistema
     */
    public void exibirHospitaisCadastrados() {
        lista = DAO.listarHospitais();
        String form[][] = new String[lista.size()][5];

        for (int i = 0; i < lista.size(); i++) {
            Hospital h = lista.get(i);
            form[i][0] = "" + h.getId();
            form[i][1] = h.getNome();
            form[i][2] = h.getRua();
            form[i][3] = h.getCidade();
            form[i][4] = h.getEstado();
        }

        interfaceListagemHospitais(form);
    }

    /**
     * Metodo que busca por um hospital para prosseguir atualizacao de dados de
     * hospital
     */
    public void parte1_buscaAtualizacao() {
        try {
            int id = limAtt.getIdHospital();

            Hospital h = DAO.getHospital(id);

            if (h == null) {
                limAtt.mensagemErro("Nenhum hospital com essa ID cadastrado!");
            } else {
                limAtt.interfaceAtualizacaoHospital(h);
            }
        } catch (Exception exc) {
            limAtt.mensagemErro("O id do hospital deve ser numerico!");
        }
    }

    public void atualizarDadosHospital_Parte2() {
        try {
            Hospital h = limAtt.getDadosAtualizados();

            if (DAO.atualizarDadosHospital(h)) {
                limAtt.limparEntradaDados();
                limAtt.mensagemSucesso();
            } else {
                limAtt.mensagemErro("Falha ao atualizar dados do hospital!");
            }
        } catch (Exception exc) {
            limAtt.mensagemErro(exc.getMessage());
        }
    }

    public DAO_HOSPITAL getDaoHospital() {
        return DAO;
    }

}
