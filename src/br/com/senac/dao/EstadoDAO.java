
package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senac.model.Estado;

public class EstadoDAO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Connection conn;
	private PreparedStatement pstm;

	public Estado getById(Integer id) throws SQLException {
		Estado e = new Estado();

		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM ESTADO WHERE IDESTADO = ?");
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			e.setNome(rs.getString("nomeestado"));
			e.setSigla(rs.getString("siglaestado"));
			e.setId(id);
		}
		rs.close();

		return e;
	}

	public List<Estado> listaEstados() throws SQLException {
		List<Estado> lista = new ArrayList<>();
		conn = Conexao.getConnection();
		pstm = conn.prepareStatement("SELECT * FROM ESTADO");
		ResultSet rs = pstm.executeQuery();
		while (rs.next()) {
			Estado e = new Estado();
			e.setNome(rs.getString("nomeestado"));
			e.setSigla(rs.getString("siglaestado"));
			e.setId(rs.getInt("idEstado"));
			lista.add(e);
		}
		rs.close();

		return lista;

	}
}
