package Controladores;

import Limites.LimitePrincipal.*;
import Model.DAO_PRINCIPAL;
import org.hibernate.*;

public class ControlePrincipal
{
    private SaghaDashboard limSagha;
    private LimiteTelaLogin limLogin;
    private LimitePrincipal limPrincipal;
    private ControleHospital objCtrlHospitais;
    private ControleDrg objCtrlDrg;
    private ControleAtendimentoDRG objCtrlAttDrg;
    private ControleHospitaisRegiao objCtrlHospReg;
    private Session sessao;
    private DAO_PRINCIPAL DAOPRINCIPAL;
    
    //Definir constantes de login
    public final String USUARIO_DE_HOSPITAL = "USUARIO_HOSPITAL";
    public final String AGENTE_ALTO_SAUDE = "AGENTE_DA_ORG_SAUDE";
    
    public ControlePrincipal()
    {
        //Conexao com banco
        DAOPRINCIPAL = new DAO_PRINCIPAL();
        sessao = DAOPRINCIPAL.abrirSessao();
        
        //Criar controladores
        objCtrlHospitais = new ControleHospital(sessao);
        objCtrlDrg = new ControleDrg(sessao);
        objCtrlAttDrg = new ControleAtendimentoDRG(sessao);
        objCtrlHospReg = new ControleHospitaisRegiao(sessao);
        
        //Iniciar interface parao usuario
        this.iniciarSaghaDashboard();
    }
    
    /**
     * Metodo que exibe a primeira interface ao usuario, a interface de login
     */
    public final void interfaceDeLogin()
    {
        limLogin = new LimiteTelaLogin(this);
    }
    
    /**
     * Iniciar sistema sagha no dashboard
     */
    public final void iniciarSaghaDashboard()
    {
        limSagha = new SaghaDashboard(this);
    }
    
    /**
     * Ao usuario acionar botao de login, esse metodo verifica se o usuario existe
     * @param login Identificador do usuario
     * @param senha Senha do usuario no sistema
     * @return String que indica o tipo de usuario
     */
    public String login(String login,String senha)
    {
        return AGENTE_ALTO_SAUDE;
    }
    
    /**
     * Metodo que inicia a interface como um usuario default (pode apenas visualizar os dados)
     */
    public void EntrarComoUsuarioDefault()
    {
        
    }
    
    public void ExibirInterfacePrincipal()
    {
        limPrincipal = new LimitePrincipal(this);
    }
    
    public void encerrarSagha()
    {
        DAOPRINCIPAL.encerrarSessao();
        System.exit(0);
    }
    
    public ControleHospital getControladorHospitais()
    {
        return objCtrlHospitais;
    }
    
    public ControleDrg getControladorDRG()
    {
        return objCtrlDrg;
    }
    
    public ControleAtendimentoDRG getControladorAtendimentoDRG()
    {
        return objCtrlAttDrg;
    }
    
    public ControleHospitaisRegiao getControladorHospitaisRegiao()
    {
        return objCtrlHospReg;
    }
}
