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
public class RelatorioDRGGeral {
    String definicao;
    int numero_total_altas;
    int numero_hospitais_capacitados;

    public RelatorioDRGGeral(String definicao, int numero_total_altas, int numero_hospitais_capacitados) {
        this.definicao = definicao;
        this.numero_total_altas = numero_total_altas;
        this.numero_hospitais_capacitados = numero_hospitais_capacitados;
    }

    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public int getNumero_total_altas() {
        return numero_total_altas;
    }

    public void setNumero_total_altas(int numero_total_altas) {
        this.numero_total_altas = numero_total_altas;
    }

    public int getNumero_hospitais_capacitados() {
        return numero_hospitais_capacitados;
    }

    public void setNumero_hospitais_capacitados(int numero_hospitais_capacitados) {
        this.numero_hospitais_capacitados = numero_hospitais_capacitados;
    }
    
    
}
