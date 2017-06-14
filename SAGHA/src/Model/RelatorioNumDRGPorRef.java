/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author carlos
 */
public class RelatorioNumDRGPorRef {
    
    String regiao_referencia;
    int qtd_DRG;

    public RelatorioNumDRGPorRef(String regiao_referencia, int qtd_DRG) {
        this.regiao_referencia = regiao_referencia;
        this.qtd_DRG = qtd_DRG;
    }

    public String getRegiao_referencia() {
        return regiao_referencia;
    }

    public void setRegiao_referencia(String regiao_referencia) {
        this.regiao_referencia = regiao_referencia;
    }

    public int getQtd_DRG() {
        return qtd_DRG;
    }

    public void setQtd_DRG(int qtd_DRG) {
        this.qtd_DRG = qtd_DRG;
    }
    
    
}
