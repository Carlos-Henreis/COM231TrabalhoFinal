package Model;
// Generated 08/06/2017 15:34:03 by Hibernate Tools 4.3.1



/**
 * Usuarios generated by hbm2java
 */
public class Usuarios  implements java.io.Serializable {


     private UsuariosId id;
     private String nome;
     private String cpf;
     private String funcao;

    public Usuarios() {
    }

	
    public Usuarios(UsuariosId id) {
        this.id = id;
    }
    public Usuarios(UsuariosId id, String nome, String cpf, String funcao) {
       this.id = id;
       this.nome = nome;
       this.cpf = cpf;
       this.funcao = funcao;
    }
   
    public UsuariosId getId() {
        return this.id;
    }
    
    public void setId(UsuariosId id) {
        this.id = id;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return this.cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getFuncao() {
        return this.funcao;
    }
    
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }




}


