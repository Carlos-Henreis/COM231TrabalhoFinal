package Controladores;

import Model.*;
import java.util.ArrayList;
import Limites.Relatorios.*;
import org.hibernate.Session;

public class ControleRelatorios
{
    private DAO_RELATORIOS DAO;
    private Session sessao;
    
    public ControleRelatorios(Session sessao)
    {
        this.sessao = sessao;
        DAO = new DAO_RELATORIOS(sessao);
    }
    
}
