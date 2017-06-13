/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

public class Relatorio1 {
    String definicao;
    double media;

    public Relatorio1(String definicao, double media) {
        this.definicao = definicao;
        this.media = media;
    }

    public Relatorio1() {
    }
    
    public String getDefinicao() {
        return definicao;
    }

    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double media) {
        this.media = media;
    }
    
}
