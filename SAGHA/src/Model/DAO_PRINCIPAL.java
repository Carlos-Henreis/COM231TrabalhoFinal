package Model;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO_PRINCIPAL
{
    private Session sessao;
    private Transaction tr;
    
    public DAO_PRINCIPAL()
    {
    }
    
    /**
     * Metodo para validar login de usuario
     * @param login login do usuario
     * @param senha senha do usuario
     * @return string com nome,funcao e ID do hospital ou nulo
     */
    public String[] realizarLogin(String login,String senha)
    {
        //p1 nome,p2 funcao
        String form[];
        
        //Buscar se o usuario e gerente de hospital
        Iterator it = sessao.createQuery("from Gerente").list().iterator();
        while(it.hasNext())
        {
            Gerente ag = (Gerente) it.next();

            if(ag.getCpf().equals(login) && ag.getSenha().equals(senha))
            {
                form = new String[3];
                form[0] = ag.getNome();
                form[1] = "GERENTE";
                form[2] = ""+ag.getHospital().getId();
                
                return form;
            }
        }
        
        //Buscar se o usuario e agente da organizacao de saude americana
        it = sessao.createQuery("from Agente").list().iterator();
        while(it.hasNext())
        {
            Agente ag = (Agente) it.next();

            if(ag.getCpf().equals(login) && ag.getSenha().equals(senha))
            {
                form = new String[2];
                form[0] = ag.getNome();
                form[1] = "AGENTE";
                
                return form;
            }
        }
        
        return null;
    }
    
    /**
     * Metodo para cadastrar um agente da organizaçao de saude americana no sagha (um usuario)
     * @param agnt Novo agente que sera cadasatrado
     * @return status da operacao
     */
    public boolean cadastrarAgente(Agente agnt)
    {
        sessao.save(agnt);
        sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo para cadastrar um gerente de hospital (usuario) no sagha
     * @param gnt gerente que sera cadastrado
     * @return status da operacao
     */
    public boolean cadastrarGerenteHospital(Gerente gnt)
    {
        sessao.save(gnt);
        sessao.beginTransaction();
        tr.commit();
        
        return tr.wasCommitted();
    }
    
    /**
     * Metodo que remove um usuario do sistema se baseando no seu CPF
     * @param cpf identificador do usuario
     * @return verdadeiro, se removido e falso se nao encontrado
     */
    public boolean removerUsuario(String cpf)
    {
        Agente ag = new Agente();
        ag = (Agente) sessao.get(Agente.class,cpf);        
        if(ag.getCpf().equals(cpf))
        {
            sessao.delete(ag);
            sessao.beginTransaction();
            tr.commit();
            
            return tr.wasCommitted();
        }
        
        Gerente gr = new Gerente();
        gr = (Gerente) sessao.get(Gerente.class, cpf);
        if(gr.getCpf().equals(cpf))
        {
            sessao.delete(gr);
            sessao.beginTransaction();
            tr.commit();
            
            return tr.wasCommitted();
        }
        
        return false;
    }
    
    public Vector obterUsuarios()
    {
        Vector vet = new Vector(1, 1);
        
        //Obter agentes
        Iterator it = sessao.createQuery("from Agente").list().iterator();
        while(it.hasNext())
        {
            vet.add(it.next());
        }
        
        //Obter Gerentes
        it = sessao.createQuery("from Gerente").list().iterator();
        while(it.hasNext())
        {
            vet.add(it.next());
        }
        
        return vet;
    }
    
    /**
     * Abrir sessao no banco de dados
     * @return Sessao aberta para demais DAO's
     */
    public Session abrirSessao()
    {
         //Abrir sessao no BD
        sessao = HibernateUtil.getSessionFactory().openSession();
        System.out.println("\n\n\n\n[INFO] - Conexao com sagha estabelecida!\n\n");
        
        return sessao;
    }
    
    /**
     * Encerrar sessao atual no banco de dados
     */
    public void encerrarSessao()
    {
        sessao.close();
        System.out.println("[INFO] - Conexao com sagha fechada!");
    }
}
