package br.com.senac.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.bo.FiltrosBO;
import br.com.senac.excecao.ActionException;
import br.com.senac.model.Perfil;

public class PesquisaAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException {
		FiltrosBO filtro = new FiltrosBO();
		List<Perfil> lista = new ArrayList<>();
		String tipo = request.getParameter("p");

		try {
			switch (tipo) {
			case "1":
				lista = filtro.getPerfilPorNome(request.getParameter("text"));
				break;
			case "2":
				lista = filtro.getPerfilPorCurso(request.getParameter("curso"));
				break;
			case "3":
				lista = filtro.getPerfilPorUnidade(request.getParameter("unidade-senac"));
				break;
			case "4":
				lista = filtro.getPerfilPorAno(request.getParameter("ano-conclusao"));
				break;
			case "5":
				lista = filtro.getPerfilPorSemestre(request.getParameter("semestre-conclusao"));
				break;
			case "6": // pesquisaporestado
				break;
			}
			request.setAttribute("lista", lista);
			return "busca";

		} catch (SQLException e) {
			throw new ActionException(e.getMessage(), e);
		}

	}

}
