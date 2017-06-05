package Controladores;

import Model.Drg;
import Limites.DRG.*;
import java.util.ArrayList;

/**
 *
 * @author Desenvolvedores SAGHA
 */
public class ControleDrg
{
    private ArrayList<Drg> listaDrg;
        private LimiteCadastroDRG limCad;
    private LimiteVisualizacaoDRG limVis;
    private LimiteAtualizacaoDRG limAtt;
    private LimiteRemocaoDRG limDel;
    
    public ControleDrg()
    {
    }
    
    public void interfaceCadastroDRG()
    {
        limCad = new LimiteCadastroDRG(this);
    }
    
    public void interfaceVisualizacaoDRG()
    {
        String vet[][] = new String[1][2];
        vet[0][0] = vet[0][1] = "DAda";
        limVis = new LimiteVisualizacaoDRG(vet);
    }
    
    public void interfaceAtualizacaoDRG()
    {
        limAtt = new LimiteAtualizacaoDRG(this);
    }
    
    public void interfaceRemocaoDRG()
    {
        limDel = new LimiteRemocaoDRG(this);
    }
}
