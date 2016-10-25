package br.com.senac.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.senac.dao.PerfilDAO;
import br.com.senac.model.Perfil;

public class FiltrosBO {

	public List<Perfil> getPerfilPorNome(String campo) throws SQLException {
		PerfilDAO perfilDao = new PerfilDAO();
		List<Perfil> lista = perfilDao.getPorNome(campo);

		return lista;
	}

	public List<Perfil> getPerfilPorCurso(String campo) throws SQLException {
		PerfilDAO perfilDao = new PerfilDAO();
		List<Perfil> lista = perfilDao.getPorCurso(campo);

		return lista;
	}

	public List<Perfil> getPerfilPorUnidade(String campo) throws SQLException {
		PerfilDAO perfilDao = new PerfilDAO();
		List<Perfil> lista = perfilDao.getPorUnidadeSenac(campo);

		return lista;
	}

	public List<Perfil> getPerfilPorAno(String campo) throws SQLException {
		PerfilDAO perfilDao = new PerfilDAO();
		List<Perfil> lista = perfilDao.getPorAno(campo);

		return lista;
	}

	public List<Perfil> getPerfilPorSemestre(String campo) throws SQLException {
		PerfilDAO perfilDao = new PerfilDAO();
		List<Perfil> lista = perfilDao.getPorSemestre(campo);

		return lista;
	}

}
