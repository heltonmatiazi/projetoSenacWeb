package br.com.senac.action;

import br.com.senac.excecao.ActionException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
        HttpSession sessao = request.getSession();
        sessao.invalidate();
        return "404";
    }

}
