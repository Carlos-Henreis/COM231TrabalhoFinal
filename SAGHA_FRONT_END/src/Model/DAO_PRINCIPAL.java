package Model;

import java.util.Iterator;
import javax.swing.JOptionPane;
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
     * @return string com dados ou nulo, caso nao encontrado
     */
    public String[] realizarLogin(String login,String senha)
    {
        //p1 nome,p2 funcao
        String form[] = new String[2];

        Iterator it = sessao.createQuery("from Usuarios").list().iterator();
        while(it.hasNext())
        {
            Usuarios us = (Usuarios) it.next();

            if(us.getId().getLogin().equals(login) && us.getId().getSenha().equals(senha))
            {
                form[0] = us.getNome();
                form[1] = us.getFuncao();
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
