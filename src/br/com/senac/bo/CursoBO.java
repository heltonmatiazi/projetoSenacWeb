package br.com.senac.bo;

import br.com.senac.dao.CursoDAO;
import br.com.senac.model.Curso;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;

public class CursoBO {

    private CursoDAO dao;

    public List<Curso> getCursos() throws ServletException {
        dao = new CursoDAO();
        try {
            return dao.getCursos();
        } catch (SQLException e) {
            throw new ServletException(e.getMessage());
        }
    }
}
