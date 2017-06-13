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
public class RelatorioContagemDRGEstado {
    String estado;
    int contagem;

    public RelatorioContagemDRGEstado(String estado, int contagem) {
        this.estado = estado;
        this.contagem = contagem;
    }

    public RelatorioContagemDRGEstado() {
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getContagem() {
        return contagem;
    }

    public void setContagem(int contagem) {
        this.contagem = contagem;
    }
    
    
}
