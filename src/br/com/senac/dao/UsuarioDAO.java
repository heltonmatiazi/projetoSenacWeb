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

	public void inserir(Usuario user) throws SQLException {

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("INSERT INTO USUARIO(LOGIN, SENHA, TIPOUSUARIO) VALUES (?,?,?);",
				Statement.RETURN_GENERATED_KEYS);
		pstm.setString(1, user.getLogin());
		pstm.setString(2, user.getSenha());
		pstm.setInt(3, user.getTipoUsuario());
		pstm.executeUpdate();

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

	public Integer getPorLogin(String cpf) throws SQLException {
		Integer id = null;
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("SELECT IDUSUARIO FROM USUARIO WHERE LOGIN = ?");
			pstm.setString(1, cpf);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			rs.close();
			return id;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}
}
