package br.com.senac.dao;

import br.com.senac.model.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    private Connection conn;
    private PreparedStatement pstm;

    public List<Curso> getCursos() throws SQLException {
        List<Curso> cursosCadastrados = new ArrayList<>();
        try {
            conn = Conexao.getConnection();
            pstm = conn.prepareStatement("SELECT * FROM CURSO");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Curso c = new Curso();
                c.setIdCurso(rs.getInt("idCurso"));
                c.setNomeCurso(rs.getString("nomeCurso"));
                cursosCadastrados.add(c);
            }
            rs.close();

            return cursosCadastrados;
        } finally {
            Conexao.closeResources(conn, pstm);
        }
    }
}
