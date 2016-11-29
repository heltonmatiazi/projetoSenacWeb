package br.com.senac.action;

import br.com.senac.bo.UsuarioBO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.senac.excecao.ActionException;
import br.com.senac.model.Usuario;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;

public class LoginAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        String username = request.getParameter("login");
        String pw = request.getParameter("senha");
        UsuarioBO bo = new UsuarioBO();
        Usuario usuario;
        try {
            usuario = bo.validaUsuario(username, pw);
            if (usuario != null) {
                HttpSession sessao = request.getSession();
                sessao.setAttribute("usuario", usuario);
                return "main";
            } else {
                request.setAttribute("message", "Usuário não encontrado. Verifique login e senha.");
                return "404";
            }
        } catch (SQLException e) {
            throw new ActionException(e.getMessage());
        }

    }

}
