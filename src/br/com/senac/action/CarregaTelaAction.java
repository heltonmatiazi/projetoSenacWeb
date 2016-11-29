package br.com.senac.action;

import br.com.senac.bo.PerfilBO;
import br.com.senac.excecao.ActionException;
import br.com.senac.model.Perfil;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarregaTelaAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        PerfilBO perfilBO = new PerfilBO();
        Perfil p = new Perfil();
        try {
            p = perfilBO.getById(id);
            request.setAttribute("perfil", p);
            return "main";
        } catch (SQLException e) {
            request.setAttribute("message", e.getMessage());
            return "500";
        }

    }
}
