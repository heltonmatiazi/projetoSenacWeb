package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Contato;
import br.com.senac.model.CursoEgresso;
import br.com.senac.model.Endereco;
import br.com.senac.model.Experiencia;
import br.com.senac.model.Perfil;
import br.com.senac.model.Sexo;

public class PerfilDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstm;
	private CursoEgressoDAO ceDAO;
	private ExperienciasDAO expDAO;
	private ContatoDAO contato;
	private EnderecoDAO endereco;
	private boolean cursoOK;
	private boolean expOK;
	private boolean enderecoOK;
	private boolean contatoOK;

	private Perfil p;

	public void preCadastro(Perfil perfil) throws SQLException, FalhaBancoException {
		Integer id = null;
		Integer idContato = null;

		try {
			contato = new ContatoDAO();

			conn = Conexao.getConnection();
			conn.setAutoCommit(false);

			idContato = contato.inserir(perfil.getContato());

			pstm = conn.prepareStatement(
					"INSERT INTO PERFIL(NOME, SOBRENOME, DATANASCIMENTO, SEXO, CPF, IDCONTATO) VALUES(?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, perfil.getNome());
			pstm.setString(2, perfil.getSobrenome());
			pstm.setDate(3, new java.sql.Date(perfil.getDataNascimento().getTime()));
			pstm.setString(4, perfil.getSexo().name());
			pstm.setString(5, perfil.getCpf());
			pstm.setInt(6, idContato);
			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
				perfil.setIdPerfil(id);
			}
			rs.close();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			conn.rollback();
			throw new FalhaBancoException(e.getMessage(), "Erro ao gravar aluno.");
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public void inserir(Perfil perfil) throws SQLException, FalhaBancoException {
		Integer id = null;
		Integer idContato = null;
		Integer idEndereco = null;
		try {
			expDAO = new ExperienciasDAO();
			ceDAO = new CursoEgressoDAO();
			contato = new ContatoDAO();
			endereco = new EnderecoDAO();

			conn = Conexao.getConnection();
			conn.setAutoCommit(false);

			idContato = contato.inserir(perfil.getContato());
			idEndereco = endereco.inserir(perfil.getEndereco());

			pstm = conn.prepareStatement(
					"INSERT INTO PERFIL(FORMACAOBASICA, FORMACAOENSINOMEDIO, NOME, SOBRENOME, DATANASCIMENTO, SEXO, CPF, IDCONTATO, IDENDERECO) VALUES(?,?,?,?,?,?,?,?,?);",
					Statement.RETURN_GENERATED_KEYS);

			pstm.setString(1, perfil.getFormacaoBasica());
			pstm.setString(2, perfil.getFormacaoEnsinoMedio());
			pstm.setString(3, perfil.getNome());
			pstm.setString(4, perfil.getSobrenome());
			pstm.setDate(5, new java.sql.Date(perfil.getDataNascimento().getTime()));
			pstm.setString(6, perfil.getSexo().name());
			pstm.setString(7, perfil.getCpf());
			pstm.setInt(8, idContato);
			pstm.setInt(9, idEndereco);
			pstm.executeUpdate();

			ResultSet rs = pstm.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
				perfil.setIdPerfil(id);
			}
			rs.close();

			if (id == null) {
				conn.rollback();
				throw new FalhaBancoException("Erro ao gravar aluno.");
			}

			for (CursoEgresso c : perfil.getCurso()) {
				c.setIdPerfil(perfil.getIdPerfil());
				ceDAO.inserir(c);
			}

			for (Experiencia exp : perfil.getExperiencias()) {
				exp.setIdPerfil(perfil.getIdPerfil());
				expDAO.inserir(exp);
			}

			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			conn.rollback();
			throw new FalhaBancoException(e.getMessage(), "Erro ao gravar aluno.");
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public void alterar(Perfil p) throws SQLException {
		try {
			conn = Conexao.getConnection();
			conn.setAutoCommit(false);
			cursoOK = false;
			expOK = false;
			enderecoOK = false;
			contatoOK = false;
			expDAO = new ExperienciasDAO();
			ceDAO = new CursoEgressoDAO();
			endereco = new EnderecoDAO();
			contato = new ContatoDAO();

			/*
			 * Decisão caso seja aluno pré-cadastrado atualizando dados
			 */
			if (p.getCurso().get(0).getIdCursoEgresso() == null) {
				for (CursoEgresso c : p.getCurso()) {
					c.setIdPerfil(p.getIdPerfil());
					ceDAO.inserir(c);
				}
			} else {
				for (CursoEgresso c : p.getCurso()) {
					cursoOK = ceDAO.alterar(c);
					if (!cursoOK) {
						throw new FalhaBancoException("Erro ao gravar cursos.");
					}
				}
			}

			if (p.getExperiencias().get(0).getIdExperiencia() == null) {
				for (Experiencia exp : p.getExperiencias()) {
					exp.setIdPerfil(p.getIdPerfil());
					expDAO.inserir(exp);
				}
			} else {
				for (Experiencia exp : p.getExperiencias()) {
					expOK = expDAO.alterar(exp);
					if (!expOK) {
						throw new FalhaBancoException("Erro ao gravar experiências profissionais.");
					}
				}
			}

			if (p.getEndereco().getId() == null) {
				endereco.inserir(p.getEndereco());
			} else {
				enderecoOK = endereco.alterar(p.getEndereco());
			}

			contatoOK = contato.alterar(p.getContato());

			pstm = conn.prepareStatement(
					"UPDATE PERFIL SET FORMACAOBASICA = ?, FORMACAOENSINOMEDIO = ?,  NOME = ?, SOBRENOME = ?, DATANASCIMENTO = ?, SEXO = ?, CPF = ?, IDCONTATO = ?, IDENDERECO = ? WHERE IDPERFIL = ?");
			pstm.setString(1, p.getFormacaoBasica());
			pstm.setString(2, p.getFormacaoEnsinoMedio());
			pstm.setString(3, p.getNome());
			pstm.setString(4, p.getSobrenome());
			pstm.setDate(5, new java.sql.Date(p.getDataNascimento().getTime()));
			pstm.setString(6, p.getSexo().toString());
			pstm.setString(7, p.getCpf());
			pstm.setInt(8, p.getContato().getIdContato());
			pstm.setInt(9, p.getEndereco().getId());
			pstm.setInt(10, p.getIdPerfil());
			int linhas = pstm.executeUpdate();

			if (cursoOK && expOK && contatoOK && enderecoOK && linhas > 0) {
				conn.commit();
			}
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			conn.rollback();
			throw new FalhaBancoException(e.getMessage(), "Erro ao gravar aluno.");
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public void excluir(Perfil p) throws SQLException {
		try {
			conn = Conexao.getConnection();
			conn.setAutoCommit(false);

			pstm = conn.prepareStatement("DELETE FROM PERFIL WHERE IDPERFIL = ?");
			pstm.setInt(1, p.getIdPerfil());
			int linhas = pstm.executeUpdate();

			ceDAO = new CursoEgressoDAO();
			expDAO = new ExperienciasDAO();

			for (CursoEgresso c : p.getCurso()) {
				cursoOK = ceDAO.excluir(c.getIdCursoEgresso());
			}

			for (Experiencia exp : p.getExperiencias()) {
				expOK = expDAO.excluir(exp.getIdExperiencia());
			}

			if (linhas > 0 && cursoOK && expOK) {
				conn.commit();
			} else {
				conn.rollback();
				// throw exception
			}

			conn.setAutoCommit(true);
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public void excluir(Integer id) throws SQLException {
		try {
			conn = Conexao.getConnection();
			conn.setAutoCommit(false);

			ceDAO = new CursoEgressoDAO();
			expDAO = new ExperienciasDAO();

			cursoOK = ceDAO.excluir(id);
			expOK = expDAO.excluir(id);

			pstm = conn.prepareStatement("DELETE FROM PERFIL WHERE IDPERFIL = ?");
			pstm.setInt(1, id);
			int linhas = pstm.executeUpdate();

			if (linhas > 0 && cursoOK && expOK) {
				conn.commit();
			} else {
				conn.rollback();
				// throw exception
			}

			conn.setAutoCommit(true);
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public Perfil getById(Integer id) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT * FROM PERFIL WHERE IDPERFIL = ?");
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				p = getPerfil(rs);
			}

			return p;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPerfilPorNome(String nome) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {

			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT * FROM PERFIL WHERE NOME LIKE ?");
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = getPerfil(rs);
				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPorNome(String nome) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT NOME, SOBRENOME, IDPERFIL FROM PERFIL WHERE NOME LIKE ?");
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = new Perfil();
				p.setNome(rs.getString("nome"));
				p.setSobrenome(rs.getString("sobrenome"));
				p.setIdPerfil(rs.getInt("idPerfil"));

				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPorCurso(String nome) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"SELECT PERFIL.NOME, PERFIL.SOBRENOME, PERFIL.IDPERFIL FROM PERFIL INNER JOIN CURSOEGRESSO ON PERFIL.IDPERFIL = CURSOEGRESSO.IDPERFIL WHERE CURSOEGRESSO.NOMECURSO LIKE ?");
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = new Perfil();
				p.setNome(rs.getString("nome"));
				p.setSobrenome(rs.getString("sobrenome"));
				p.setIdPerfil(rs.getInt("idperfil"));

				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPorUnidadeSenac(String nome) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"SELECT PERFIL.NOME, PERFIL.SOBRENOME, PERFIL.IDPERFIL FROM PERFIL INNER JOIN CURSOEGRESSO ON PERFIL.IDPERFIL = CURSOEGRESSO.IDPERFIL WHERE CURSOEGRESSO.UNIDADESENAC LIKE ?");
			pstm.setString(1, "%" + nome + "%");
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = new Perfil();
				p.setNome(rs.getString("nome"));
				p.setSobrenome(rs.getString("sobrenome"));
				p.setIdPerfil(rs.getInt("idPerfil"));

				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPorAno(String campo) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"SELECT PERFIL.NOME, PERFIL.SOBRENOME, PERFIL.IDPERFIL FROM PERFIL INNER JOIN CURSOEGRESSO ON PERFIL.IDPERFIL = CURSOEGRESSO.IDPERFIL WHERE CURSOEGRESSO.ANOCONCLUSAO = ?");
			pstm.setString(1, campo);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = new Perfil();
				p.setNome(rs.getString("nome"));
				p.setSobrenome(rs.getString("sobrenome"));
				p.setIdPerfil(rs.getInt("idPerfil"));

				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public List<Perfil> getPorSemestre(String campo) throws SQLException {
		Perfil p = null;
		List<Perfil> lista = new ArrayList<>();

		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"SELECT PERFIL.NOME, PERFIL.SOBRENOME, PERFIL.SOBRENOME, PERFIL.IDPERFIL FROM PERFIL INNER JOIN CURSOEGRESSO ON PERFIL.IDPERFIL = CURSOEGRESSO.IDPERFIL WHERE CURSOEGRESSO.SEMESTRECONCLUSAO = ?");
			pstm.setString(1, campo);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				p = new Perfil();
				p.setNome(rs.getString("nome"));
				p.setSobrenome(rs.getString("sobrenome"));
				p.setIdPerfil(rs.getInt("idPerfil"));

				lista.add(p);
			}

			return lista;

		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	private Perfil getPerfil(ResultSet rs) throws SQLException {
		expDAO = new ExperienciasDAO();
		ceDAO = new CursoEgressoDAO();
		contato = new ContatoDAO();
		endereco = new EnderecoDAO();

		p = new Perfil();
		p.setCpf(rs.getString("cpf"));
		p.setNome(rs.getString("nome"));
		p.setSobrenome(rs.getString("sobrenome"));
		p.setDataNascimento(new java.util.Date(rs.getDate("datanascimento").getTime()));
		p.setSexo(Sexo.valueOf(rs.getString("sexo").toUpperCase()));
		p.setIdPerfil(rs.getInt("idPerfil"));
		p.setFormacaoBasica(rs.getString("formacaoBasica"));
		p.setFormacaoEnsinoMedio(rs.getString("formacaoEnsinoMedio"));

		Contato c = new Contato();
		c = contato.getById(rs.getInt("idcontato"));
		Endereco e = new Endereco();
		e = endereco.getById(rs.getInt("idendereco"));

		List<Experiencia> exp = expDAO.getByIdPerfil(rs.getInt("idPerfil"));
		List<CursoEgresso> cursoegresso = ceDAO.getByIdPerfil(rs.getInt("idPerfil"));

		p.setContato(c);
		p.setEndereco(e);
		p.setExperiencias(exp);
		p.setCurso(cursoegresso);

		return p;
	}

}
