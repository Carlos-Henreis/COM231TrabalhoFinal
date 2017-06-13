package Model;
// Generated 08/06/2017 20:47:30 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Drg generated by hbm2java
 */
public class Drg  implements java.io.Serializable {


     private short codigo;
     private String definicao;
     private Set atendimentoDrgs = new HashSet(0);

    public Drg() {
    }

	
    public Drg(short codigo, String definicao) {
        this.codigo = codigo;
        this.definicao = definicao;
    }
    public Drg(short codigo, String definicao, Set atendimentoDrgs) {
       this.codigo = codigo;
       this.definicao = definicao;
       this.atendimentoDrgs = atendimentoDrgs;
    }
   
    public short getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(short codigo) {
        this.codigo = codigo;
    }
    public String getDefinicao() {
        return this.definicao;
    }
    
    public void setDefinicao(String definicao) {
        this.definicao = definicao;
    }
    public Set getAtendimentoDrgs() {
        return this.atendimentoDrgs;
    }
    
    public void setAtendimentoDrgs(Set atendimentoDrgs) {
        this.atendimentoDrgs = atendimentoDrgs;
    }




}

