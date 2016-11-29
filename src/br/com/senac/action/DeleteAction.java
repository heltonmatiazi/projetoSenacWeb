package br.com.senac.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.bo.PerfilBO;
import br.com.senac.excecao.ActionException;
import br.com.senac.excecao.FalhaBancoException;

public class DeleteAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        int userId = Integer.parseInt(request.getParameter("id"));
        PerfilBO perfilBO = new PerfilBO();
        try {
            perfilBO.excluir(userId);
            request.setAttribute("message", "Aluno excluído com sucesso.");
        } catch (FalhaBancoException | SQLException e) {
            request.setAttribute("message", e);
        }
        return "busca";
    }

}
