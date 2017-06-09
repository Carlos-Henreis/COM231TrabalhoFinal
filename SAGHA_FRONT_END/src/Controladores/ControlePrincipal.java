package Controladores;

import Limites.LimitePrincipal.*;
import Limites.Usuarios.*;
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
    public final String USUARIO_DE_HOSPITAL = "USUARIO";
    public final String AGENTE_ALTO_SAUDE = "AGENTE";
    
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
        this.interfaceDeLogin();
    }
    
    /**
     * Metodo que exibe a primeira interface ao usuario, a interface de login
     */
    public final void interfaceDeLogin()
    {
        limLogin = new LimiteTelaLogin(this);
    }
    
    public void interfaceCadastroUsuario()
    {
        new LimiteCadastroUsuarios(this);
    }
    
    public void interfaceRemocaoUsuario()
    {
        new LimiteRemocaoUsuario(this);
    }
    
    public void interfaceVisualizacaoUsuarios()
    {
        String form[][] = new String[1][3];
        form[0][0] = "Vitor";
        form[0][1] = "Vitor";
        form[0][2] = "Vitor";
        
        new LimiteVisualizacaoUsuarios(form);
    }
    
    /**
     * Ao usuario acionar botao de login, esse metodo verifica se o usuario existe
     * @param login Identificador do usuario
     * @param senha Senha do usuario no sistema
     * @return String que indica o tipo de usuario
     */
    public void login()
    {
        String login;
        String senha;
        
        try {
            String form[] = limLogin.obterDadosInterface();
            login = form[0];
            senha = form[1];
            
            String dados[] =  DAOPRINCIPAL.realizarLogin(login, senha);
            
            if(dados == null)
                limLogin.mensagemErro("Nenhum usuario encontrado!\nVerifique os dados informados!");
            else
                limLogin.sucessoLogin(dados[0],dados[1]);
        } catch (Exception exc) {
            limLogin.mensagemErro(exc.getMessage());
        }
    }
    
    public void ExibirInterfacePrincipal(String user)
    {
        limPrincipal = new LimitePrincipal(this,user);
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
