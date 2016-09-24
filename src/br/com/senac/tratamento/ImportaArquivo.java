package br.com.senac.tratamento;

import br.com.senac.dao.PessoaDAO;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.senac.model.Pessoa;
import br.com.senac.model.Sexo;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook; 
import jxl.read.biff.BiffException;


public class ImportaArquivo {
    private Pessoa pessoa;    
    
    /* 
        Teste Importação
    */
    public static void main(String[] args) {
        ImportaArquivo imp = new ImportaArquivo();
        File arquivo = new File("C:\\SNAC\\PG.xls");
        try {
            imp.importar(arquivo);
        } catch(Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void importar(File file) throws BiffException, IOException, SQLException {
	PessoaDAO dao = new PessoaDAO();	
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");	
        Workbook workbook = Workbook.getWorkbook(file); 
        Sheet sheet = workbook.getSheet(0);     
        int linhas = sheet.getRows();    
      
        for(int i = 1; i < linhas; i++ )         {
            Cell celulaCpf = sheet.getCell(3, i); 
            Cell celulaNome = sheet.getCell(4, i);      
            Cell celulaSexo = sheet.getCell(9, i); 
            Cell celulaDataNasc = sheet.getCell(10, i);     	 
  		
            pessoa = new Pessoa();
            pessoa.setCpf(celulaCpf.getContents());
    	  
            String[] arrayNome = celulaNome.getContents().split(" ");
            pessoa.setNome(arrayNome[0]);                       	  
    	  
            pessoa.setSobrenome(celulaNome.getContents().substring(pessoa.getNome().length(), celulaNome.getContents().length()));
            
            try {
                Date data = formatter.parse(celulaDataNasc.getContents());
                pessoa.setDataNascimento(new java.sql.Date(data.getTime()));
            } catch (ParseException e) {
                System.err.println("Erro ao formatar data.");
                e.printStackTrace();
            }
                                   
            if (celulaSexo.getContents().trim().equals("M")) {    		  
    		pessoa.setSexo(Sexo.MASCULINO);
            } else {
                pessoa.setSexo(Sexo.FEMININO);
            }
            
            dao.inserir(pessoa);
        }

    }
}
