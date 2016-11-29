package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.senac.excecao.ProblemaTecnicoError;
import br.com.senac.model.Usuario;

public class UsuarioDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstm;

	public Integer inserir(Usuario user) throws SQLException {
		Integer chave = null;
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("INSERT INTO USUARIO(LOGIN, SENHA, NOMEUSUARIO, TIPOUSUARIO) VALUES (?,?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, user.getLogin());
		pstm.setString(2, user.getSenha());
		pstm.setString(3, user.getNomeUsuario());
		pstm.setInt(4, user.getTipoUsuario());
		pstm.executeUpdate();

		ResultSet rs = pstm.getGeneratedKeys();
		if (rs.next()) {
			chave = rs.getInt(1);
		}
		return chave;
	}

	public boolean validaLogin(String login) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT LOGIN FROM USUARIO WHERE LOGIN = ?");
			pstm.setString(1, login);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				return false;
			}

			return true;
		} catch (SQLException e) {
			throw new ProblemaTecnicoError("Falha ao validar login.");
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	// public boolean alterar(Usuario user) throws SQLException {
	// try {
	// conn = Conexao.getConnection();
	// pstm = conn
	// .prepareStatement("UPDATE USUARIO SET LOGIN = ?, SENHA = ?, TIPOUSUARIO =
	// ? WHERE IDUSUARIO = ?");
	// pstm.setString(1, user.getLogin());
	// pstm.setString(2, user.getSenha());
	// pstm.setInt(3, user.getTipoUsuario());
	// pstm.setInt(4, user.getIdUsuario());
	// int linhas = pstm.executeUpdate();
	// if (linhas > 0) {
	// return true;
	// }
	//
	// return false;
	// } finally {
	// Conexao.closeResources(conn, pstm);
	// }
	// }
	public boolean excluir(Usuario user) throws SQLException {

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("DELETE FROM USUARIO WHERE LOGIN = ?");
		pstm.setString(1, user.getLogin());
		int linhas = pstm.executeUpdate();

		if (linhas > 0) {
			return true;
		}

		return false;
	}

	public Usuario getUsuario(Usuario u) throws SQLException {
		Usuario usuario = new Usuario();
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT * FROM USUARIO WHERE LOGIN = ?, SENHA = ?");
			pstm.setString(1, u.getLogin());
			pstm.setString(2, u.getSenha());
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setTipoUsuario(rs.getInt("tipo"));
				usuario.setNomeUsuario(rs.getString("nomeUsuario"));
			}
			rs.close();
			return usuario;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public Usuario getPorLogin(String login) throws SQLException {
		Usuario u = null;
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT * FROM USUARIO WHERE LOGIN = ?");
			pstm.setString(1, login);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setSenha(rs.getString("senha"));
				u.setTipoUsuario(rs.getInt("tipoUsuario"));
				u.setNomeUsuario(rs.getString("nomeUsuario"));
			}
			rs.close();
			return u;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}
}
