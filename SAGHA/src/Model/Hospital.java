package Model;
// Generated 08/06/2017 20:47:30 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Hospital generated by hbm2java
 */
public class Hospital  implements java.io.Serializable {


     private int id;
     private String nome;
     private String rua;
     private String cidade;
     private String estado;
     private Set atendimentoDrgs = new HashSet(0);
     private Set gerentes = new HashSet(0);
     private Set hospitaisRegiaos = new HashSet(0);

    public Hospital() {
    }

	
    public Hospital(int id, String nome, String rua, String cidade, String estado) {
        this.id = id;
        this.nome = nome;
        this.rua = rua;
        this.cidade = cidade;
        this.estado = estado;
    }
    public Hospital(int id, String nome, String rua, String cidade, String estado, Set atendimentoDrgs, Set gerentes, Set hospitaisRegiaos) {
       this.id = id;
       this.nome = nome;
       this.rua = rua;
       this.cidade = cidade;
       this.estado = estado;
       this.atendimentoDrgs = atendimentoDrgs;
       this.gerentes = gerentes;
       this.hospitaisRegiaos = hospitaisRegiaos;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRua() {
        return this.rua;
    }
    
    public void setRua(String rua) {
        this.rua = rua;
    }
    public String getCidade() {
        return this.cidade;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getAtendimentoDrgs() {
        return this.atendimentoDrgs;
    }
    
    public void setAtendimentoDrgs(Set atendimentoDrgs) {
        this.atendimentoDrgs = atendimentoDrgs;
    }
    public Set getGerentes() {
        return this.gerentes;
    }
    
    public void setGerentes(Set gerentes) {
        this.gerentes = gerentes;
    }
    public Set getHospitaisRegiaos() {
        return this.hospitaisRegiaos;
    }
    
    public void setHospitaisRegiaos(Set hospitaisRegiaos) {
        this.hospitaisRegiaos = hospitaisRegiaos;
    }




}


