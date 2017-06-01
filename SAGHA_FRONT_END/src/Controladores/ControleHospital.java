package Controladores;

import Limites.Hospital.*;
import Model.Hospital;

public class ControleHospital
{
    private LimiteCadastroHospital limCad;
    private LimiteVisualizacaoHospital limVis;
    private LimiteAtualizacaoHospital limAtt;
    private LimiteRemocaoHospital limDel;
    
    public ControleHospital()
    {
        
    }
    
    /**
     * Metodo que aciona a interface de cadastro de hospital
     */
    public void interfaceCadastrohospital()
    {
         limCad = new LimiteCadastroHospital();
    }
    
    /**
     * Metodo que aciona a interface de remocao de hospital
     */
    public void interfaceRemocaoHospital()
    {
        limDel = new LimiteRemocaoHospital();
    }
    
    /**
     * Metodo que aciona a interface de atualizacao de hospital
     */
    public void interfaceAtualizacaoHospital()
    {
        limAtt = new LimiteAtualizacaoHospital();
    }
    
    /**
     * Metodo que aciona a interface de visualizacao de hospitais
     */
    public void interfaceVisualizacaoHospitais()
    {
        limVis = new LimiteVisualizacaoHospital(null);
    }
    
    /**
     * Metodo que obtem os dados da interface e cadastra um novo hospital
     */
    public boolean cadastrarHospital(String form[])
    {
        //Gerar novo hospital
        Hospital h = new Hospital(Integer.parseInt(form[0]), form[1]);
        return false;
    }
}
