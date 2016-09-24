package br.com.senac.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/projeto_egressos";
    private static final String LOGIN = "";
    private static final String PASS = "";
    private static Connection conn;
    
    public static Connection getConnection() throws SQLException {               
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        conn = DriverManager.getConnection(URL, LOGIN, PASS);
        return conn;
	
    }
	
    private static void closeAll(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {
	if(c != null) {
            c.close();
	}
	if(ps != null) {
            ps.close();
	}
        if(rs != null) {
            rs.close();
	}
    }
	
	
    public static void closeResources(Connection c, PreparedStatement ps, ResultSet rs) throws SQLException {  
	closeAll(c,ps,rs);
    }
	
    public static void closeResources(Connection c, PreparedStatement ps) throws SQLException {
	closeAll(c,ps,null);
    }
}



	