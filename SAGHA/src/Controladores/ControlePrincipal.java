package Controladores;

import Limites.LimitePrincipal.*;
import Limites.Usuarios.*;
import Model.Agente;
import Model.DAO_PRINCIPAL;
import Model.Gerente;
import Model.Hospital;
import java.util.Vector;
import org.hibernate.*;

public class ControlePrincipal
{
    private SaghaDashboard limSagha;
    private LimiteTelaLogin limLogin;
    private LimitePrincipal limPrincipal;
    private ControleHospital objCtrlHospitais;
    private LimiteCadastroUsuarios limCadUser;
    private LimiteRemocaoUsuario limDelUser;
    private LimiteVisualizacaoUsuarios limVisUser;
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
        int i=0;
        
        Vector vet = DAOPRINCIPAL.obterUsuarios();
        String form[][] = new String[vet.size()][3];
        
        for(Object obj : vet)
        {
            if(obj instanceof Agente)
            {
                Agente ag = (Agente) obj;
                form[i][0] = ag.getNome();
                form[i][1] = ag.getCpf();
                form[i][2] = "##";
            }
            else
            {
                Gerente gr = (Gerente) obj;
                form[i][0] = gr.getNome();
                form[i][1] = gr.getCpf();
                form[i][2] = ""+gr.getHospital().getId();
            }
            i++;
        }
        
        new LimiteVisualizacaoUsuarios(form);
    }
    
    /**
     * Ao usuario acionar botao de login, esse metodo verifica se o usuario existe
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
    
    /**
     * Metodo que obtem os dados da interface e cadastra um novo agente de saude
     */
    public void cadastrarAgenteOrgSaude()
    {
        //1: nome, 2: cpf, 3: Tipo de usuario, 4: Id do hospital
        try {
            String dados[] = limCadUser.obterDadosUsuario();
            Agente ag = new Agente(dados[1],dados[0],dados[2]);
            
            if(DAOPRINCIPAL.cadastrarAgente(ag))
                limCadUser.mensagemSucesso();
            else
                limCadUser.mensagemErro("Falha ao cadastrar novo usuario!");
        } catch (Exception exc) {
            limCadUser.mensagemErro(exc.getMessage());
        }
    }
    
    /**
     * Metodo que obtem os dados da interface de usuario e cadastra um novo gerente de hospital
     * (usuario do sistema)
     */
    public void cadastrarGerenteDeHospital()
    {
        try {
            String dados[] = limCadUser.obterDadosUsuario();
            Hospital h = getControladorHospitais().getDaoHospital().getHospital(Integer.parseInt(dados[4]));
            Gerente gr = new Gerente(dados[1],h,dados[0],dados[2]);
            
            if(DAOPRINCIPAL.cadastrarGerenteHospital(gr))
                limCadUser.mensagemSucesso();
            else
                limCadUser.mensagemErro("Falha no cadastro de novo usuario!");
        } catch (Exception exc) {
            limCadUser.mensagemErro(exc.getMessage());
        }
    }
    
    /**
     * Remove um usuario cadastrado no sistema a partir do CPF vindo da interface
     */
    public void removerUsuarioSagha()
    {
        try {
            String cpf = limDelUser.getCPF();
            if(DAOPRINCIPAL.removerUsuario(cpf))
            {
                limDelUser.mensagemSucesso();
                limDelUser.limparEntradaTexto();
            }
            else
                limDelUser.mensagemErro("Nenhum usuario com o cpf informado existente!");
        } catch (Exception exc) {
            limDelUser.mensagemErro(exc.getMessage());
        }
    }

    /**
     * Inicia a tela principal do sistema, apos realizado o login
     * @param user tipo de usuario que estara usando o sistema
     */    
    public void ExibirInterfacePrincipal(String user)
    {
        limPrincipal = new LimitePrincipal(this,user);
    }
    
    /**
     * Encerrar o sistema, primeiro encerrando a sessao no BD
     */
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
