package Model;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class DAO_PRINCIPAL
{
    private Session sessao;
    private Transaction tr;
    
    public DAO_PRINCIPAL()
    {
    }
    
    public String[] realizarLogin()
    {
        //p1 nome,p2 funcao
        String form[] = new String[2];
        
        
        
        return form;
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
