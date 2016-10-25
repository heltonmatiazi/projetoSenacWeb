package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.CursoEgresso;
import br.com.senac.model.Titulo;

public class CursoEgressoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstm;
	private Connection conn;

	public void inserir(CursoEgresso curso) throws SQLException {
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("INSERT INTO CURSOEGRESSO(IDPERFIL, NOMECURSO, UNIDADESENAC,ANOINGRESSO,"
				+ "SEMESTREINGRESSO,ANOCONCLUSAO,SEMESTRECONCLUSAO,IDTITULO) VALUES(?,?,?,?,?,?,?,?);");

		pstm.setInt(1, curso.getIdPerfil());
		pstm.setString(2, curso.getNomeCurso());
		pstm.setString(3, curso.getUnidadeSenac());
		pstm.setInt(4, curso.getAnoIngresso());
		pstm.setInt(5, curso.getSemestreIngresso());
		pstm.setInt(6, curso.getAnoConclusao());
		pstm.setInt(7, curso.getSemestreConclusao());
		pstm.setInt(8, curso.getTitulo().getCodigoTitulo());

		int linhas = pstm.executeUpdate();

		if (linhas == 0) {
			throw new FalhaBancoException("Erro ao gravar informações do curso.");
		}

	}

	public boolean alterar(CursoEgresso curso) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"UPDATE CURSOEGRESSOS SET NOMECURSO =?, UNIDADESENAC = ?, ANOINGRESSO = ?, SEMESTREINGRESSO = ?, ANOCONLUSAO = ?, SEMESTRECONCLUSAO = ?, IDTITULO = ? WHERE IDCURSOEGRESSO = ?");
			pstm.setString(1, curso.getNomeCurso());
			pstm.setString(2, curso.getUnidadeSenac());
			pstm.setInt(3, curso.getAnoIngresso());
			pstm.setInt(4, curso.getSemestreIngresso());
			pstm.setInt(5, curso.getAnoConclusao());
			pstm.setInt(6, curso.getSemestreConclusao());
			pstm.setInt(7, curso.getTitulo().getCodigoTitulo());
			pstm.setInt(8, curso.getIdCursoEgresso());
			int linhas = pstm.executeUpdate();

			if (linhas > 0) {
				return true;
			}

			return false;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public boolean excluirCurso(Integer idCurso) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("DELETE FROM CURSOEGRESSO WHERE IDCURSOEGRESSO = ?");
			pstm.setInt(1, idCurso);
			int linhas = pstm.executeUpdate();

			if (linhas > 0) {
				return true;
			}

			return false;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public boolean excluir(Integer idPerfil) throws SQLException {

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("DELETE FROM CURSOEGRESSO WHERE IDPERFIL = ?");
		pstm.setInt(1, idPerfil);
		int linhas = pstm.executeUpdate();

		if (linhas > 0) {
			return true;
		}

		return false;
	}

	public List<CursoEgresso> getByIdPerfil(Integer id) throws SQLException {
		List<CursoEgresso> lista = new ArrayList<>();

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM CURSOEGRESSO WHERE IDPERFIL = ?");
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			CursoEgresso c = new CursoEgresso();
			c.setAnoConclusao(rs.getInt("anoconclusao"));
			c.setAnoIngresso(rs.getInt("anoingresso"));
			c.setIdCursoEgresso(rs.getInt("idcursoegresso"));
			c.setIdPerfil(id);
			c.setNomeCurso(rs.getString("nomeCurso"));
			c.setSemestreConclusao(rs.getInt("semestreconclusao"));
			c.setSemestreIngresso(rs.getInt("semestreingresso"));
			c.setUnidadeSenac(rs.getString("unidadesenac"));
			switch (rs.getInt("idtitulo")) {
			case 1:
				c.setTitulo(Titulo.TECNOLOGO);
				break;
			case 2:
				c.setTitulo(Titulo.LICENCIATURA);
				break;
			case 3:
				c.setTitulo(Titulo.BACHARELADO);
				break;
			case 4:
				c.setTitulo(Titulo.MESTRADO);
				break;
			case 5:
				c.setTitulo(Titulo.DOUTORADO);
				break;
			case 6:
				c.setTitulo(Titulo.POS_DOUTORADO);
				break;
			}

			lista.add(c);
		}
		rs.close();

		return lista;

	}
}
