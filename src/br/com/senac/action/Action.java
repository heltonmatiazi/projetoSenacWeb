package br.com.senac.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.excecao.ActionException;

public interface Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ActionException;
}
