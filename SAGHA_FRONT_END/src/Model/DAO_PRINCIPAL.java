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
