package br.com.senac.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import br.com.senac.excecao.ProblemaTecnicoError;

public class Conexao implements Serializable {
	private static final long serialVersionUID = 1L;
	private static PoolProperties p;
	private static DataSource ds;
	private static Connection conn;

	public static Connection getConnection() throws SQLException {
		try {
			if (conn != null && !conn.isClosed()) {
				return conn;
			}

			p = new PoolProperties();
			p.setUrl("jdbc:mysql://localhost:3306/egressos");
			p.setDriverClassName("com.mysql.jdbc.Driver");
			p.setUsername("root");
			p.setPassword("admin");
			p.setMaxActive(10);
			p.setMaxIdle(10);

			ds = new DataSource();
			ds.setPoolProperties(p);

			conn = ds.getConnection();
			conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

			return conn;
		} catch (SQLException e) {
			throw new ProblemaTecnicoError("Impossível estabelecer comunicação com o banco de dados:" + e.getMessage());
		}

		//
		// if (conn != null && !conn.isClosed()) {
		// return conn;
		// }
		//
		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		//
		// conn = DriverManager.getConnection(URL, LOGIN, PASS);
		//
		// return conn;

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
