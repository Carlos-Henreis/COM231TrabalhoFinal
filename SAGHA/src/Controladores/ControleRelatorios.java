package Controladores;

import Limites.Relatorios.*;
import Model.*;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import org.hibernate.Session;
import java.io.FileOutputStream;
import java.io.IOException;

public class ControleRelatorios
{
    private DAO_RELATORIOS DAO;
    private Session sessao;
    private ArrayList <RelatorioPagMedioDrgEstado> listaDados;
    private LimiteRelatorioPagamentoMedioDrgEstado limRelatorioPagMedioDrgEstado;
    private LimiteRelatorioPagamentoMedioDRG limRelatorioPagMedioDRG;
    private LimiteRelatorioContagemDrgAtendidaPorEstado limRelatorioContagemDrgAtendidaEstado;
    private LimiteRelatorioGeralDRG limRelatorioGeralDRG;
    private LimiteRelatorioAtendimentoDrgRegReferencia limRelatorioPorRegiao;
    
    public ControleRelatorios(Session sessao)
    {
        this.sessao = sessao;
        DAO = new DAO_RELATORIOS(sessao);
        listaDados = new ArrayList<>();
    }
    
    /**
     * Exibe o GUI do relatorio ao usuario
     */
    public void interfaceRelatorioPagMedioDrgEstado()
    {
        limRelatorioPagMedioDrgEstado = new LimiteRelatorioPagamentoMedioDrgEstado(this,DAO.obterListaDeEstados(), listaDados, listaDados);
    }
    
    /**
     * Atualiza a interface do relatorio com os novos dados do estado selecionado
     * @param estado o estado selecionado pelo usuario
     */
    public void atualizarInterfaceRelatorioPagMedioDrgEstado(String estado)
    {
        listaDados = DAO.relatorioPagMedioDrgEstado(estado);
        
        //Gerar sublistas com os maiores e menores valores
        ArrayList<RelatorioPagMedioDrgEstado> maior = new ArrayList<>();
        ArrayList<RelatorioPagMedioDrgEstado> menor = new ArrayList<>();
        
        //Adicionar dados a lista com os menores valores
        for(int i=0 ; i<5 ; i++)
        {
            if(listaDados.size() > i)
                menor.add(listaDados.get(i));
        }
        
        int dim = listaDados.size();
        if(listaDados.size() >= 5)
            dim = 5;
        else
            dim = listaDados.size();
        
        //Adicionar dados a lista com os maiores valores
        for(int j=1 ; j<=dim ; j++)
        {
            maior.add(listaDados.get(listaDados.size()-j));
        }
        
        //Atualizar a interface
        limRelatorioPagMedioDrgEstado.atualizarParaNovoEstado(maior, menor);
    }
    
    /**
     * Exibir interface do relatorio de pagamentos medios de acordo com DRG
     */
    public void interfaceRelatorioPagamentoMedioDRG()
    {
        listaDados = DAO.relatorioPagMedioPorDrg();
        String dados[][] = new String[listaDados.size()][2];
        
        int i=0;
        for(RelatorioPagMedioDrgEstado rel : listaDados)
        {
            dados[i][0] = rel.getDefinicao();
            dados[i][1] = ""+rel.getMedia();
            i++;
        }
        
        limRelatorioPagMedioDRG = new LimiteRelatorioPagamentoMedioDRG(this, dados);
    }
    
    /**
     * Exibe a interface do relatorio de contagem de DRG's atendidas em cada estado
     */
    public void interfaceRelatorioContagemDrgAtendidaPorEstado()
    {
        limRelatorioContagemDrgAtendidaEstado = new LimiteRelatorioContagemDrgAtendidaPorEstado(this,DAO.relatorioContNumeroDRGsEstado());
    }
    
    /**
     * Exibe a interface do relatorio geral de DRG
     */
    public void interfaceRelatorioGeralDRG()
    {
        limRelatorioGeralDRG = new LimiteRelatorioGeralDRG(this, DAO.relatorioDRG(1));
    }
    
    /**
     * Atualizar dados do relatorio - Usuario acionou o criterio de ordenaçao
     * @param criterio criterio de ordenacao escolhido
     */
    public void atualizarRelatorioGeral(int criterio)
    {
        limRelatorioGeralDRG.atualizarInterface(DAO.relatorioDRG(criterio));
    }
    
    /**
     * Visualizar relatorio de DRGs atendidas de acordo com a regiao de referencia
     */
    public void interfaceRelatorioDrgRegiaoReferecia()
    {
        limRelatorioPorRegiao = new LimiteRelatorioAtendimentoDrgRegReferencia(this, DAO.relatorioNumDRGPorRef());
    }
    
    /**
     * Obter tabela para adicionar ao pdf a partir da JTable
     * @param jTable tabela com os dados
     * @param nameHeaders cabeçalho da JTable
     * @return tabela para ser adicionada ao PDF
     */
    public PdfPTable getPdfPTable(JTable jTable, String[] nameHeaders)
    {
        PdfPTable tab = new PdfPTable(nameHeaders.length);
        
        for (int i = 0; i < nameHeaders.length; i++) {
            tab.addCell(nameHeaders[i]);
        }
        
        int rowCount = jTable.getRowCount();
        int collumCount = jTable.getColumnCount();
        
        for (int x = 0; x < rowCount; x++) {
            for (int y = 0; y < collumCount; y++) {
                try{
                    tab.addCell(GetData(jTable, x, y).toString());
                }catch(Exception exp){
                    System.out.println("Error to create pdf table "+exp);
                };
            }
        }
        
        return tab;
    }
    
    public Object GetData(JTable table, int row_index, int col_index) throws Exception
    {
        return table.getModel().getValueAt(row_index, col_index);
    }
    
    /**
     *
     * @param filename
     * @param nomerelatorio
     * @param nameHeaders
     * @param jtable
     */
    public void gerarPDF(String filename, String nomerelatorio, String[] nameHeaders, JTable jtable) {
        
        Document doc = new Document(PageSize.A4, 10, 10, 10, 10);
        filename += ".pdf";
        
        try {
            // INSTANCIA O ARQUIVO COMO PDF NO GERADOR
            PdfWriter.getInstance(doc, new FileOutputStream(filename));
            // ABRE O ARQUIVO
            doc.open();
            // FONTE ESTILO
            Font f1 = new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD);
            
            // INICIO DO CABEÇALHO
            // AREA DO CABEÇALHO - TABELA
            PdfPTable HEADER = new PdfPTable(new float[] { 0.25f, 0.75f }); //cria uma HEADER com 2 colunas
            
            // CELULA 1 - LOGO
            PdfPCell cel1 = new PdfPCell(); //cria uma celula com parametro de Image.getInstance com o caminho da imagem do cabeçalho
            cel1.addElement(Image.getInstance("img/logo_login.jpeg"));
            cel1.setBorder(-1); // aqui vc tira as bordas da celula 
            
            // CELULA 2 - TEXTO CABEÇALHO
            PdfPCell cel2 = new PdfPCell(); //adiciona o paragrafo com o titulo na segunda celula.
            Paragraph hp = new Paragraph("\nSAGHA - Sistema de Apoio À Gestão Hospitais Americanos\n"
                    + nomerelatorio, f1); 
            hp.setAlignment(Element.ALIGN_CENTER);
            cel2.setBorder(-1);
            cel2.addElement(hp);
            
            HEADER.addCell(cel1); //aqui adiciona as celulas na HEADER.
            HEADER.addCell(cel2);
            doc.add(HEADER); // coloca a HEADER na pagina do PDF.
            // FIM DO CABEÇALHO
            
            // ADICIONA A JTABLE NO PDF
            PdfPTable table = getPdfPTable(jtable, nameHeaders);
            boolean add = doc.add(table);


        } catch (DocumentException | IOException ex) {
            System.out.println("Error: " + ex);
        } finally {
            doc.close();
        }

        // ESSE TRY É PARA ABRIR O PDF ASSIM QUE TERMINAR DE CRIAR ELE
        try {
            Desktop.getDesktop().open(new File(filename));

        } catch (IOException ex) {
            //Logger.getLogger(ControleRelatorios.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Falha ao gerar arquivo PDF!");
        }
    }
}
