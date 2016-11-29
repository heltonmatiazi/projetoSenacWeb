package br.com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.senac.excecao.FalhaBancoException;

public class Conexao {

	private static final String URL = "jdbc:mysql://localhost:3306/egressos";
	private static final String LOGIN = "root";
	private static final String PASS = "admin";
	private static Connection conn;

	public static Connection getConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			return conn;
		}
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			throw new FalhaBancoException(e.getMessage());
		}

		conn = DriverManager.getConnection(URL, LOGIN, PASS);
		return conn;

	}

	private static void closeAll(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (ps != null) {
			ps.close();
		}

		if (c != null) {
			c.close();
		}

	}

	public static void closeResources(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {
		closeAll(c, ps, rs);
	}

	public static void closeResources(Connection c, PreparedStatement ps) throws SQLException {
		closeAll(c, ps, null);
	}
}
