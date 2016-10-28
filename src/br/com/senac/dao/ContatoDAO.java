
package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Contato;

public class ContatoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstm;

	public Integer inserir(Contato c) throws SQLException {
		Integer id = null;

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("INSERT INTO CONTATO(EMAIL, TELEFONE) VALUES(?,?)",
				Statement.RETURN_GENERATED_KEYS);

		pstm.setString(1, c.getEmail());
		pstm.setString(2, c.getTelefone());
		pstm.executeUpdate();

		ResultSet rs = pstm.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
			c.setIdContato(id);
		}
		rs.close();

		if (id == null) {
			throw new FalhaBancoException("Erro ao gravar contato.");
		}

		return id;
	}

	public boolean alterar(Contato c) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("UPDATE CONTATO SET EMAIL = ?, TELEFONE = ? WHERE IDCONTATO = ?");
			pstm.setString(1, c.getEmail());
			pstm.setString(2, c.getTelefone());
			pstm.setInt(3, c.getIdContato());
			int linhas = pstm.executeUpdate();

			if (linhas > 0) {
				return true;
			}

			return false;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public boolean excluir(Integer idContato) throws SQLException {
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("DELETE FROM CONTATO WHERE IDCONTATO = ?");
		pstm.setInt(1, idContato);
		int linhas = pstm.executeUpdate();

		if (linhas > 0) {
			return true;
		}

		return false;
	}

	public Contato getById(Integer id) throws SQLException {
		Contato c = new Contato();
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM CONTATO WHERE IDCONTATO = ?");
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			c.setIdContato(id);
			c.setEmail(rs.getString("email"));
			c.setTelefone(rs.getString("telefone"));
		}
		rs.close();

		return c;
	}
}
