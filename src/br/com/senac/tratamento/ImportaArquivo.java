package br.com.senac.tratamento;

import br.com.senac.bo.UsuarioBO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.senac.dao.PerfilDAO;
import br.com.senac.model.Contato;
import br.com.senac.model.Perfil;
import br.com.senac.model.Sexo;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportaArquivo {

    private Perfil perfil;

    /*
	 * Teste Importação
     */
    public static void main(String[] args) {
        ImportaArquivo imp = new ImportaArquivo();
        File arquivo = new File("D:\\TS\\PG.xls");
        try {
            imp.importar(arquivo);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void importar(File file) throws BiffException, IOException, SQLException {
        PerfilDAO dao = new PerfilDAO();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
        int linhas = sheet.getRows();

        for (int i = 1; i < linhas; i++) {
            Cell celulaCpf = sheet.getCell(3, i);
            Cell celulaNome = sheet.getCell(4, i);
            Cell celulaEmail = sheet.getCell(5, i);
            Cell celulaTel = sheet.getCell(7, i);
            Cell celulaSexo = sheet.getCell(9, i);
            Cell celulaDataNasc = sheet.getCell(10, i);

            perfil = new Perfil();
            perfil.setCpf(celulaCpf.getContents());

            String[] arrayNome = celulaNome.getContents().split(" ");

            perfil.setNome(arrayNome[0]);
            perfil.setSobrenome(
                    celulaNome.getContents().substring(perfil.getNome().length(), celulaNome.getContents().length()));
            Contato c = new Contato();
            c.setEmail(celulaEmail.getContents());
            c.setTelefone(celulaTel.getContents());
            perfil.setContato(c);

            try {
                Date data = formatter.parse(celulaDataNasc.getContents());
                perfil.setDataNascimento(new java.sql.Date(data.getTime()));
            } catch (ParseException e) {
                System.err.println("Erro ao formatar data.");
                e.printStackTrace();
            }

            if (celulaSexo.getContents().trim().equals("M")) {
                perfil.setSexo(Sexo.MASCULINO);
            } else {
                perfil.setSexo(Sexo.FEMININO);
            }

            dao.preCadastro(perfil);
            UsuarioBO bo = new UsuarioBO();
            bo.inserir(bo.geraUsuario(perfil, 1));

        }

    }
}
