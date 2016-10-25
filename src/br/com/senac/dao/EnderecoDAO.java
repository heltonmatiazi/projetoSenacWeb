
package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.senac.excecao.FalhaBancoException;
import br.com.senac.model.Endereco;
import br.com.senac.model.Estado;

public class EnderecoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstm;

	public Integer inserir(Endereco end) throws SQLException {
		Integer id = null;
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement(
				"INSERT INTO ENDERECO(IDESTADO,RUA,NUMERO,COMPLEMENTO,CIDADE,CEP) VALUES(?,?,?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);

		pstm.setInt(1, end.getEstado().getId());
		pstm.setString(2, end.getRua());
		pstm.setInt(3, end.getNumero());
		pstm.setString(4, end.getComplemento());
		pstm.setString(5, end.getCidade());
		pstm.setString(6, end.getCep());
		pstm.executeUpdate();

		ResultSet rs = pstm.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
			end.setId(id);
		}
		rs.close();

		if (id == null) {
			throw new FalhaBancoException("Erro ao gravar endereço.");
		}

		return id;
	}

	public boolean alterar(Endereco end) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement(
					"UPDATE ENDERECO SET IDESTADO = ?, RUA = ?, NUMERO = ?, COMPLEMENTO = ?, CIDADE = ?, CEP =? WJERE IDENDERECO = ?");
			pstm.setInt(1, end.getEstado().getId());
			pstm.setString(2, end.getRua());
			pstm.setInt(3, end.getNumero());
			pstm.setString(4, end.getComplemento());
			pstm.setString(5, end.getCidade());
			pstm.setString(6, end.getCep());
			pstm.setInt(7, end.getId());
			int linhas = pstm.executeUpdate();

			if (linhas > 0) {
				return true;
			}

			return false;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public boolean excluir(Integer idEndereco) throws SQLException {
		try {
			conn = Conexao.getConnection();
			pstm = conn.prepareStatement("DELETE FROM ENDERECO WHERE IDENDERECO = ?");
			pstm.setInt(1, idEndereco);
			int linhas = pstm.executeUpdate();

			if (linhas > 0) {
				return true;
			}

			return false;
		} finally {
			Conexao.closeResources(conn, pstm);
		}
	}

	public Endereco getById(Integer id) throws SQLException {
		Endereco end = new Endereco();
		EstadoDAO estadoDao = new EstadoDAO();

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM ENDERECO WHERE IDENDERECO = ?");
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			end.setId(id);
			end.setCep(rs.getString("cep"));
			end.setCidade(rs.getString("cidade"));
			end.setComplemento(rs.getString("complemento"));
			Estado estado = estadoDao.getById(rs.getInt("idestado"));
			end.setEstado(estado);
			end.setNumero(rs.getInt("numero"));
			end.setRua(rs.getString("rua"));
		}
		rs.close();

		return end;

	}
}
