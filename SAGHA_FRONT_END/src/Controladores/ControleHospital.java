package Controladores;

import Limites.Hospital.*;
import Model.Hospital;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvedores SAGHA
 */
public class ControleHospital
{
    private LimiteCadastroHospital limCad;
    private LimiteVisualizacaoHospital limVis;
    private LimiteAtualizacaoHospital limAtt;
    private LimiteRemocaoHospital limDel;
    private ArrayList<Hospital> lista;
    private Hospital h;
    
    public ControleHospital()
    {
        lista = new ArrayList<>();
    }
    
    public void interfaceCadastroHospital()
    {
        limCad = new LimiteCadastroHospital(this);
    }
    
    public void interfaceRemocaoHospital()
    {
        limDel = new LimiteRemocaoHospital(this);
    }
    
    public void interfaceListagemHospitais()
    {
        String vet[][] = new String[1][5];
                vet[0][0] = "da";
                vet[0][1] = "da";
                vet[0][2] = "da";
                vet[0][3] = "da";
                vet[0][4] = "da";
        limVis = new LimiteVisualizacaoHospital(vet);
    }
    
    public void interfaceAtualizacaoHospital()
    {
        limAtt = new LimiteAtualizacaoHospital(this);
    }
}
