package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Experiencia;
import br.com.senac.model.TipoTrabalho;

public class ExperienciasDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private PreparedStatement pstm;
	private Connection conn;

	public void inserir(Experiencia exp) throws SQLException {
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement(
				"INSERT INTO EXPERIENCIAS(IDPERFIL, EMPRESA,CARGO, DATAINGRESSO, DATASAIDA, IDTIPOTRABALHO) VALUES(?,?,?,?,?,?);");

		pstm.setInt(1, exp.getIdPerfil());
		pstm.setString(2, exp.getEmpresa());
		pstm.setString(3, exp.getCargo());
		pstm.setDate(4, new java.sql.Date(exp.getDataIngresso().getTime()));
		pstm.setDate(5, new java.sql.Date(exp.getDataSaida().getTime()));
		pstm.setInt(6, exp.getTipoTrabalho().getCodigoTipo());
		int linhas = pstm.executeUpdate();

		if (linhas == 0) {
			throw new FalhaBancoException("Erro ao gravar experiência profissional.");
		}

	}

	public boolean alterar(Experiencia exp) throws SQLException {

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement(
				"UPDATE EXPERIENCIAS SET EMPRESA = ?, CARGO = ?, DATAINGRESSO = ?, DATASAIDA = ?, IDTIPOTRABALHO = ? WHERE IDEXPERIENCIAS = ?");
		pstm.setString(1, exp.getEmpresa());
		pstm.setString(2, exp.getCargo());
		pstm.setDate(3, new java.sql.Date(exp.getDataIngresso().getTime()));
		pstm.setDate(4, new java.sql.Date(exp.getDataSaida().getTime()));
		pstm.setInt(5, exp.getTipoTrabalho().getCodigoTipo());
		pstm.setInt(6, exp.getIdExperiencia());
		int linhas = pstm.executeUpdate();

		if (linhas > 0) {
			return true;
		}

		return false;

	}

	public boolean excluirExperiencia(Integer idExp) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("DELETE FROM EXPERIENCIAS WHERE IDEXPERIENCIAS = ?");
			pstm.setInt(1, idExp);
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
		pstm = conn.prepareStatement("DELETE FROM EXPERIENCIAS WHERE IDPERFIL = ?");
		pstm.setInt(1, idPerfil);
		int linhas = pstm.executeUpdate();

		if (linhas > 0) {
			return true;
		}

		return false;

	}

	public List<Experiencia> getByIdPerfil(Integer id) throws SQLException {
		List<Experiencia> lista = new ArrayList<>();

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM EXPERIENCIAS WHERE IDPERFIL = ?");
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();

		while (rs.next()) {
			Experiencia e = new Experiencia();
			e.setCargo(rs.getString("cargo"));
			e.setEmpresa("empresa");
			e.setIdExperiencia(rs.getInt("idexperiencias"));
			e.setIdPerfil(id);
			e.setDataIngresso(new java.util.Date(rs.getDate("dataingresso").getTime()));
			e.setDataSaida(new java.util.Date(rs.getDate("datasaida").getTime()));
			switch (rs.getInt("idtipotrabalho")) {
			case 1:
				e.setTipoTrabalho(TipoTrabalho.ESTAGIO);
				break;
			case 2:
				e.setTipoTrabalho(TipoTrabalho.CONTRATACAO);
				break;
			case 3:
				e.setTipoTrabalho(TipoTrabalho.CLT);
				break;
			case 4:
				e.setTipoTrabalho(TipoTrabalho.CONCURSO);
				break;
			}

			lista.add(e);
		}
		rs.close();

		return lista;

	}
}
