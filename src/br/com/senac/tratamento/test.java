package br.com.senac.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.Test;

import br.com.senac.bo.PerfilBO;
import br.com.senac.dao.Conexao;
import br.com.senac.dao.PerfilDAO;
import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Contato;
import br.com.senac.model.CursoEgresso;
import br.com.senac.model.Endereco;
import br.com.senac.model.Estado;
import br.com.senac.model.Experiencia;
import br.com.senac.model.Perfil;
import br.com.senac.model.Sexo;
import br.com.senac.model.TipoTrabalho;
import br.com.senac.model.Titulo;
import br.com.senac.tratamento.ImportaArquivo;
import jxl.read.biff.BiffException;

public class test {

	private PerfilDAO perfilDAO;
	private Perfil perfil;
	private File file;
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	public test() {
		perfilDAO = new PerfilDAO();
	}

	@Test
	public void salvarPerfil() throws FalhaBancoException, SQLException, ParseException {

		perfil = new Perfil();

		perfil.setCpf("09701981948");
		perfil.setNome("João");
		perfil.setSexo(Sexo.MASCULINO);
		perfil.setSobrenome("Vitor");

		CursoEgresso curso = new CursoEgresso();
		Endereco endereco = new Endereco();
		Contato contato = new Contato();
		Experiencia exp = new Experiencia();
		ArrayList listaCurso = new ArrayList<>();
		ArrayList listaExp = new ArrayList<>();

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		perfil.setDataNascimento(df.parse("25-05-1997"));
		perfil.setFormacaoBasica("nivel susu");
		perfil.setFormacaoEnsinoMedio("susuususu");

		curso.setNomeCurso("curso test");

		curso.setTitulo(Titulo.TECNOLOGO);

		curso.setUnidadeSenac("senai");
		curso.setAnoIngresso(2011);
		curso.setSemestreIngresso(1);
		curso.setAnoConclusao(2013);
		curso.setSemestreConclusao(5);

		endereco.setRua("rua raimundo susu");
		endereco.setNumero(12);
		endereco.setComplemento("ao lado do susu");
		endereco.setCidade("Susulandia");
		endereco.setCep("46296587");
		Estado estado = new Estado();
		estado.setId(2);
		endereco.setEstado(estado);

		contato.setEmail("joaoschaidt@hotmail.com");
		contato.setTelefone("99805567");

		exp.setEmpresa("Empresa susuMoveis");
		exp.setCargo("Gerente susu");

		exp.setTipoTrabalho(TipoTrabalho.CONCURSO);

		exp.setDataIngresso(df.parse("12-01-2016"));
		exp.setDataSaida(df.parse("05-04-2016"));

		listaCurso.add(curso);
		listaExp.add(exp);

		perfil.setCurso(listaCurso);
		perfil.setExperiencias(listaExp);

		perfil.setEndereco(endereco);
		perfil.setContato(contato);

		perfilDAO.inserir(perfil);

		assertNotNull(perfil.getIdPerfil());
	}

	@Test
	public void salvarExcel() throws BiffException, IOException, SQLException {

		conn = Conexao.getConnection();
		String consulta = "select MAX(idPerfil) from perfil";
		int max;

		psmt = conn.prepareStatement(consulta);
		rs = psmt.executeQuery();

		if (rs.next()) {
			max = rs.getInt(1);
		} else {
			max = 0;
		}

		ImportaArquivo importador = new ImportaArquivo();
		file = new File("D:\\TS\\PG.xls");// passar o file
		assertTrue(file.exists());
		importador.importar(file);

		rs = psmt.executeQuery();

		assertTrue("Algo foi inserido", max < rs.getInt(1));

		Conexao.closeResources(conn, psmt);

	}

	@Test
	public void verificaExclusao() throws SQLException {

		conn = Conexao.getConnection();
		String consulta = "select * from perfil where idPerfil=(select MAX(idPerfil) from perfil)";

		int maxId;
		int idEndereco;
		int idContato;

		psmt = conn.prepareStatement(consulta);
		rs = psmt.executeQuery();

		maxId = rs.getInt(1);

		PerfilBO bo = new PerfilBO();

		bo.excluir(maxId);

		consulta = "SELECT * FROM Perfil INNER JOIN Endereco ON Endereco.idEndereco = Perfil.idEndereco "
				+ "INNER JOIN Contato ON Contato.idContato = Perfil.idContato where idPerfil=" + maxId;

		psmt = conn.prepareStatement(consulta);
		rs = psmt.executeQuery();

		assertTrue("Excluido com sucesso", !rs.next());

	}

}
